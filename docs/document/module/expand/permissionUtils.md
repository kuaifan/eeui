# permissionUtils

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## 获取应用权限
```js
/**
* @参数一      固定值
* 
* @返回 JSON
 */
let variable = weiui.permissionUtils('getPermissions')
```

## 判断权限是否被授予
```js
/**
* @参数一      固定值
* @参数二      权限请名称，如：android.permission-group.CAMERA
* 
* @返回 true|false
 */
let variable = weiui.permissionUtils('isGranted', permissions)
```

## 打开应用详情设置
```js
/**
* @参数一      固定值
 */
weiui.permissionUtils('launchAppDetailsSettings')
```


