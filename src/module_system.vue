<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">系统信息</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/system')">
                <weiui_icon icon="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">
            <text class="item" v-for="(item, index) in lists" :key="index">{{item}}</text>
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
    }

    .item {
        padding-top: 26px;
        padding-bottom: 26px;
        padding-left: 30px;
        padding-right: 30px;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                lists: [],
            }
        },

        mounted() {
            this.lists.push("获取状态栏高度（屏幕像素）：" + weiui.getStatusBarHeight());
            this.lists.push("获取状态栏高度（weex px单位）：" + weiui.getStatusBarHeightPx());
            this.lists.push("获取虚拟键盘高度（屏幕像素）：" + weiui.getNavigationBarHeight());
            this.lists.push("获取虚拟键盘高度（weex px单位）：" + weiui.getNavigationBarHeightPx());
            this.lists.push("获取本地软件版本号：" + weiui.getLocalVersion());
            this.lists.push("获取本地软件版本号名称：" + weiui.getLocalVersionName());
            this.lists.push("获取手机的IMEI：" + weiui.getImei());
        },

        methods: {
            viewCode(str) {
                weiui.openPage({
                    url: "http://kuaifan.vip/weiui/#/" + str,
                    pageType: 'web'
                });
            },
        }
    };
</script>
