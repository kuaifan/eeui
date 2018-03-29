<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">广告弹窗</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('component/module_ad_dialog')">
                <weiui_icon icon="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">
            <text class="button" @click="openAdDialog">打开广告弹窗</text>
        </div>

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

    .content {
        flex: 1;
        justify-content: center;
        align-items: center;
    }

    .button {
        text-align: center;
        margin-top: 20px;
        margin-bottom: 20px;
        padding-top: 20px;
        padding-bottom: 20px;
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
            openAdDialog() {
                weiui.adDialog("http://kuaifan.vip/weiui/assets/images/testImage1.png", (res) => {
                    weiui.toast("状态：" + res.status);
                });
            },
        }
    };
</script>
