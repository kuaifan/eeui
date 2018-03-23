<template>

    <div class="app">

        <weiui_navbar class="navbar">
            <weiui_navbar_item type="left" @click="scaner">
                <weiui_icon class="navbar-icon" :weiui="{icon: 'qr-scanner'}"></weiui_icon>
            </weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text class="navbar-title">WEIUI</text>
            </weiui_navbar_item>

            <weiui_navbar_item type="right" @click="refresh">
                <weiui_icon class="navbar-icon" :weiui="{icon: 'refresh'}"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <weiui_list class="list" :weiui="{pullTips:false}">

            <text class="list-title">Components</text>

            <weiui_recyler>

                <div class="list-item" v-for="item in components" @click="openUrl(item.url)">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{icon: item.icon}"></weiui_icon>
                        <text class="list-left-title">{{item.title}}</text>
                    </div>
                    <div class="list-item-right">
                        <text class="list-right-title">{{item.title_en}}</text>
                        <weiui_icon class="list-right-icon" :weiui="{icon: 'ios-arrow-right 70%'}"></weiui_icon>
                    </div>
                </div>

            </weiui_recyler>

            <text class="list-title">Module</text>

            <weiui_recyler>

                <div class="list-item" v-for="item in module" @click="openUrl(item.url)">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{icon: item.icon}"></weiui_icon>
                        <text class="list-left-title">{{item.title}}</text>
                    </div>
                    <div class="list-item-right">
                        <text class="list-right-title">{{item.title_en}}</text>
                        <weiui_icon class="list-right-icon" :weiui="{icon: 'ios-arrow-right 70%'}"></weiui_icon>
                    </div>
                </div>

            </weiui_recyler>

            <text class="list-title">Third Module</text>

            <weiui_recyler>

                <div class="list-item" v-for="item in third_module" @click="openUrl(item.url)">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{icon: item.icon}"></weiui_icon>
                        <text class="list-left-title">{{item.title}}</text>
                    </div>
                    <div class="list-item-right">
                        <text class="list-right-title">{{item.title_en}}</text>
                        <weiui_icon class="list-right-icon" :weiui="{icon: 'ios-arrow-right 70%'}"></weiui_icon>
                    </div>
                </div>

            </weiui_recyler>

            <text class="list-title">About Weiui</text>

            <weiui_recyler>

                <div class="list-item" v-for="item in about_lists" @click="openWeb(item.url)">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{icon: item.icon}"></weiui_icon>
                        <text class="list-left-title">{{item.title}}</text>
                    </div>
                    <div class="list-item-right">
                        <text class="list-right-title">{{item.title_en}}</text>
                        <weiui_icon class="list-right-icon" :weiui="{icon: 'ios-arrow-right 70%'}"></weiui_icon>
                    </div>
                </div>

            </weiui_recyler>

            <div class="list-title-box" v-if="history.length > 0">
                <text class="list-title">扫码历史</text>
                <text class="list-subtitle" @click="clearHistory()">清空历史</text>
            </div>

            <weiui_recyler v-if="history.length > 0">

                <div class="list-item" v-for="text in history" @click="openUrl(text)">
                    <div class="list-item-left">
                        <weiui_icon class="list-left-icon" :weiui="{icon: 'ionic'}"></weiui_icon>
                        <text class="list-left-title-history">{{text}}</text>
                    </div>
                    <div class="list-item-right">
                        <weiui_icon class="list-right-icon" :weiui="{icon: 'ios-arrow-right 70%'}"></weiui_icon>
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
    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                components: [{
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
                    icon: 'qr-scanner',
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
                    title: '更多拓展模块',
                    title_en: 'expandModule',
                    icon: 'more',
                    url: 'index_expand.js',
                }],

                third_module: [{
                    title: '图片选择器',
                    title_en: 'third_picture',
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
            }
        },

        mounted() {
            this.history = JSON.parse(weiui.getCachesString("scaner"), []);
        },

        methods: {

            clearHistory() {
                this.history = [];
                weiui.setCachesString("scaner", JSON.stringify(this.history), 0);
            },

            scaner() {
                weiui.openScaner(null, (res) => {
                    if (res.status === "success") {
                        this.history.unshift(res.text);
                        weiui.setCachesString("scaner", JSON.stringify(this.history), 0);
                        this.openUrl(res.text);
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

            refresh() {
                weiui.reloadPage();
            },
        }
    };
</script>