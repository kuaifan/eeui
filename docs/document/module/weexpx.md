# px单位转换

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## weex px转屏幕像素
```js
/**
 * @param var       weex px
 * 
 * @return int
 */
let variable = weiui.weexPx2dp(var)
```

## 屏幕像素转weex px
```js
/**
 * @param var       屏幕像素
 * 
 * @return int
 */
let variable = weiui.weexDp2px(var)
```

