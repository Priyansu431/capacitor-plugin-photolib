package com.elitecodex.photolib;

import android.content.Context;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.TimeZone;


public class Photolib {

    protected Photolib() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
      }

    private SimpleDateFormat dateFormatter;

    private static final String TAG = Photolib.class.getName();

    public static final String PHOTO_LIBRARY_PROTOCOL = "cdvphotolibrary";

    public static final int DEFAULT_WIDTH = 512;
    public static final int DEFAULT_HEIGHT = 384;
    public static final double DEFAULT_QUALITY = 0.5;

    public static final String ACTION_GET_LIBRARY = "getLibrary";
    public static final String ACTION_GET_ALBUMS = "getAlbums";
    public static final String ACTION_GET_THUMBNAIL = "getThumbnail";
    public static final String ACTION_GET_PHOTO = "getPhoto";
    public static final String ACTION_STOP_CACHING = "stopCaching";
    public static final String ACTION_REQUEST_AUTHORIZATION = "requestAuthorization";
    public static final String ACTION_SAVE_IMAGE = "saveImage";
    public static final String ACTION_SAVE_VIDEO = "saveVideo";


    private Context context;

    Photolib(Context context) {
        this.context = context;
    }


    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public List<String> getAlbums(Context context) throws JSONException {
        List<String> list=new ArrayList<String>();
        // which image properties are we querying
        String[] projection = new String[] {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN
        };

// content:// style URI for the "primary" external storage volume
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Log.i("Uri",MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

// Make the query.
        Cursor cur = context.getContentResolver().query(images,
                projection, // Which columns to return
                null,       // Which rows to return (all rows)
                null,       // Selection arguments (none)
                null        // Ordering
        );

        Log.i("ListingImages"," query count=" + cur.getCount());

        if (cur.moveToFirst()) {
            String bucket;
            String date;
            int bucketColumn = cur.getColumnIndex(
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

            int dateColumn = cur.getColumnIndex(
                    MediaStore.Images.Media.DATE_TAKEN);

            do {
                // Get the field values
                bucket = cur.getString(bucketColumn);
                date = cur.getString(dateColumn);

                list.add("ListingImages bucket=" + bucket
                        + "  date_taken=" + date);
                // Do something with the values.
                Log.i("ListingImages", " bucket=" + bucket
                        + "  date_taken=" + date);
            } while (cur.moveToNext());

        }
        return list;
    
      }
    
      private String queryContentProvider(Context context, Uri collection, JSONObject columns, String whereClause) throws JSONException {

        final ArrayList<String> columnNames = new ArrayList<String>();
        final ArrayList<String> columnValues = new ArrayList<String>();
    
        Iterator<String> iteratorFields = columns.keys();
    
        while (iteratorFields.hasNext()) {
          String column = iteratorFields.next();
    
          columnNames.add(column);
          columnValues.add("" + columns.getString(column));
        }
    
        final String sortOrder = MediaStore.Images.Media.DATE_TAKEN + " DESC";
    
        final Cursor cursor = context.getContentResolver().query(
          collection,
          columnValues.toArray(new String[columns.length()]),
          whereClause, null, sortOrder);
    
        final ArrayList<JSONObject> buffer = new ArrayList<JSONObject>();
    
        if (cursor.moveToFirst()) {
          do {
            JSONObject item = new JSONObject();
    
            for (String column : columnNames) {
              int columnIndex = cursor.getColumnIndex(columns.get(column).toString());
    
              if (column.startsWith("int.")) {
                item.put(column.substring(4), cursor.getInt(columnIndex));
                if (column.substring(4).equals("width") && item.getInt("width") == 0) {
                  System.err.println("cursor: " + cursor.getInt(columnIndex));
                }
              } else if (column.startsWith("float.")) {
                item.put(column.substring(6), cursor.getFloat(columnIndex));
              } else if (column.startsWith("date.")) {
                long intDate = cursor.getLong(columnIndex);
                Date date = new Date(intDate);
                item.put(column.substring(5), dateFormatter.format(date));
              } else {
                item.put(column, cursor.getString(columnIndex));
              }
            }
            buffer.add(item);
    
            // TODO: return partial result
    
          }
          while (cursor.moveToNext());
        }
    
        cursor.close();
    
        return "Got data";
    
      }
    


}
