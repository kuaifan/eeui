<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">确认对话框</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/alert')">
                <weiui_icon icon="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">
            <text class="button" @click="toAlert">alert</text>
            <text class="button" @click="toAlert2">alert 带标题</text>
            <text class="button" @click="toConfirm">confirm</text>
            <text class="button" @click="toConfirm2">confirm 3个按钮</text>
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
    const weiui = weex.requireModule('weiui');

    export default {
        methods: {
            viewCode(str) {
                weiui.openPage({
                    url: "http://kuaifan.vip/weiui/#/" + str,
                    pageType: 'web'
                });
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
        }
    };
</script>
