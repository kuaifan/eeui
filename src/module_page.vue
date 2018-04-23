<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">页面功能</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/newPage')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">
            <text class="button" @click="openPage">打开一个普通的页面</text>
            <text class="button" @click="openPage2">打开一个沉浸式页面</text>
            <text class="button" @click="openPage3">打开一个全屏的页面</text>
            <text class="button" @click="openPage4">打开一个WEB页面</text>
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
        methods: {
            viewCode(str) {
                openViewCode(str);
            },
            openPage() {
                weiui.openPage({
                    url: 'http://dotwe.org/raw/dist/25f26c060a9358ebfce419f1a0dc7f9b.bundle.wx',
                });
            },
            openPage2() {
                weiui.openPage({
                    url: 'http://dotwe.org/raw/dist/a3ccb0407c780c120c65ade15c6c7110.bundle.wx',
                    statusBarType: 'immersion',
                });
            },
            openPage3() {
                weiui.openPage({
                    url: 'http://dotwe.org/raw/dist/c7f96bf3571cc23a94127a6835d1c7a4.bundle.wx',
                    statusBarType: 'fullscreen',
                });
            },
            openPage4() {
                weiui.openPage({
                    url: 'http://kuaifan.vip/weiui',
                    pageType: 'web',
                });
            },
        }
    };
</script>
