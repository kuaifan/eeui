# 监听通知

> 需要的模块

```js
const weiui_rongim = weex.requireModule('weiui_rongim');
```

## weiui_rongim.login

> 连接登录

```js
/**
 * @param params    详细参数
 * @param callback  回调事件
 */
weiui_rongim.login({params}, callback(result))
``` 

### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| userid | `String` | √ | 会员ID | - |
| username | `String` | - | 会员昵称 | - |
| userimg | `String` | - | 会员头像地址 | - |

### callback 回调`result`说明

```js
{
    status: 'success',   //状态，success | error
    
    userid: 18888888888,        //【限：status=success】会员ID
    token: "sdf2jkehjs23...",   //【限：status=success】融云会员身份识别码
    
    errorCode: -801,
    errorMsg: "错误描述",
}
```

## weiui_rongim.logout

> 退出登录，断开与融云服务器的连接，并且不再接收 Push 消息。

```js
weiui_rongim.logout()
```


