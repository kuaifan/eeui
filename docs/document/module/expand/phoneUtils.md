# phoneUtils

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## 判断设备是否是手机
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.phoneUtils('isPhone')
```

## 获取设备唯一ID
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.phoneUtils('getDeviceId')
```

## 获取 IMEI 码
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.phoneUtils('getIMEI')
```

## 获取 MEID 码
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.phoneUtils('getMEID')
```

## 获取 IMSI 码
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.phoneUtils('getIMSI')
```

## 获取移动终端类型
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.phoneUtils('getPhoneType')
```

## 判断 sim 卡是否准备好
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.phoneUtils('isSimCardReady')
```

## 获取 Sim 卡运营商名称
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.phoneUtils('getSimOperatorName')
```

## 获取 Sim 卡运营商名称（使用mnc）
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.phoneUtils('getSimOperatorByMnc')
```

## 获取手机状态信息
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.phoneUtils('getPhoneStatus')
```

## 跳至拨号界面
```js
/**
* @参数一      固定值
* @参数二      电话号码
 */
weiui.phoneUtils('dial', phoneNumber)
```

## 直接拨打电话
```js
/**
* @参数一      固定值
* @参数二      电话号码
 */
weiui.phoneUtils('call', phoneNumber)
```

## 跳至发送短信界面
```js
/**
* @参数一      固定值
* @参数二      电话号码
* @参数三      短信内容
 */
weiui.phoneUtils('sendSms', phoneNumber, content)
```

## 直接发送短信
```js
/**
* @参数一      固定值
* @参数二      电话号码
* @参数三      短信内容
 */
weiui.phoneUtils('sendSmsSilent', phoneNumber, content)
```

