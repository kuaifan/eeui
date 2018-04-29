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
| pageType | `String` | - | 页面类型：`weex`、`web`<br/>可填写`auto`系统自动识别 (不建议) | weex |
| params |`Object`  | - | 页面传递参数，通过`weex.config.params`获取         | -       |
| cache | `Number` | - | 页面缓存时间，仅`weex`类型有效，<br/>设置`0`不缓存（单位：毫秒） | 0 |
| loading | `Boolean` | - | 是否显示等待效果：`true`、`false` | true |
| swipeBack | `Boolean` | - | 是否支持滑动返回：`true`、`false` | true |
| statusBarType | `String` | - | 状态栏样式：<br/>正常: `normal`<br/>全屏: `fullscreen`<br/>沉浸式: `immersion`<br/>_非默认下`statusBarType`、`statusBarAlpha`无效_ | normal |
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
- `start`页面正在启动
- `resume`页面已在前台可见
- `pause`页面正在停止
- `stop`页面即将停止或者完全被覆盖
- `restart`页面正在重新启动
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

# weiui.setPageBackPressed

> 拦截返回按键事件

```js
/**
 * @param params    页面名称参数
 * @param callback  返回键触发事件
 */
weiui.setPageBackPressed({params}, callback())
```
#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| pageName | `String` | - | 页面名称，留空拦截当前页面（不建议） | - |

#### 简单示例

```js
//示例①
weiui.setPageBackPressed({
    pageName: 'pageName_1',
}, function(){
    //返回键触发事件
});

//示例②
weiui.setPageBackPressed('pageName_1', function(){
    //返回键触发事件
});
```

# weiui.setOnRefreshListener

> 仅对`Weex Js页面`有效，监听下拉刷新事件，下拉刷新事件结束后请使用`weiui.setRefreshing(pageName, false)`设置下拉刷新结束状态

```js
/**
 * @param params    页面名称参数
 * @param callback  下拉刷新回调事件
 */
weiui.setOnRefreshListener({params}, callback(pageName))
```
#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| pageName | `String` | - | 页面名称，留空拦截当前页面（不建议） | - |

#### 简单示例

```js
//示例①
weiui.setOnRefreshListener({
    pageName: 'pageName_1',
}, function(pageName){
    //下拉刷新回调事件
    weiui.setRefreshing(pageName, false);
});

//示例②
weiui.setOnRefreshListener('pageName_1', function(pageName){
    //下拉刷新回调事件
    weiui.setRefreshing(pageName, false);
});
```

# weiui.setRefreshing

> 仅对`Weex Js页面`有效，设置下拉刷新状态，主要用于`weiui.setOnRefreshListener`回调处理完成后设置结束状态

```js
/**
 * @param params        页面名称参数
 * @param refreshing    状态：true|fals
 */
weiui.setRefreshing({params}, refreshing)
```
#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| pageName | `String` | - | 页面名称，留空拦截当前页面（不建议） | - |


# weiui.setPageStatusListener

> 添加监听页面状态变化

```js
/**
 * @param name    监听名称，用于防止重复监听
 * @param callback  回调事件，参数详见【weiui.openPage】的回调事件（留空取消监听）
 */
weiui.setPageStatusListener(name, callback(result))
```
#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| name | `String` | √ | 监听名称，用于防止重复监听 | - |

#### 简单示例

```js
//示例
weiui.setPageStatusListener('name_1', function(pageName){
    //回调事件，参数详见【weiui.openPage】的回调事件
});
```

# weiui.getCacheSizePage

> 获取`Weex Js页面`缓存大小

```js
/**
 * @param callback  回调事件，{size:123123}，单位：字节B
 */
weiui.getCacheSizePage(callback(result))
```

# weiui.clearCachePage

> 手动清除缓存`Weex Js页面`

```js
weiui.clearCachePage()
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
weiui.closePage('pageName_1');
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

# weiui.goDesktop

> 返回手机桌面

```js
/**
 * @param url
 */
weiui.goDesktop()
```


