#### 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

#### 复制文本到剪贴板
```js
/**
 * @param string
 */
weiui.copyText(string)
```


#### 获取剪贴板的文本
```js
/**
 * @return string
 */
let variable = weiui.pasteText()
```


