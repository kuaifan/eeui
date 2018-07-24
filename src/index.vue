<template>

    <div class="app">

        <weiui_navbar class="navbar">
            <weiui_navbar_item type="left" @click="scaner">
                <weiui_icon class="navbar-icon" :weiui="{content: 'tb-scan'}"></weiui_icon>
            </weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text class="navbar-title">WEIUI</text>
            </weiui_navbar_item>

            <weiui_navbar_item type="right" @click="refresh">
                <weiui_icon class="navbar-icon" :weiui="{content: 'refresh'}"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div v-if="newApp" class="version-box" @click="downApp">
            <text class="version-text">发现新版本</text>
            <text class="version-button">立即下载</text>
        </div>

        <weiui_list class="list" :weiui="{ pullTips:false}">

            <text class="list-title">Components</text>

            <weiui_recyler @itemClick="componentsClick" >

                <div class="list-item" v-for="item in components">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{content: item.icon}"></weiui_icon>
                        <text class="list-left-title">{{item.title}}</text>
                    </div>
                    <div class="list-item-right">
                        <text class="list-right-title">{{item.title_en}}</text>
                        <weiui_icon class="list-right-icon" :weiui="{content: 'ios-arrow-right 70%'}"></weiui_icon>
                    </div>
                </div>

            </weiui_recyler>

            <text class="list-title">Module</text>

            <weiui_recyler @itemClick="moduleClick" >

                <div class="list-item" v-for="item in module">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{content: item.icon}"></weiui_icon>
                        <text class="list-left-title">{{item.title}}</text>
                    </div>
                    <div class="list-item-right">
                        <text class="list-right-title">{{item.title_en}}</text>
                        <weiui_icon class="list-right-icon" :weiui="{content: 'ios-arrow-right 70%'}"></weiui_icon>
                    </div>
                </div>

            </weiui_recyler>

            <text class="list-title">Third Module</text>

            <weiui_recyler @itemClick="thirdModuleClick" >

                <div class="list-item" v-for="item in third_module">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{content: item.icon}"></weiui_icon>
                        <text class="list-left-title">{{item.title}}</text>
                    </div>
                    <div class="list-item-right">
                        <text class="list-right-title">{{item.title_en}}</text>
                        <weiui_icon class="list-right-icon" :weiui="{content: 'ios-arrow-right 70%'}"></weiui_icon>
                    </div>
                </div>

            </weiui_recyler>

            <text class="list-title">About Weiui</text>

            <weiui_recyler @itemClick="aboutListsClick" >

                <div class="list-item" v-for="item in about_lists">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{content: item.icon}"></weiui_icon>
                        <text class="list-left-title">{{item.title}}</text>
                    </div>
                    <div class="list-item-right">
                        <text class="list-right-title">{{item.title_en}}</text>
                        <weiui_icon class="list-right-icon" :weiui="{content: 'ios-arrow-right 70%'}"></weiui_icon>
                    </div>
                </div>

            </weiui_recyler>

            <div class="list-title-box" v-if="history.length > 0">
                <text class="list-title">扫码历史</text>
                <text class="list-subtitle" @click="clearHistory()">清空历史</text>
            </div>

            <weiui_recyler v-if="history.length > 0" @itemClick="historyClick" >

                <div class="list-item" v-for="text in history">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{content: 'ionic'}"></weiui_icon>
                        <text class="list-left-title-history">{{text}}</text>
                    </div>
                    <div class="list-item-right">
                        <weiui_icon class="list-right-icon" :weiui="{content: 'ios-arrow-right 70%'}"></weiui_icon>
                    </div>
                </div>

            </weiui_recyler>

        </weiui_list>

    </div>

</template>

