<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">滚动文字</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('component/weiui_scroll_text')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

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
                        color: '#6e0a92',
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

    .title {
        font-size: 28px;
        color: #ffffff
    }

    .iconr {
        width: 100px;
        color: #ffffff;
    }

    .scroll_text {
        width: 750px;
        margin-top: 50px;
        height: 60px;
    }
</style>

<script>
    import {openViewCode} from "../statics/js/app";

    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                scrollText: "这是一段滚动的文字，可以自定义调整速度的滚动文字~~~~~感谢你对weiui的支持！",
            }
        },
        methods: {
            viewCode(str) {
                openViewCode(str);
            },
            itemClick(params) {
                let starting = params.isStarting ? "运行中" : "停止了";
                weiui.toast("我被点击了，当前状态：" + starting);
            },
        }
    };
</script>
