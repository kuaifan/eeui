#### 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

# weiui.ajax

> 跨域请求

* 支持自定义header
* 支持上传文件，选择文件请参考模块[pictureSelector](module/third/pictureSelector/install)

```js
/**
 * @param params    详细参数
 * @param callback  回调事件
 */
weiui.ajax({params}, callback(result))
```

#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| url | `String` | √ | 请求地址 | - |
| name | `String` | - | 请求名称，用于取消请求 | - |
| method | `String` | - | 请求类型，`get`、`post` | get |
| dataType | `String` | - | 返回数据类型，`json`、`text` | json |
| timeout | `Number` | - | 请求超时时间（单位：毫秒） | 15000 |
| cache | `Number` | - | 缓存时间，0不缓存（单位：毫秒） | 0 |
| headers | `Object` | - | 请求头部headers | - |
| data | `Object` | - | 发送数据 | - |
| files | `Object` | - | 提交文件  | - |


#### callback 回调`result`说明

```js
{
    status: 'success',      //状态，详见：注①
    name: 'requestName',    //请求名称
    url: 'http://....',     //请求地址
    cache: false,           //请求结果是否为缓存
    result: { .... },       //请求结果
}
```
###### 注①：
- `ready`就绪
- `success`请求成功
- `error`请求失败
- `complete`请求结束

流程：`ready` -> (`success` | `error`) -> `complete`

#### 简单示例

```js
//示例①
weiui.ajax({
    url: 'http://....'
}, function(result) {
    //......
});

//示例②
weiui.ajax({
    url: 'http://....',
    method: 'post',
    headers: {
        token: 'x2eefhjb2h3rj'
    },
    data: {
        username: 'weiui'
    },
    files: {
        userimg: '/storage/sdcard/.....'
    }
}, function(result) {
    //......
});
```

# weiui.ajaxClearCache

> 清除跨域请求缓存

```js
weiui.ajaxClearCache()
```

# weiui.ajaxCancel

> 取消跨域请求

```js
/**
 * @param name    请求名称（留空则取消所有请求）
 */
weiui.ajaxCancel(name)
```


