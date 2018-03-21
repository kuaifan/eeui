#### 打开网络设置界面
```js
/**
* @参数一      固定值
 */
weiui.networkUtils('openWirelessSettings')
```

#### 判断网络是否连接
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.networkUtils('isConnected')
```

#### 判断网络是否可用
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.networkUtils('isAvailableByPing')
```

#### 判断移动数据是否打开
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.networkUtils('getMobileDataEnabled')
```

#### 打开或关闭移动数据
```js
/**
* @参数一      固定值
* @参数二      true:打开、false:关闭
 */
weiui.networkUtils('setMobileDataEnabled', enabled)
```

#### 判断网络是否是移动数据
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.networkUtils('isMobileData')
```

#### 判断网络是否是 4G
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.networkUtils('is4G')
```

#### 判断 wifi 是否打开
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.networkUtils('getWifiEnabled')
```

#### 打开或关闭 wifi
```js
/**
* @参数一      固定值
* @参数二      true:打开、false:关闭
 */
weiui.networkUtils('setWifiEnabled', enabled)
```

#### 判断 wifi 是否连接状态
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.networkUtils('isWifiConnected')
```

#### 判断 wifi 数据是否可用
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.networkUtils('isWifiAvailable')
```

#### 获取移动网络运营商名称
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.networkUtils('getNetworkOperatorName')
```

#### 获取当前网络类型
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.networkUtils('getNetworkType')
```

#### 获取 IP 地址
```js
/**
* @参数一      固定值
* @参数二      true:默认IPv4格式、false:原始格式
* 
* @返回 String
 */
let variable = weiui.networkUtils('getIPAddress', useIPv4)
```

#### 获取 IP 地址
```js
/**
* @参数一      固定值
* @参数二      域名地址
* 
* @返回 String
 */
let variable = weiui.networkUtils('getDomainAddress', domain)
```

