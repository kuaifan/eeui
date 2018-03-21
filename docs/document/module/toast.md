# weiui.toast

> 吐司(Toast)提示

```js
/**
 * @param params    详细参数
 */
weiui.toast({params})
```

#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| message | `String` | - | 提示文本 | - |
| gravity | `String` | - | 提示位置，`top`、`middle`、`bottom` | bottom |
| messageColor | `String` | - | 提示文本颜色代码 | #FFFFFF |
| backgroundColor | `String` | - | 提示背景颜色代码 | #FEFFFFFF |
| long | `Boolean` | - | 是否比较长时间显示 | false |

#### 简单示例

```js
//示例①
weiui.toast('请稍后...');

//示例②
weiui.toast({
    message: '请稍后...',
    gravity: 'middle'
});
```

# weiui.toastClose

> 吐司(Toast)隐藏

```js
weiui.toastClose()
```


