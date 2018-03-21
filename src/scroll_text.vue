<template>
    <div class="app">

        <weiui_scroll_text
                ref="reflectName"
                class="scroll_text"
                :weiui="{
                        text: scrollText,
                        speed: 2,
                        fontSize: 16,
                    }"
                @itemClick="itemClick"
        ></weiui_scroll_text>

        <weiui_scroll_text
                ref="reflectName2"
                class="scroll_text"
                :weiui="{
                        text: scrollText,
                        speed: 10,
                        fontSize: 16,
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
                        fontSize: 12,
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
