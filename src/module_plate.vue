<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">剪切板</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/plate')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">
            <input class="inPut" v-model="text"/>
            <text class="button" @click="copyText">复制文本到剪贴板</text>
            <text class="button" @click="pasteText">获取剪贴板的文本</text>
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

    .inPut {
        font-size: 26px;
        width: 650px;
        height: 80px;
        margin-bottom: 30px;
        padding-top: 10px;
        padding-bottom: 10px;
        padding-left: 10px;
        padding-right: 10px;
        border-width: 1px;
        border-style: solid;
        border-color: #65b4d8;
    }

    .button {
        width: 380px;
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
</style>

<script>
    import {openViewCode} from "../statics/js/app";

    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                text: '',
            }
        },

        mounted() {
            this.text = '随机字符' + this.randomString(6);
        },
        methods: {
            viewCode(str) {
                openViewCode(str);
            },
            randomString(len) {
                len = len || 32;
                let $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678oOLl9gqVvUuI1';
                let maxPos = $chars.length;
                let pwd = '';
                for (let i = 0; i < len; i++) {
                    pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
                }
                return pwd;
            },

            copyText() {
                weiui.copyText(this.text);
                weiui.toast("复制成功");
            },
            pasteText() {
                let variable = weiui.pasteText();
                weiui.toast("获取剪贴板：" + variable);
            },
        }
    };
</script>
