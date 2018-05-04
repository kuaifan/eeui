# 压缩图片

> 需要的模块

```js
const weiui_picture = weex.requireModule('weiui_picture');
```

## weiui_picture.compressImage

> 压缩图片列表

```js
/**
 * @param params    详细参数
 * @param callback  回调事件
 */
weiui_picture.compressImage({params}, callback(result))
```

### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| compressSize | `Number` | - | 小于`compressSize`kb的图片不压缩 | 100 |
| selected | `[{Object}]` | - | 已选图片，一般传入回调的`result.lists`数据 | - |

### callback 回调`result`说明

```js
{
    status: 'success',   //状态，详见：注①
    
    lists: [{
        path:'...',         //为原图path
        cutPath:'...',      //为裁剪后path，需判断isCut是否为true
        compressPath:'...', //为压缩后path，需判断compressed是否为true
        // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
        
        isCut: true,        //是否裁剪
        compressed: true,   //是否压缩
        mimeType: 1,        //mime类型
    },
    ......
    ]
}
```

> 注①：

- `success`压缩成功返回
- `error`压缩失败返回

