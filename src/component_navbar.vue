<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">导航栏</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('component/weiui_navbar')">
                <weiui_icon icon="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <!--样式①-->
        <weiui_navbar class="navbar">

            <weiui_navbar_item type="back"></weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text>标题</text>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式②-->
        <weiui_navbar class="navbar" :weiui="{titleType:'left'}">

            <weiui_navbar_item type="back"></weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text>标题</text>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式③-->
        <weiui_navbar class="navbar">

            <weiui_navbar_item type="back"></weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text>标题</text>
            </weiui_navbar_item>

            <weiui_navbar_item type="right">
                <weiui_icon icon="refresh" class="icon"></weiui_icon>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式④-->
        <weiui_navbar class="navbar">

            <weiui_navbar_item type="left">
                <weiui_icon icon="navicon-round" class="icon"></weiui_icon>
            </weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text>标题</text>
            </weiui_navbar_item>

            <weiui_navbar_item type="right">
                <weiui_icon icon="refresh" class="icon"></weiui_icon>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式⑤-->
        <weiui_navbar class="navbar">

            <!--返回按钮-->
            <weiui_navbar_item type="back"></weiui_navbar_item>

            <!--左边内容-->
            <weiui_navbar_item type="left">
                <text>左边内容</text>
            </weiui_navbar_item>

            <!--中间内容(标题内容)-->
            <weiui_navbar_item type="title">
                <text>中间(标题)内容</text>
            </weiui_navbar_item>

            <!--右边内容-->
            <weiui_navbar_item type="right">
                <text>右边内容</text>
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

    .icon {
        width: 100px;
        height: 100px;
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
        }
    };
</script>