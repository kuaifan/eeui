<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">数据缓存</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/caches')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">

            <text class="subtitle">数据缓存：重启APP不被删除</text>

            <text style="font-size:24px">{{caches}}</text>

            <text class="button" @click="setCaches()">设置缓存</text>
            <text class="button" @click="getCaches()">读取缓存</text>


            <text class="subtitle">全局变量：重启APP后数据不存在</text>

            <text style="font-size:24px">{{variate}}</text>

            <text class="button" @click="setVariate()">设置变量</text>
            <text class="button" @click="getVariate()">读取变量</text>

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
        align-items: center;
    }

    .subtitle {
        padding-top: 56px;
        padding-right: 24px;
        padding-bottom: 24px;
        padding-left: 24px;
        font-size: 28px;
        color: #757575;
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
        data() {
            return {
                caches: '',
                variate: '',
            }
        },

        mounted() {
            this.caches = weiui.getCachesString('tempName', '');
            this.variate = weiui.getVariate('tempName', '');
        },

        methods: {
            viewCode(str) {
                openViewCode(str);
            },
            randomString(len) {
                len = len || 32;
                let $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678oOLl9gqVvUuI1';
                let maxPos = $chars.length;
                let pwd = '';
                for (let i = 0; i < len; i++) {
                    pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
                }
                return pwd;
            },

            setCaches() {
                this.caches = this.randomString(16);
                weiui.setCachesString('tempName', this.caches, 0);
            },
            getCaches() {
                this.caches = weiui.getCachesString('tempName', '');
                weiui.toast("读取成功，缓存：" + this.caches)
            },

            setVariate() {
                this.variate = this.randomString(16);
                weiui.setVariate('tempName', this.variate);
            },
            getVariate() {
                this.variate = weiui.getVariate('tempName', '');
                weiui.toast("读取成功，变量：" + this.variate)
            },
        }
    };
</script>
