#### 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

#### 判断设备是否 rooted
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.deviceUtils('isDeviceRooted')
```

#### 获取设备系统版本号
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.deviceUtils('getSDKVersionCode')
```

#### 获取设备系统版本名称
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.deviceUtils('getSDKVersionName')
```

#### 获取设备 AndroidID
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.deviceUtils('getAndroidID')
```

#### 获取设备 MAC 地址
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.deviceUtils('getMacAddress')
```

#### 获取设备厂商
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.deviceUtils('getManufacturer')
```

#### 获取设备型号
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.deviceUtils('getModel')
```

#### 关机
```js
/**
* @参数一      固定值
 */
weiui.deviceUtils('shutdown')
```

#### 重启
```js
/**
* @参数一      固定值
 */
weiui.deviceUtils('reboot')
```

#### 重启到 recovery
```js
/**
* @参数一      固定值
 */
weiui.deviceUtils('reboot2Recovery')
```

#### 重启到 bootloader
```js
/**
* @参数一      固定值
 */
weiui.deviceUtils('reboot2Bootloader')
```


