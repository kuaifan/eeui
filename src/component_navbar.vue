<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">导航栏</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('component/weiui_navbar')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <!--样式①-->
        <weiui_navbar class="navbar">

            <weiui_navbar_item type="back"></weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text class="nav-title">标题</text>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式②-->
        <weiui_navbar class="navbar" :weiui="{titleType:'left'}">

            <weiui_navbar_item type="back"></weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text class="nav-title">标题</text>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式③-->
        <weiui_navbar class="navbar">

            <weiui_navbar_item type="back"></weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text class="nav-title">标题</text>
            </weiui_navbar_item>

            <weiui_navbar_item type="right">
                <weiui_icon content="refresh" class="icon"></weiui_icon>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式④-->
        <weiui_navbar class="navbar">

            <weiui_navbar_item type="left">
                <weiui_icon content="navicon-round" class="icon"></weiui_icon>
            </weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text class="nav-title">标题</text>
            </weiui_navbar_item>

            <weiui_navbar_item type="right">
                <weiui_icon content="refresh" class="icon"></weiui_icon>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式⑤-->
        <weiui_navbar class="navbar">

            <!--返回按钮-->
            <weiui_navbar_item type="back"></weiui_navbar_item>

            <!--左边内容-->
            <weiui_navbar_item type="left">
                <text class="nav-title">左边内容</text>
            </weiui_navbar_item>

            <!--中间内容(标题内容)-->
            <weiui_navbar_item type="title">
                <text class="nav-title">中间(标题)内容</text>
            </weiui_navbar_item>

            <!--右边内容-->
            <weiui_navbar_item type="right">
                <text class="nav-title">右边内容</text>
            </weiui_navbar_item>

        </weiui_navbar>

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

    .navbar {
        width: 750px;
        margin-top: 50px;
    }

    .nav-title {
        font-size: 24px;
        color: #ffffff
    }

    .icon {
        width: 100px;
        height: 100px;
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
        }
    };
</script>