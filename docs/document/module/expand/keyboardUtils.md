# keyboardUtils

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## 动态显示软键盘
```js
/**
* @参数一      固定值
 */
weiui.keyboardUtils('showSoftInput')
```

## 动态隐藏软键盘
```js
/**
* @参数一      固定值
 */
weiui.keyboardUtils('hideSoftInput')
```

## 切换键盘显示与否状态
```js
/**
* @参数一      固定值
 */
weiui.keyboardUtils('toggleSoftInput')
```

## 判断软键盘是否可见
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.keyboardUtils('isSoftInputVisible')
```