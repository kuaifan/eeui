# weiui_marquee

> `<weiui_marquee>` 是一个横向自动滚动文字的容器，主要用于文字多单地方小的地方，比如单行标题。

## 子组件

无

## 预览效果

> 说明：gif图片压缩，原效果非常流畅。

![](media/ezgif-4-a40b01a11e.gif)

## 示例代码

```vue
<template>
    <div class="app">

        <weiui_marquee
                ref="reflectName"
                class="marquee"
                style="width:750px"
                :weiui="{
                        text: scrollText,
                        fontSize: 24,
                    }"
        ></weiui_marquee>

        <weiui_marquee
                ref="reflectName2"
                class="marquee"
                style="width:375px"
                :weiui="{
                        text: scrollText,
                        fontSize: 24,
                        color: '#ff0000',
                        backgroundColor: '#00ffff'
                    }"
        ></weiui_marquee>

        <weiui_marquee
                ref="reflectName3"
                class="marquee"
                style="width:200px"
                :weiui="{
                        text: scrollText,
                        fontSize: 24,
                        color: '#6e0a92',
                        backgroundColor: '#c8e7ff'
                    }"
        ></weiui_marquee>


    </div>
</template>

<style scoped>
    .app {
        width: 750px;
        flex: 1;
    }
    .marquee {
        margin-top: 50px;
        height: 60px;
        background-color: #00B4FF;
    }
</style>

<script>
    export default {
        data() {
            return {
                scrollText: "地方不够放时才滚动！",
            }
        }
    };
</script>
```


## 配置参数 `weiui`
>说明：ui自定义；数据格式：对象数据。

| 属性名           | 类型     | 描述                          | 默认值     |
| ------------- | ------ | -------------------------- | ------- |
| content |`String`  | 显示的文字           | -       |
| text  |`String`  | 同`content`            | -       |
| color |`String`  | 滚动的文字颜色           | #000000       |
| fontSize |`Number`  | 字体大小           | 24       |
| textAlign |`String`  | 对齐方式:`left` `center` `right`           | left       |
| backgroundColor |`String`  | 滚动的文字背景颜色           | #00ffffff       |

> 例如：

```vue
<weiui_marquee
    ref="reflectName"
    :weiui="{
        text: '地方不够放时才滚动',
        fontSize: 24,
        color: '#6dff28',
        backgroundColor: '#c8e7ff'
    }"></weiui_marquee>
```

## 事件回调 `callback`

``` js
/**
 * 组件加载完成
 */
@ready = function() { ... }
```

## 调用方法 `methods`

```js
/**
 * 设置显示的文字
 * 参数一：滚动文字
 */
this.$refs.reflectName.setText('文字');

/**
 * 添加显示的文字
 * 参数一：添加的显示文字
 */
this.$refs.reflectName.addText('文字');

/**
 * 设置字体大小
 * 参数一：文字大小
 */
this.$refs.reflectName.setTextSize(24);

/**
 * 设置字体颜色
 * 参数一：颜色代码
 */
this.$refs.reflectName.setTextColor('#ff0000');

/**
 * 设置字体背景颜色
 * 参数一：颜色代码
 */
this.$refs.reflectName.setBackgroundColor('#0000ff');
```


