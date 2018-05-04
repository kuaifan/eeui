# 监听通知

> 需要的模块

```js
const weiui_umeng = weex.requireModule('weiui_umeng');
```

## weiui_umeng.getToken

> 获取友盟token

```js
/**
* @返回 友盟token
 */
let variable = weiui_umeng.getToken()
``` 

## weiui_umeng.setNotificationClickHandler

> 自定义通知打开动作（点击通知事件）

```js
/**
 * @param callback  回调事件
 */
weiui_umeng.setNotificationClickHandler(callback(result))
```

### callback 回调`result`说明

```js
{
　　"status":"click",         //为 “click” 时就是点击通知的动作了
　　 
　　"display_type":"notification",
　　"msg_id":"uurztln152223767161010",
　　"body":{
　　　　"after_open":"go_app",
　　　　"play_lights":"false",
　　　　"ticker":"测试标题1",
　　　　"play_vibrate":"false",
　　　　"text":"测试描述22222",
　　　　"title":"测试标题1",
　　　　"play_sound":"true"
　　},
　　"random_min":0,
}
```

### 简单示例

```js
weiui_umeng.setNotificationClickHandler(function(result) {
    //......
});
```

