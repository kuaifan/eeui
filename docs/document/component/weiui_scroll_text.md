# weiui_scroll_text

> `<weiui_scroll_text>` 是一个横向滚动文字的容器，主要用于单行公告。

## 子组件

无

## 预览效果

> 说明：gif图片压缩，原效果非常流畅。

![](media/ezgif-5-0c1dca77a0.gif)

## 示例代码

```vue
<template>
    <div class="app">

        <weiui_scroll_text
                ref="reflectName"
                class="scroll_text"
                :weiui="{
                        text: scrollText,
                        speed: 2,
                        fontSize: 32,
                    }"
                @itemClick="itemClick"
        ></weiui_scroll_text>

        <weiui_scroll_text
                ref="reflectName2"
                class="scroll_text"
                :weiui="{
                        text: scrollText,
                        speed: 10,
                        fontSize: 32,
                        color: '#ff0000',
                        backgroundColor: '#00ffff'
                    }"
                @itemClick="itemClick"
        ></weiui_scroll_text>

        <weiui_scroll_text
                ref="reflectName3"
                class="scroll_text"
                :weiui="{
                        text: scrollText,
                        speed: 5,
                        fontSize: 24,
                        color: '#6dff28',
                        backgroundColor: '#c8e7ff'
                    }"
                @itemClick="itemClick"
        ></weiui_scroll_text>

    </div>
</template>

<style scoped>
    .app {
        width: 750px;
        flex: 1;
    }
    .scroll_text {
        width: 750px;
        height: 80px;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                scrollText: "这是一段滚动的文字，可以自定义调整速度的滚动文字~~~~~感谢你对weiui的支持！",
            }
        },
        methods: {
            itemClick(params) {
                let starting = params.isStarting ? "运行中": "停止了";
                weiui.toast("我被点击了，当前状态：" + starting);
            },
        }
    };
</script>
```


## 配置参数 `weiui`
>说明：ui自定义；数据格式：对象数据。

| 属性名           | 类型     | 描述                          | 默认值     |
| ------------- | ------ | -------------------------- | ------- |
| content |`String`  | 滚动的文字           | -       |
| text |`String`  | 同`content`           | -       |
| speed |`Float`  | 滚动的速度           | 2       |
| fontSize |`Number`  | 字体大小           | 24       |
| color |`String`  | 滚动的文字颜色           | #000000       |
| backgroundColor |`String`  | 滚动的文字背景颜色           | #00ffffff       |

> 例如：

```vue
<weiui_scroll_text
    ref="reflectName"
    :weiui="{
        text: '滚动的文字',
        speed: 5,
        fontSize: 24,
        color: '#6dff28',
        backgroundColor: '#c8e7ff'
    }"></weiui_scroll_text>
```

## 事件回调 `callback`

``` js
/**
 * 组件加载完成
 */
@ready = function() { ... }

/**
 * 项目点击事件
 * 返回参数：data = {isStarting: false}
 */
@itemClick = function(data) { ... }
```

## 调用方法 `methods`

```js
/**
 * 设置滚动的文字
 * 参数一：滚动文字
 */
this.$refs.reflectName.setText('滚动文字');

/**
 * 添加滚动的文字
 * 参数一：添加的滚动文字
 */
this.$refs.reflectName.addText('滚动文字');

/**
 * 设置为滚动状态
 */
this.$refs.reflectName.startScroll();

/**
 * 设置为停止滚动状态
 */
this.$refs.reflectName.stopScroll();

/**
 * 获取是否在滚动状态
 * 返回参数：true|false
 */
 this.$refs.reflectName.isStarting();

/**
 * 设置文字滚动速度
 * 参数一：滚动速度
 */
this.$refs.reflectName.setSpeed(3);

/**
 * 设置滚动字体大小
 * 参数一：文字大小
 */
this.$refs.reflectName.setTextSize(16);

/**
 * 设置滚动字体颜色
 * 参数一：颜色代码
 */
this.$refs.reflectName.setTextColor('#ff0000');

/**
 * 设置滚动字体背景颜色
 * 参数一：颜色代码
 */
this.$refs.reflectName.setBackgroundColor('#0000ff');
```


