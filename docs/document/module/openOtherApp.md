# 打开其他APP

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## weiui.openOtherApp

> 打开常用第三方APP

```js
/**
 * @param type    APP
 */
weiui.openOtherApp(type)
```

### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| type | `String` | √ | 要打开的APP：<br/>`wx`、`qq`、`alipay`、`jd` | - |


### 简单示例

```js
//示例
weiui.openOtherApp("wx");
```

