#### 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

#### 卸载 App
```js
/**
* @参数一      固定值
* @参数二      应用包名
 */
weiui.appUtils('uninstallApp', packageName)
```

#### 静默卸载 App
```js
/**
* @参数一      固定值
* @参数二      应用包名
 */
weiui.appUtils('uninstallAppSilent', packageName)
```

#### 判断 App 是否安装
```js
/**
* @参数一      固定
* @参数二      应用包名
* 
* @返回 true|false
 */
let variable = weiui.appUtils('isAppInstalled', packageName)
```

#### 判断 App 是否有 root 权限
```js
/**
* @参数一      固定
* 
* @返回 true|false
 */
let variable = weiui.appUtils('isAppRoot')
```

#### 打开 App
```js
/**
* @参数一      固定值
* @参数二      应用包名
 */
weiui.appUtils('launchApp', packageName)
```

#### 关闭应用
```js
/**
* @参数一      固定值
 */
weiui.appUtils('exitApp')
```

#### 获取 App 包名
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.appUtils('getAppPackageName')
```

#### 获取 App 名称
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.appUtils('getAppName')
```

#### 获取 App 路径
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.appUtils('getAppPath')
```

#### 获取 App 版本号
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.appUtils('getAppVersionName')
```

#### 获取 App 版本码
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.appUtils('getAppVersionCode')
```

#### 判断 App 是否是系统应用
```js
/**
* @参数一      固定值
* @参数二      应用包名
* 
* @返回 true|false
 */
let variable = weiui.appUtils('isAppSystem', packageName)
```

#### 获取应用签名的的 SHA1 值
```js
/**
* @参数一      固定值
* @参数二      应用包名
* 
* @返回 String
 */
let variable = weiui.appUtils('getAppSignatureSHA1', packageName)
```

#### 判断 App 是否处于前台
```js
/**
* @参数一      固定值
* @参数二      应用包名
* 
* @返回 true|false
 */
let variable = weiui.appUtils('isAppForeground', packageName)
```



