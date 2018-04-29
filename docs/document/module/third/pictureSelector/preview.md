#### 需要的模块

```js
const weiui_picture = weex.requireModule('weiui_picture');
```

# weiui_picture.picturePreview

> 预览图片。效果及示例代码请[点击这里查看](module/third/pictureSelector/install?id=预览效果)

```js
/**
 * @param position  焦点位置，从0开始
 * @param path      图片path组
 * @param callback  预览右上角删除选择事件，留空则关闭删除功能
 */
weiui_picture.picturePreview(position, [path], callback(result))
```

#### callback 回调`result`说明

```js
{
    position: 1,        //删除所在的位置，从0开始
}
```

# weiui_picture.videoPreview

> 预览视频。效果及示例代码请[点击这里查看](module/third/pictureSelector/install?id=预览效果)

```js
/**
 * @param path      视频地址
 */
weiui_picture.videoPreview(path)
```

