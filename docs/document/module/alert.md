# 确认对话框

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## alert 警告框
```js
/**
 * @param params    警告框内容 或 详细参数
 * @param callback  回调事件
 */
weiui.alert({params}, callback())
```

### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| title | `String` | - | 对话框标题 | - |
| message | `String` | - | 对话框内容 | - |
| button | `String` | - | 对话框按钮名称 | 确定 |
| cancelable | `Boolean` | - | 点击对话框以外的区域是否让对话框消失 | true |

### 简单示例

```js
//示例①
weiui.alert('你使用weiui了吗？', function() {
    //......
});

//示例②
weiui.alert({
    title: '温馨提示',
    message: '使用weiui真的很不错哦！',
}, function() {
    //......
});
```

## confirm 确认对话框
```js
/**
 * @param params    警告框内容 或 详细参数
 * @param callback  回调事件
 */
weiui.confirm({params}, callback(result))
```

### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| title | `String` | - | 对话框标题 | - |
| message | `String` | - | 对话框内容 | - |
| buttons | `Array` | - | 按钮参数，请参考`buttons参数说明` | - |
| cancelable | `Boolean` | - | 点击对话框以外的区域是否让对话框消失 | true |

### buttons 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| title | `String` | - | 按钮名称 | - |
| type | `String` | - | 按钮类型：<br/>`positive`：正面按钮，表示“积极”、“确认”的意思<br/>`negative`：反面按钮，表示“消极”、“取消”的意思<br/>`neutral`：中立按钮 | - |

### callback 回调`result`说明

```js
{
    status: 'click',    //状态：show-显示，cancel-消失，click-点击按钮
    
    //status=click
    position: 0,        //所点击的按钮的位置，从0开始
    title: '确定',       //点击的按钮名称
}
```

### 简单示例

```js
//示例①
weiui.confirm("你确定退出吗？", function(result) {
    if (result.status == "click" && result.title == "确定") {
        //......
    }
});

//示例②
weiui.confirm({
    title: "温馨提示",
    message: "你确定退出吗？",
    buttons: ["取消", "确定"]
}, function(result) {
    if (result.status == "click" && result.title == "确定") {
        //......
    }
});

//示例③
weiui.confirm({
    title: "温馨提示",
    message: "你确定退出吗？",
    buttons: [{
        title: "取消",
        type: "negative"
    }, {
        title: "确定",
        type: "positive"
    }, {
        title: "第三个按钮",
        type: "neutral"
    }]
}, function(result) {
    if (result.status == "click" && result.title == "确定") {
        //......
    }
});
```

## input 输入对话框
```js
/**
 * @param params    详细参数
 * @param callback  回调事件
 */
weiui.input({params}, callback(result))
```

### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| title | `String` | - | 对话框标题 | - |
| message | `String` | - | 对话框内容 | - |
| buttons | `Array` | - | 按钮参数，请参考confirm的`buttons参数说明` | - |
| inputs | `Array` | - | 输入参数，请参考`inputs参数说明` | - |
| cancelable | `Boolean` | - | 点击对话框以外的区域是否让对话框消失 | true |

### inputs 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| type | `String` | - | 输入框类型：<br/>`datetime`、`date`、`time`、<br/>`email`、`password`、`passnumber`、<br/>`tel`、`url`、`number` | text |
| value | `String` | - | 默认内容 | - |
| maxLength | `Number` | - | 最大输入长度 | - |
| placeholder | `String` | - | 提示信息 | - |
| singleLine | `Boolean` | - | 只允许单行输入 | - |
| autoFocus | `Boolean` | - | 自动获取焦点 | - |
| textSize | `String` | - | 字体大小 | - |
| textColor | `String` | - | 字体颜色 | - |
| backgroundColor | `String` | - | 背景颜色 | - |
| ems | `Number` | - | - | - |
| lines | `Number` | - | - | - |

### callback 回调`result`说明

```js
{
    status: 'click',    //状态：show-显示，cancel-消失，click-点击按钮
    
    //status=click
    data: ['第一个输入框内容', ....]
    position: 0,        //所点击的按钮的位置，从0开始
    title: '确定',       //点击的按钮名称
}
```

### 简单示例

```js
//示例
weiui.input({
    title: "输入昵称",
    buttons: ["取消", "确定"],
    inputs:[{
        type: 'text',
    }, ....]
}, function(result) {
    if (result.status == "click" && result.title == "确定") {
        //......
    }
});
```