<style>
    .app {
        flex: 1;
    }

    .navbar {
        width: 750px;
        height: 100px;
    }

    .navbar-title {
        font-size: 32px;
        color: #ffffff;
    }

    .navbar-icon {
        width: 100px;
        height: 100px;
        color: #ffffff;
    }

    .version-box {
        width: 750px;
        height: 82px;
        flex-direction: row;
        background-color: #ff6666;
    }

    .version-text {
        flex: 1;
        padding-left: 20px;
        font-size: 26px;
        color: #ffffff;
        height: 82px;
        line-height: 82px;
    }

    .version-button {
        color: #ffffff;
        font-size: 22px;
        margin-top: 13px;
        margin-right: 20px;
        padding-left: 18px;
        padding-right: 18px;
        height: 56px;
        line-height: 56px;
        border-width: 1px;
        border-color: #e4e4e4;
        border-style: solid;
    }

    .list {
        width: 750px;
        flex: 1;
    }

    .list-title-box {
        flex-direction: row;
        align-items: center;
    }

    .list-title {
        padding-top: 36px;
        padding-right: 24px;
        padding-bottom: 24px;
        padding-left: 24px;
        font-size: 28px;
        color: #757575;
    }

    .list-subtitle {
        position: absolute;
        right: 24px;
        bottom: 24px;
    }

    .list-item {
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
        height: 100px;
        flex: 1;
        padding-left: 20px;
        padding-right: 20px;
        border-top-width: 1px;
        border-top-color: #e8e8e8;
        border-top-style: solid;
    }

    .list-item-left {
        flex-direction: row;
        align-items: center;
        justify-content: flex-start;
        height: 100px;
        flex: 1;
    }

    .list-left-icon {
        width: 60px;
        color: #3EB4FF;
    }

    .list-left-title {
        color: #242424;
        padding-left: 12px;
        width: 380px;
        font-size: 26px;
        text-overflow: ellipsis;
        lines: 1;
    }

    .list-left-title-history {
        color: #242424;
        padding-left: 12px;
        width: 600px;
        font-size: 26px;
        text-overflow: ellipsis;
        lines: 1;
    }

    .list-right-title {
        color: #a2a2a2;
        padding-right: 3px;
        font-size: 22px;
        text-overflow: ellipsis;
        lines: 1;
    }

    .list-right-icon {
        width: 40px;
        color: #C9C9CE;
    }

    .list-item-right {
        flex-direction: row;
        align-items: center;
        justify-content: flex-end;
        height: 100px;
    }
</style>

