<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">确认对话框</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/alert')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">
            <text class="button" @click="toAlert">alert</text>
            <text class="button" @click="toAlert2">alert 带标题</text>
            <text class="button" @click="toConfirm">confirm</text>
            <text class="button" @click="toConfirm2">confirm 3个按钮</text>
            <text class="button" @click="toInput">input</text>
            <text class="button" @click="toInput2">input 2个输入框</text>
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
        margin-top: 16px;
        margin-bottom: 16px;
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
            toAlert() {
                weiui.alert('你使用weiui了吗？', function() {
                    weiui.toast("点击了确定！")
                });

            },
            toAlert2() {
                weiui.alert({
                    title: '温馨提示',
                    message: '使用weiui真的很不错哦！',
                }, function() {
                    weiui.toast("点击了确定！")
                });
            },
            toConfirm() {
                weiui.confirm("你确定使用weiui了吗？", function(result) {
                    if (result.status == "click") {
                        weiui.toast("点击了：" + result.title)
                    }
                });
            },
            toConfirm2() {
                weiui.confirm({
                    title: "温馨提示",
                    message: "你确定使用weiui了吗？",
                    buttons: ["取消", "确定", "第三个按钮"],
                }, function(result) {
                    if (result.status == "click") {
                        weiui.toast("点击了：" + result.title)
                    }
                });
            },
            toInput() {
                weiui.input({
                    title: "输入昵称",
                    buttons: ["取消", "确定"],
                    inputs:[{
                        type: 'text',
                    }]
                }, function(result) {
                    if (result.status == "click" && result.title == "确定") {
                        weiui.toast("昵称：" + result.data[0])
                    }
                });
            },
            toInput2() {
                weiui.input({
                    title: "输入昵称和真实姓名",
                    buttons: ["取消", "确定"],
                    inputs:[{
                        type: 'text',
                        placeholder: '请输入昵称',
                    },{
                        type: 'text',
                        placeholder: '请输入真实姓名',
                    }]
                }, function(result) {
                    if (result.status == "click" && result.title == "确定") {
                        weiui.toast("昵称：" + result.data[0] + "，真实姓名：" + result.data[1])
                    }
                });
            }
        }
    };
</script>
