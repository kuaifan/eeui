<template>
    <div class="app">

        <weiui_tabbar
                ref="reflectName"
                class="tabbar"
                :weiui="{ tabType: 'bottom' }"
                @tabSelect="tabSelect"
                @tabReselect="tabReselect"
                @refreshListener="refreshListener">

            <!--页签①-->
            <weiui_tabbar_page :weiui="{ tabName: 'name_1', title:'首页', selectedIcon:'home' }">
                <weiui_navbar class="page-navbar">
                    <weiui_navbar_item type="title">
                        <text class="page-navbar-title">首页</text>
                    </weiui_navbar_item>
                </weiui_navbar>
                <div class="page-content">
                    <text>页签里面可以放任何子组件，感谢你对weiui的支持</text>
                    <image src="http://demo.sc.chinaz.com/Files/pic/icons/6430/m2.png" class="page-content-image"></image>
                </div>
            </weiui_tabbar_page>

            <!--页签②-->
            <weiui_tabbar_page :weiui="{ tabName: 'name_2', title:'好友', message:3, selectedIcon:'http://demo.sc.chinaz.com/Files/pic/icons/6749/g7.png' , unSelectedIcon:'http://demo.sc.chinaz.com/Files/pic/icons/6749/g4.png' }">
                <div class="page-content"><text>page 2，图标支持网络图片</text></div>
            </weiui_tabbar_page>

            <!--页签③-->
            <weiui_tabbar_page :weiui="{ tabName: 'name_3', title:'圈子', message:99, selectedIcon:'aperture 26sp' }">
                <div class="page-content"><text>page 3</text></div>
            </weiui_tabbar_page>

            <!--页签④-->
            <weiui_tabbar_page :weiui="{ tabName: 'name_4', title:'设置', dot:true, selectedIcon:'gear-a' }">
                <div class="page-content"><text>page 4</text></div>
            </weiui_tabbar_page>

        </weiui_tabbar>

    </div>
</template>

<style>
    .app {
        flex: 1
    }
    .tabbar {
        width: 750px;
        flex: 1;
    }
    .page-content {
        width: 750px;
        flex: 1;
        justify-content: center;
        align-items: center;
    }
    .page-navbar {
        width: 750px;
        height: 90px;
    }
    .page-navbar-title {
        color: #ffffff;
        font-size: 28px;
    }
    .page-content-image {
        width: 480px;
        height: 480px;
        margin-top: 30px;
        margin-bottom: 30px;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');

    export default {
        methods: {
            tabSelect(params) {
                weiui.toast("切换到第" + (params.position + 1) + "个标签页");
            },
            tabReselect(params) {
                weiui.toast("第" + (params.position + 1) + "个标签页被再次点击");
            },
            refreshListener(params) {
                setTimeout(()=>{
                    weiui.toast("刷新成功：第" + (params.position + 1) + "个标签页");
                    this.$refs.reflectName.setRefreshing(params['tabName'], false);
                }, 1000);
            }
        }
    };
</script>