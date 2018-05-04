# weiui_side_panel_item

> 为组件`<weiui_side_panel>`的子组件，详细请看 [这里](component/weiui_side_panel)

## 子组件

支持任何组件作为自己的子组件。

## 示例代码

```vue
<weiui_side_panel_menu class="panel_menu" name="菜单1">
    <text>菜单①</text>
</weiui_side_panel_menu>
```

## 配置参数
>说明：子组件类型；数据格式：字符串。

| 属性名           | 类型     | 描述                          | 默认值     |
| ------------- | ------ | -------------------------- | ------- |
| name |`String`  | 菜单名称（用于父组件点击返回的标识）           | -       |

> 例如：

```vue
<weiui_side_panel_menu name="menu_1">
    .....
</weiui_side_panel_menu>
```
## 事件回调 `callback`

无

## 调用方法 `methods`

无



