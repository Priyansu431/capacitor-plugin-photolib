# capacitor-plugin-photolib

Get albums

## Install

```bash
npm install capacitor-plugin-photolib
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`getAlbums()`](#getalbums)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<any>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------


### getAlbums()

```typescript
getAlbums() => Promise<ReaddirResult>
```

**Returns:** <code>Promise&lt;<a href="#readdirresult">ReaddirResult</a>&gt;</code>

--------------------


### Interfaces


#### ReaddirResult

| Prop        | Type                | Description                                        | Since |
| ----------- | ------------------- | -------------------------------------------------- | ----- |
| **`files`** | <code>string</code> | List of files and directories inside the directory | 1.0.0 |

</docgen-api>
