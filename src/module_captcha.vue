<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">弹窗验证</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/captcha')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">
            <text class="button" @click="swipeCaptcha">开始验证</text>
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
        justify-content: center;
        align-items: center;
    }

    .button {
        font-size: 24px;
        text-align: center;
        margin-top: 20px;
        padding-top: 20px;
        padding-bottom: 20px;
        padding-left: 30px;
        padding-right: 30px;
        color: #ffffff;
        background-color: #00B4FF;
    }
</style>

<script>
    import {openViewCode} from "../statics/js/app";

    const weiui = weex.requireModule('weiui');

    export default {
        methods: {
            viewCode(str) {
                openViewCode(str);
            },
            swipeCaptcha() {
                weiui.swipeCaptcha(null, (res) => {
                    switch (res.status) {
                        case "success":
                            weiui.toast("验证成功");
                            break;

                        case "failed":
                            weiui.toast("验证失败");
                            break;
                    }
                });
            },
        }
    };
</script>
