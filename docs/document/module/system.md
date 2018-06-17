# 系统信息

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## 获取状态栏高度（屏幕像素）
```js
/**
 * @return int
 */
let variable = weiui.getStatusBarHeight()
```

## 获取状态栏高度（weex px单位）
```js
/**
 * @return int
 */
let variable = weiui.getStatusBarHeightPx()
```

## 获取虚拟键盘高度（屏幕像素）
```js
/**
 * @return int
 */
let variable = weiui.getNavigationBarHeight()
```

## 获取虚拟键盘高度（weex px单位）
```js
/**
 * @return int
 */
let variable = weiui.getNavigationBarHeightPx()
```

## 获取weiui版本号 <font color="red">[新]</font>
```js
/**
 * @return int
 */
let variable = weiui.getVersion()
```

## 获取weiui版本号名称 <font color="red">[新]</font>
```js
/**
 * @return String
 */
let variable = weiui.getVersionName()
```

## 获取本地软件版本号
```js
/**
 * @return int
 */
let variable = weiui.getLocalVersion()
```

## 获取本地软件版本号名称
```js
/**
 * @return String
 */
let variable = weiui.getLocalVersionName()
```

## 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
```js
/**
 * @param version1      比较的版本1
 * @param version2      比较的版本2
 * 
 * @return int
 */
let variable = weiui.compareVersion(version1, version2)
```

## 获取手机的IMEI
```js
/**
 * @return String
 */
let variable = weiui.getImei()
```

## 获取设备系统版本号
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.getSDKVersionCode()
```

## 获取设备系统版本名称
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.getSDKVersionName()
```


