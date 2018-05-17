# 监听通知

> 需要的模块

```js
const weiui_rongim = weex.requireModule('weiui_rongim');
```

## weiui_rongim.joinChatRoom

* 加入聊天室。如果聊天室不存在则自动创建

```js
/**
 * @param roomId            聊天室ID，如果聊天室不存在则自动创建
 * @param defMessageCount   默认开始时拉取的历史记录条数
 * @param callback          回调事件
 */
weiui_rongim.joinChatRoom(roomId, defMessageCount, callback(result))
``` 

> callback 回调`result`说明

```js
{
    status: 'success',   //状态，success | error
    
    errorCode: -801,
    errorMsg: "错误描述",
}
```

## weiui_rongim.quitChatRoom

* 退出当前聊天室，不在接收其消息

```js
/**
 * @param callback          回调事件
 */
weiui_rongim.quitChatRoom(callback(result))
``` 

> callback 回调`result`说明

```js
{
    status: 'success',   //状态，success | error
    
    errorCode: -801,
    errorMsg: "错误描述",
}
```

## weiui_rongim.addEventHandler

* 添加接收者（监听聊天室消息）

```js
/**
 * @param callback          回调事件
 */
weiui_rongim.addEventHandler(callback(result))
``` 

> callback 回调`result`说明

```js
{
    status: 'arrived',   //状态（消息类型），详见：注①
    
    userid: "",     //登录时的会员ID
    username: "",   //登录时的会员昵称
    userimg: "",    //登录时的会员头像地址
    body: "",       //消息正文内容
    extra: "",      //消息附加内容
}
```

> 注①

* arrived：收到新消息
* send：发送消息成功
* send_error：发送消息失败


## weiui_rongim.removeEventHandler

* 移除接收者（取消监听聊天室消息）

```js
weiui_rongim.removeEventHandler()
``` 

## weiui_rongim.sendTextMessage

* 当前聊天室发送文本消息

```js
/**
 * @param text      发送的文本内容
 * @param callback  回调事件
 */
weiui_rongim.sendTextMessage(rtext, callback(result))
``` 

> callback 回调`result`说明

```js
{
    status: 'success',   //状态，success|error
}
```