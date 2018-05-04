# weiui_navbar_item

> 为组件`<weiui_navbar>`的子组件，详细请看 [这里](component/weiui_navbar)

## 子组件

支持任何组件作为自己的子组件（除配置参数`type="back"`不支持以外）。

## 示例代码

```vue
<weiui_navbar_item type="title">
    <text>标题</text>
</weiui_navbar_item>
```

## 配置参数`type`
>说明：子组件类型；数据格式：字符串。

| 类型     | 描述                          | 默认值     |
| ------ | -------------------------- | ------- |
|`String`  | 所属类型 (`back`/`left`/`title`/`right`)           | -       |

注意：`type='back'`类型不支持子组件
> 例如：

```vue
<weiui_navbar_item type="title">
    .....
</weiui_navbar_item>
```

```vue
<weiui_navbar_item type="back"></weiui_navbar_item>
```
## 事件回调 `callback`

无

## 调用方法 `methods`

无



