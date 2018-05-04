# weiui_ripple

> `<weiui_ripple>` 是一个点击元素时，会产生向外扩散的水波纹效果容器，支持写法`<ripple>`。

## 子组件

`<ripple>` 基本容器组件，因此支持包括 `<div>` 在内的任何组件作为自己的子组件。因此，在写一个组件时，推荐外层使用 `<div>` 作为根容器。

## 事件回调 `callback`

``` js
/**
 * 组件加载完成
 */
@ready = function() { ... }

/**
 * 点击事件
 */
@itemClick = function(data) { ... }
```

> 注意： click 事件的回调函数和 itemClick 点击事件的执行顺序未被定义。不要使用 click 来进行 itemClick 点击前的逻辑处理。

