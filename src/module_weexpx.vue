<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">像素转换</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/weexpx')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">

            <text class="subtitle">weex px转屏幕像素</text>

            <div class="inBox">
                <input class="inPut" v-model="px2dpA"/>
                <text style="font-size:24px">=</text>
                <input class="inPut" v-model="px2dpB"/>
            </div>

            <text class="button" @click="weexPx2dp()">转换</text>


            <text class="subtitle">屏幕像素转weex px</text>

            <div class="inBox">
                <input class="inPut" v-model="dp2pxA"/>
                <text style="font-size:24px">=</text>
                <input class="inPut" v-model="dp2pxB"/>
            </div>

            <text class="button" @click="weexDp2px()">转换</text>

        </div>

    </div>
</template>

<style>
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

    .content {
        flex: 1;
        align-items: center;
    }

    .subtitle {
        padding-top: 56px;
        padding-right: 24px;
        padding-bottom: 24px;
        padding-left: 24px;
        font-size: 28px;
        color: #757575;
    }

    .button {
        width: 276px;
        font-size: 24px;
        text-align: center;
        margin-top: 20px;
        margin-bottom: 20px;
        padding-top: 26px;
        padding-bottom: 26px;
        padding-left: 30px;
        padding-right: 30px;
        color: #ffffff;
        background-color: #00B4FF;
    }

    .inBox {
        flex-direction: row;
        justify-content: center;
        align-items: center;
    }

    .inPut {
        width: 120px;
        height: 60px;
        font-size: 26px;
        text-align: center;
        margin-left: 10px;
        margin-right: 10px;
        background-color: #e4f5ff;
    }
</style>

<script>
    import {openViewCode} from "../statics/js/app";

    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                px2dpA: '',
                px2dpB: '',
                dp2pxA: '',
                dp2pxB: '',
            }
        },
        methods: {
            viewCode(str) {
                openViewCode(str);
            },

            weexPx2dp() {
                this.px2dpB = weiui.weexPx2dp(this.px2dpA);
            },

            weexDp2px() {
                this.dp2pxB = weiui.weexDp2px(this.dp2pxA);
            },
        }
    };
</script>
