#### 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

# weiui.openPage

> 打开新`Weex Js页面` 或 打开新`Web页面`

```js
/**
 * @param params    详细参数
 * @param callback  回调事件
 */
weiui.openPage({params}, callback(result))
```

#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| url | `String` | √ | `Weex Js`或`Web Url`地址 | - |
| pageName | `String` | - | 页面名称 | - |
| pageType | `String` | - | 页面类型，`weex`、`web` | weex |
| loading | `Boolean` | - | 是否显示等待效果，`true`、`false` | true |
| swipeBack | `Boolean` | - | 是否支持滑动返回，`true`、`false` | true |
| statusBarType | `String` | - | 状态栏样式：<br/>全屏: `fullscreen`<br/>沉浸式: `immersion`<br/>设置时`statusBarType`、`statusBarAlpha`无效 | - |
| statusBarColor | `String` | - | 状态栏颜色值 | #3EB4FF |
| statusBarAlpha | `Number` | - | 状态栏透明度， 0-255 | 0 |
| backgroundColor | `String` | - | 页面背景颜色 | #f4f8f9 |
| backPressedClose | `Boolean` | - | 允许按返回键关闭页面 | true |

#### callback 回调`result`说明

```js
{
    pageName: '页面名称',
    status: 'create',   //状态，详见：注①
    
    //status=statusChanged|errorChanged|titleChanged
    webStatus: '',  //Web状态
    
    //status=errorChanged
    errCode: '',    //错误代码
    errMsg: '',     //错误描述
    errUrl: '',     //错误地址
    
    //status=titleChanged
    title: '',      //网页标题
}
```
###### 注①：
- `create`页面创建完毕
- `destroy`页面已销毁
- `viewCreated`WeexJS第一个视图的呈现完成
- `renderSuccess`WeexJS呈现视图阶段结束
- `error`WeexJS运行时报告异常
- `statusChanged`Web状态发生改变
- `errorChanged`Web运行时报告异常
- `titleChanged`Web标题发生改变

#### 简单示例

```js
const weiui = weex.requireModule('weiui');
//示例①
weiui.openPage({
    url: 'http://dotwe.org/raw/dist/ad0045a7cff0b3a680d9de6dd4806e81.bundle.wx',
}, function(result) {
    //......
});

//示例②
weiui.openPage({
    pageName: 'pageName_1',
    pageType: 'weex',
    url: 'http://dotwe.org/raw/dist/ad0045a7cff0b3a680d9de6dd4806e81.bundle.wx'
}, function(result) {
    //......
});

//示例③
weiui.openPage({
    pageType: 'web',
    url: 'http://kuaifan.vip'
}, function(result) {
    //......
});
```

# weiui.getPageInfo

> 获取页面信息

```js
/**
 * @param params    详细参数
 */
weiui.getPageInfo({params})
```

#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| pageName | `String` | - | 页面名称，留空获取当前页面（不建议） | - |

#### 简单示例

```js
//示例①
let variable = weiui.getPageInfo({
    pageName: 'pageName_1',
});

//示例②
let variable = weiui.getPageInfo('pageName_1');

//variable返回示例
{
　　"backgroundColor":"#f4f8f9",
　　"pageType":"weex",
　　"swipeBack":true,
　　"statusBarColor":"#3EB4FF",
　　"backPressedClose":true,
　　"statusBarAlpha":0,
　　"loading":true,
　　"statusBarType":"default",
　　"pageName":"open_qGRQ9fHP",
　　"url":"http://....../dist/index.js"
}
```

# weiui.reloadPage

> 重新加载`Weex Js页面` 或 `Web页面`

```js
/**
 * @param params    详细参数
 */
weiui.reloadPage({params})
```

#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| pageName | `String` | - | 页面名称，留空重载当前页面（不建议） | - |

#### 简单示例

```js
//示例①
weiui.reloadPage({
    pageName: 'pageName_1',
});

//示例②
weiui.reloadPage('pageName_1');
```

# weiui.closePage

> 关闭`Weex Js页面` 或 `Web页面`

```js
/**
 * @param params    详细参数
 */
weiui.closePage({params})
```

#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| pageName | `String` | - | 页面名称，留空关闭当前页面（不建议） | - |

#### 简单示例

```js
//示例①
weiui.closePage({
    pageName: 'pageName_1',
});

//示例②
weiui.openPage('pageName_1');
```

# weiui.openWeb

> 调用系统浏览器打开页面

```js
/**
 * @param url
 */
weiui.openWeb(url)
```
#### url 参数说明

| 类型 | 必须 | 描述 | 默认值 |
| --- | :-: | --- | --- |
|  `String` | √ | 打开的页面url | - |


#### 简单示例

```js
weiui.openWeb('http://kuaifan.vip');
```


