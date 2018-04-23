<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">提示消息</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/toast')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">
            <text class="button" @click="toast1">普通的toast</text>
            <text class="button" @click="toast2">红色的toast</text>
            <text class="button" @click="toast3">背景红色的toast</text>
            <text class="button" @click="toast4">显示较长的toast</text>
            <text class="button" @click="toast5">顶部的toast</text>
            <text class="button" @click="toast6">中间的toast</text>
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
        width: 380px;
        font-size: 24px;
        text-align: center;
        margin-top: 15px;
        margin-bottom: 15px;
        padding-top: 26px;
        padding-bottom: 26px;
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
            toast1() {
                weiui.toast("普通的toast");
            },
            toast2() {
                weiui.toast({
                    message: '红色的toast',
                    messageColor: '#ff0000'
                });
            },
            toast3() {
                weiui.toast({
                    message: '背景红色的toast',
                    messageColor: '#ffffff',
                    backgroundColor: '#ff0000'
                });
            },
            toast4() {
                weiui.toast({
                    message: '时间较长的toast',
                    long: true
                });
            },
            toast5() {
                weiui.toast({
                    message: '顶部的toast',
                    gravity: 'top',
                    y: 50
                });
            },
            toast6() {
                weiui.toast({
                    message: '中间的toast',
                    gravity: 'middle',
                });
            },
        }
    };
</script>
