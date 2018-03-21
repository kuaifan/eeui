#### 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

# weiui.loading

> 显示等待弹窗

```js
/**
 * @param params    详细参数
 * @param callback  点击空白处或返回键关闭回调事件
 * 
 * @return String 返回loading的名称，可通过此名称关闭指定的等待弹窗
 */
let loaddingName = weiui.loading({params}, callback())
```

#### params 参数说明

| 属性名 | 类型 | 必须 | 描述 | 默认值 |
| --- | --- | :-: | --- | --- |
| title | `String` | - | 等待描述 | - |
| titleSize | `Number` | - | 等待描述字体大小 | 16 |
| titleColor | `String` | - | 等待描述字体颜色 | - |
| cancelable | `Boolean` | - | 是否允许点击空白处或返回键关闭 | true |
| duration | `Number` | - | 自动关闭时间，0取消自动关闭 | 0 |
| style | `String` | - | loading样式，详见：注① | - |
| styleColor | `String` | - | loading样式颜色 | - |
| amount | `Float ` | - | 背景透明程度，取值：0-1 | - |

###### 注①

Style | Preview
------------     |   -------------
RotatingPlane    | <img src='document/module/media/RotatingPlane.gif' alt='RotatingPlane' width="60px" height="60px"/>
DoubleBounce     | <img src='document/module/media/DoubleBounce.gif' alt='DoubleBounce' width="60px" height="60px"/>
Wave             | <img src='document/module/media/Wave.gif' alt='Wave' width="60px" height="60px"/>
WanderingCubes   | <img src='document/module/media/WanderingCubes.gif' alt='WanderingCubes' width="60px" height="60px"/>
Pulse            | <img src='document/module/media/Pulse.gif' alt='Pulse' width="60px" height="60px"/>
ChasingDots      | <img src='document/module/media/ChasingDots.gif' alt='ChasingDots' width="60px" height="60px"/>
ThreeBounce      | <img src='document/module/media/ThreeBounce.gif' alt='ThreeBounce' width="60px" height="60px"/>
Circle           | <img src='document/module/media/Circle.gif' alt='Circle' width="60px" height="60px"/>
CubeGrid         | <img src='document/module/media/CubeGrid.gif' alt='CubeGrid' width="60px" height="60px"/>
FadingCircle     | <img src='document/module/media/FadingCircle.gif' alt='FadingCircle' width="60px" height="60px"/>
FoldingCube      | <img src='document/module/media/FoldingCube.gif' alt='FoldingCube' width="60px" height="60px"/>
RotatingCircle   | <img src='document/module/media/RotatingCircle.gif' alt='RotatingCircle' width="60px" height="60px"/>

#### callback 回调说明

* 返回键或点击空白处取消回调事件

#### 简单示例

```js
//示例①
weiui.loading();

//示例②
weiui.loading({
    style: 'RotatingPlane',
    duration: 5000
});
```

# weiui.loadingClose

> 关闭等待弹窗

```js
//示例①
weiui.loadingClose();

//示例②
weiui.loadingClose(loaddingName);
```

#### loaddingName 参数说明

* 关闭指定名称的弹窗，留空则关闭正在显示的第一个弹窗


