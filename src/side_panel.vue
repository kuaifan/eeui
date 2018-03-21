<template>
    <div class="app">

        <weiui_side_panel
                ref="reflectName"
                class="side_panel"
                :weiui="{
                        width: '380px',
                        scrollbar: false,
                        backgroundColor: '#26c9ff'
                    }"
                @itemClick="itemClick">

            <!--侧滑菜单部分-->
            <weiui_side_panel_menu class="panel_menu" name="菜单1">
                <text>菜单①</text>
            </weiui_side_panel_menu>

            <weiui_side_panel_menu class="panel_menu" name="菜单2">
                <text>菜单②</text>
            </weiui_side_panel_menu>

            <!--正文内容部分-->
            <div class="content">
                <!--正文标题栏-->
                <weiui_navbar class="content-navbar">
                    <weiui_navbar_item type="left">
                        <weiui_icon icon="navicon-round" class="content-navbar-icon" @click="menuToggle"></weiui_icon>
                    </weiui_navbar_item>
                    <weiui_navbar_item type="title">
                        <text class="content-navbar-title">标题</text>
                    </weiui_navbar_item>
                    <weiui_navbar_item type="right">
                        <weiui_icon icon="refresh" class="content-navbar-icon"></weiui_icon>
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

    .side_panel {
        width: 750px;
        flex: 1;
    }

    .panel_menu {
        width: 380px;
        padding-top: 26px;
        padding-bottom: 26px;
        padding-right: 20px;
        padding-left: 20px;
        border-bottom-width: 1px;
        border-bottom-style: solid;
        border-bottom-color: #ffffff;
    }
    .content {
        width: 750px;
    }
    .content-navbar {
        width: 750px;
        height: 100px;
    }
    .content-navbar-icon {
        width: 100px;
        height: 100px;
        color: #ffffff;
    }
    .content-navbar-title {
        color: #ffffff;
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
            itemClick(params) {
                weiui.toast("点击了" + (params.position + 1) + "项，name：" + params.name);
            },
            menuToggle() {
                this.$refs.reflectName.menuToggle();
            },
        }
    };
</script>
