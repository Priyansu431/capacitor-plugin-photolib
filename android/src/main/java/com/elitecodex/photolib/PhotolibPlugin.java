package com.elitecodex.photolib;

import com.getcapacitor.JSObject;
import com.getcapacitor.JSArray;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import org.json.JSONObject;


@CapacitorPlugin(name = "Photolib")
public class PhotolibPlugin extends Plugin {

    private Photolib implementation;

    @Override
    public void load() {
        implementation = new Photolib(getContext());
    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void getAlbums(PluginCall call) {
        JSObject ret = new JSObject();

        try {
            List<String> albums = implementation.getAlbums(getContext());
            JSArray jsArray = new JSArray(albums);

            ret.put("files", jsArray);
            call.resolve(ret);
        }
        catch (JSONException e) {
            JSONException data = e;
            Log.i("JSONException Priyansu",e.toString());
            //some exception handler code.
        }  
        // JSONArray ret = new JSONArray(albums);
        //ret.put("value", implementation.getAlbums(getContext()));
      
    }

}
