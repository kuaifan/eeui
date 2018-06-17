# 启动相册拍照

> 需要的模块

```js
const weiui_picture = weex.requireModule('weiui_picture');
```

## weiui_picture.create

> 启动相册或启动拍照。效果及示例代码请[点击这里查看](module/third/pictureSelector/install?id=预览效果)

```js
/**
 * @param params    详细参数
 * @param callback  回调事件
 */
weiui_picture.create({params}, callback(result))
```

### params 参数说明

> 以下参数不要被表面吓到，用不到的可以不写（简单使用基本不用）

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| type | `String` | - | 类型:<br/>`gallery`:启动相册并拍照<br/>`camera`:单独启动拍照或视频 | gallery |
| gallery | `Number` | - | 内容：`0`全部、`1`图片、`2`视频、`3`音频 | 0 |
| maxNum | `Number` | - | 最大选择数量 | 9 |
| minNum | `Number` | - | 最小选择数量 | 0 |
| spanCount | `Number` | - | 每行显示个数 | 4 |
| mode | `Number` | - | 模式：`1`单选、`2`多选 | 2 |
| previewImage | `Boolean` | - | 是否可预览图片 | true |
| previewVideo | `Boolean` | - | 是否可预览视频 | true |
| previewAudio | `Boolean` | - | 是否可预览音频 | true |
| camera | `Boolean` | - | 是否显示拍照按钮 | true |
| format | `String` | - | 拍照保存图片格式:`.JPEG`或`.png` | .JPEG |
| zoomAnim | `Boolean` | - | 图片列表点击 缩放效果 | true |
| multiplier | `Float` | - | Glide加载图片大小`0~1`之间 <br/>如设置`overrideWidth`、`overrideHeight`无效 | 0.5 |
| crop | `Boolean` | - | 是否启用裁剪 | false |
| compress | `Boolean` | - | 是否启用压缩 | false |
| overrideWidth | `Number` | - | glide 加载宽，越小图片列表越流畅 | 100 |
| overrideHeight | `Number` | - | glide 加载高，越小图片列表越流畅 | 100 |
| ratioX | `Number` | - | 裁剪比例 | 1 |
| ratioY | `Number` | - | 裁剪比例 | 1 |
| cropControls | `Boolean` | - | 是否显示uCrop工具栏 | false |
| gif | `Boolean` | - | 是否显示gif图片 | false  |
| freeCrop | `Boolean` | - | 裁剪框是否可拖拽 | false |
| circle | `Boolean` | - | 是否圆形裁剪 | false |
| cropFrame | `Boolean` | - | 是否显示裁剪矩形边框 圆形裁剪时建议设为false | true |
| cropGrid | `Boolean` | - | 是否显示裁剪矩形网格 圆形裁剪时建议设为false | true |
| clickSound | `Boolean` | - | 是否开启点击声音 | false |
| eggs | `Boolean` | - | 预览图片时 是否增强左右滑动图片体验 | false |
| quality | `Number` | - | 裁剪压缩质量 | 90 |
| compressSize | `Number` | - | 小于`compressSize`kb的图片不压缩 | 100 |
| sync | `Boolean` | - | 同步true或异步false 压缩 | true |
| cropWidth | `Number` | - | 裁剪宽高比，设置如果大于图片本身宽高则无效 | 0 |
| cropHeight | `Number` | - | 裁剪宽高比，设置如果大于图片本身宽高则无效 | 0 |
| rotate | `Boolean` | - | 裁剪是否可旋转图片 | true |
| scale | `Boolean` | - | 裁剪是否可放大缩小图片 | true |
| videoQuality | `Number` | - | 视频录制质量: `0`或`1` | 0 |
| videoMaxSecond | `Number` | - | 显示多少秒以内的视频or音频也可适用 | 15 |
| videoMinSecond | `Number` | - | 显示多少秒以内的视频or音频也可适用 | 10 |
| recordVideoSecond | `Number` | - | 视频录制秒数 | 60 |
| selected | `[{Object}]` | - | 已选图片，一般传入回调的`result.lists`数据 | - |

### callback 回调`result`说明

```js
{
    pageName: '页面名称',
    status: 'create',   //状态，详见：注①
    
    //status=success
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

- `create`页面创建完毕
- `destroy`页面已销毁
- `success`成功返回

### 简单示例

```js
//示例①：启动相册并拍照  
weiui_picture.create({
    gallery: 1
}, function(result) {
    //......
});

//示例②：单独启动拍照或视频 根据mimeType自动识别
weiui_picture.create({
    gallery: 1,
    type: 'camera'
}, function(result) {
    //......
});
```

