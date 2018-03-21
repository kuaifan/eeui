#### 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

#### 获取屏幕的宽度（单位：屏幕像素）
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.screenUtils('getScreenWidth')
```

#### 获取屏幕的高度（单位：屏幕像素）
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.screenUtils('getScreenHeight')
```

#### 获取屏幕密度
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.screenUtils('getScreenDensity')
```

#### 获取屏幕密度 DPI
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.screenUtils('getScreenDensityDpi')
```

#### 设置屏幕为全屏
```js
/**
* @参数一      固定值
 */
weiui.screenUtils('setFullScreen')
```

#### 设置屏幕为横屏
```js
/**
* @参数一      固定值
 */
weiui.screenUtils('setLandscape')
```

#### 设置屏幕为竖屏
```js
/**
* @参数一      固定值
 */
weiui.screenUtils('setPortrait')
```

#### 判断是否横屏
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.screenUtils('isLandscape')
```

#### 判断是否竖屏
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.screenUtils('isPortrait')
```

#### 获取屏幕旋转角度
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.screenUtils('getScreenRotation')
```

#### 判断是否锁屏
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.screenUtils('isScreenLock')
```

#### 设置进入休眠时长
```js
/**
* @参数一      固定值
* @参数二      休眠时长
 */
weiui.screenUtils('setSleepDuration', duration)
```

#### 获取进入休眠时长
```js
/**
* @参数一      固定值
* 
* @返回 Number
 */
let variable = weiui.screenUtils('getSleepDuration')
```

#### 判断是否是平板
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.screenUtils('isTablet')
```

