<template>
    <div class="app">

        <weiui_side_panel
                ref="reflectName"
                class="side_panel"
                :weiui="{
                        width: '420px',
                        scrollbar: false,
                        backgroundColor: '#26c9ff'
                    }"
                @itemClick="itemClick"
                @switchListener="switchListener">

            <!--侧滑菜单部分-->
            <weiui_side_panel_menu class="panel_menu" name="菜单1">
                <text class="menu-text">菜单①</text>
            </weiui_side_panel_menu>

            <weiui_side_panel_menu class="panel_menu" name="菜单2">
                <text class="menu-text">菜单②</text>
            </weiui_side_panel_menu>

            <!--正文内容部分-->
            <div class="content">
                <!--正文标题栏-->
                <weiui_navbar class="content-navbar">
                    <weiui_navbar_item type="back"></weiui_navbar_item>
                    <weiui_navbar_item type="title">
                        <text class="title">侧边栏</text>
                    </weiui_navbar_item>
                    <weiui_navbar_item type="right" @click="viewCode('component/weiui_side_panel')">
                        <weiui_icon icon="code-working" class="iconr"></weiui_icon>
                    </weiui_navbar_item>
                </weiui_navbar>
                <!--正文内容-->
                <scroller class="content-body">
                    <text>正文内容，屏幕右划试试看。</text>
                    <text class="content-body-toggle" @click="menuToggle">切换显示状态</text>
                </scroller>
            </div>

        </weiui_side_panel>

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

    .side_panel {
        width: 750px;
        flex: 1;
    }

    .panel_menu {
        width: 420px;
        padding-top: 26px;
        padding-bottom: 26px;
        padding-right: 20px;
        padding-left: 20px;
        border-bottom-width: 1px;
        border-bottom-style: solid;
        border-bottom-color: #ffffff;
    }

    .menu-text {
        color: #ffffff;
        font-size: 26px;
    }

    .content {
        width: 750px;
    }

    .content-navbar {
        width: 750px;
        height: 100px;
    }

    .content-body {
        flex: 1;
        justify-content: center;
        align-items: center;
    }

    .content-body-toggle {
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
    const weiui = weex.requireModule('weiui');

    export default {
        methods: {
            viewCode(str) {
                weiui.openPage({
                    url: "http://kuaifan.vip/weiui/#/" + str,
                    pageType: 'web'
                });
            },
            itemClick(params) {
                weiui.toast("点击了" + (params.position + 1) + "项，name：" + params.name);
            },
            switchListener(params) {
                weiui.toast("侧边栏：" + (params.show ? "显示了" : "隐藏了"));
            },
            menuToggle() {
                this.$refs.reflectName.menuToggle();
            },
        }
    };
</script>
