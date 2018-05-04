# 文字图片分享

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## weiui.shareText

> 调用系统分享文字

```js
/**
 * @param text
 */
weiui.shareText(text)
```

### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| text | `String` | √ | 分享的文字内容 | - |


### 简单示例

```js
//示例
weiui.shareText("如果你喜欢 weiui，可以通过 Star 来表示，https://github.com/kuaifan/weiui");
```

## weiui.shareImage

> 调用系统分享图片

```js
/**
 * @param imgUrl
 */
weiui.shareImage(imgUrl)
```

### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| imgUrl | `String` | √ | 分享的图片地址 | - |


### 简单示例

```js
//示例
weiui.shareImage("http://kuaifan.vip/weiui/app/demo.png");
```

