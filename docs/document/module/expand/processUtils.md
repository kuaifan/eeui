# processUtils

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## 获取前台线程包名
```js
/**
* @参数一      固定值
* 
* @返回 String
 */
let variable = weiui.processUtils('getForegroundProcessName')
```

## 杀死所有的后台服务进程
```js
/**
* @参数一      固定值
 */
weiui.processUtils('killAllBackgroundProcesses')
```

## 杀死指定后台服务进程
```js
/**
* @参数一      固定值
* @参数二      应用包名
* 
* @返回 true|false
 */
let variable = weiui.processUtils('killBackgroundProcesses', packageName)
```