<script>
    import {checkVersion} from "../statics/js/app";

    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                components: [{
                    title: '轮播控件',
                    title_en: 'weiui_banner',
                    icon: 'easel',
                    url: 'component_banner.js',
                }, {
                    title: '常用按钮',
                    title_en: 'weiui_button',
                    icon: 'android-checkbox-blank',
                    url: 'component_button.js',
                }, {
                    title: '网格容器',
                    title_en: 'weiui_grid',
                    icon: 'grid',
                    url: 'component_grid.js',
                }, {
                    title: '字体图标',
                    title_en: 'weiui_icon',
                    icon: 'ionic',
                    url: 'component_icon.js',
                }, {
                    title: '跑马文字',
                    title_en: 'weiui_marquee',
                    icon: 'code-working',
                    url: 'component_marquee.js',
                }, {
                    title: '导航栏',
                    title_en: 'weiui_navbar',
                    icon: 'navicon',
                    url: 'component_navbar.js',
                }, {
                    title: '列表容器',
                    title_en: 'weiui_recyler',
                    icon: 'ios-list 90%',
                    url: 'component_recyler.js',
                }, {
                    title: '滚动文字',
                    title_en: 'weiui_scroll_text',
                    icon: 'more',
                    url: 'component_scroll_text.js',
                }, {
                    title: '侧边栏',
                    title_en: 'weiui_side_panel',
                    icon: 'ios-box',
                    url: 'component_side_panel.js',
                }, {
                    title: '标签页面',
                    title_en: 'weiui_tabbar',
                    icon: 'filing',
                    url: 'component_tabbar.js',
                }],

                module: [{
                    title: '页面功能',
                    title_en: 'newPage',
                    icon: 'ios-book-outline 96%',
                    url: 'module_page.js',
                }, {
                    title: '系统信息',
                    title_en: 'system',
                    icon: 'gear-a',
                    url: 'module_system.js',
                }, {
                    title: '数据缓存',
                    title_en: 'caches',
                    icon: 'soup-can-outline',
                    url: 'module_caches.js',
                }, {
                    title: '单位转换',
                    title_en: 'weex px',
                    icon: 'ios-calculator',
                    url: 'module_weexpx.js',
                }, {
                    title: '确认对话框',
                    title_en: 'alert',
                    icon: 'android-alert 90%',
                    url: 'module_alert.js',
                }, {
                    title: '等待弹窗',
                    title_en: 'loading',
                    icon: 'load-d',
                    url: 'module_loading.js',
                }, {
                    title: '验证弹窗',
                    title_en: 'captcha',
                    icon: 'ios-checkmark-outline',
                    url: 'module_captcha.js',
                }, {
                    title: '二维码扫描',
                    title_en: 'scaner',
                    icon: 'tb-scan',
                    url: 'module_scaner.js',
                }, {
                    title: '跨域异步请求',
                    title_en: 'ajax',
                    icon: 'pull-request',
                    url: 'module_ajax.js',
                }, {
                    title: '剪切板',
                    title_en: 'clipboard',
                    icon: 'ios-copy-outline',
                    url: 'module_plate.js',
                }, {
                    title: '提示消息',
                    title_en: 'toast',
                    icon: 'ios-barcode-outline',
                    url: 'module_toast.js',
                }, {
                    title: '广告弹窗',
                    title_en: 'adDialog',
                    icon: 'social-buffer-outline',
                    url: 'module_ad_dialog.js',
                }, {
                    title: '更多拓展模块',
                    title_en: 'expandModule',
                    icon: 'more',
                    url: 'index_expand.js',
                }],

                third_module: [{
                    title: '城市选择器',
                    title_en: 'citypicker',
                    icon: 'android-pin',
                    url: 'third_citypicker.js',
                }, {
                    title: '图片选择器',
                    title_en: 'pictureSelector',
                    icon: 'ios-camera-outline',
                    url: 'third_picture.js',
                }],

                about_lists: [{
                    title: '开发文档',
                    title_en: 'document',
                    icon: 'code-working',
                    url: 'http://kuaifan.vip/weiui',
                }, {
                    title: '托管平台',
                    title_en: 'github',
                    icon: 'social-github-outline',
                    url: 'https://github.com/kuaifan/weiui',
                }, {
                    title: '个人博客',
                    title_en: 'http://kuaifan.vip',
                    icon: 'social-rss-outline',
                    url: 'http://kuaifan.vip',
                }],

                history: [],

                newApp: false,
            }
        },

        mounted() {
            this.history = JSON.parse(weiui.getCachesString("scaner", []));
            //
            if (!checkVersion(22)) {
                this.newApp = true;
                weiui.confirm({
                    title: "版本更新",
                    message: "你当前使用的版本比较低，部分功能可能无法正常显示，建议升级至最新版本！",
                    buttons: [{
                        title: "稍后再说",
                        type: "negative"
                    }, {
                        title: "立即下载",
                        type: "positive"
                    }],
                    cancelable: false
                }, (result)=>{
                    if (result.status === "click" && result.title === "立即下载") {
                        this.downApp();
                    }
                });
            }
            //
            weiui.setPageBackPressed(null, function(){
                weiui.confirm({
                    title: "温馨提示",
                    message: "你确定要退出WEIUI吗？",
                    buttons: ["取消", "确定"]
                }, (result)=>{
                    if (result.status === "click" && result.title === "确定") {
                        weiui.closePage(null);
                    }
                });
            });
        },

        methods: {
            downApp() {
                weiui.openWeb("http://kuaifan.vip/weiui/app/android-13.apk");
            },

            scaner() {
                weiui.openScaner(null, (res) => {
                    if (res.status === "success") {
                        this.history.unshift(res.text);
                        weiui.setCachesString("scaner", JSON.stringify(this.history), 0);
                        this.openAuto(res.text);
                    }
                });
            },

            refresh() {
                weiui.reloadPage();
            },

            componentsClick(data) {
                this.openUrl(this.components[data.position].url);
            },

            moduleClick(data) {
                this.openUrl(this.module[data.position].url);
            },

            thirdModuleClick(data) {
                this.openUrl(this.third_module[data.position].url);
            },

            aboutListsClick(data) {
                this.openWeb(this.about_lists[data.position].url);
            },

            historyClick(data) {
                this.openAuto(this.history[data.position]);
            },

            clearHistory() {
                weiui.confirm({
                    title: "删除提示",
                    message: "你确定要删除扫码记录吗？",
                    buttons: ["取消", "确定"]
                }, (result)=>{
                    if (result.status === "click" && result.title === "确定") {
                        this.history = [];
                        weiui.setCachesString("scaner", JSON.stringify(this.history), 0);
                    }
                });
            },

            openUrl(url) {
                weiui.openPage({
                    url: url
                });
            },

            openWeb(url) {
                weiui.openPage({
                    url: url,
                    pageType: 'web'
                });
            },

            openAuto(url) {
                weiui.openPage({
                    url: url,
                    pageType: 'auto'
                });
            },
        }
    };
</script>
