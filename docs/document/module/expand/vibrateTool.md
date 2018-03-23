#### 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

#### 开始震动
```js
/**
* @参数一      固定值
* @参数二      震动的时间，毫秒
 */
weiui.vibrateTool('vibrateOnce', millisecond)
```

#### 停止震动
```js
/**
* @参数一      固定值
 */
weiui.vibrateTool('vibrateStop')
```

