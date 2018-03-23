<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">跨域异步请求</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/ajax')">
                <weiui_icon icon="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">
            <input class="inPut" v-model="url"/>
            <text class="button" @click="startAjax">开始请求</text>
            <text class="subtitle">状态：{{status}}</text>
            <textarea class="textarea" rows="20" v-model="result"></textarea>
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

    .button {
        width: 380px;
        text-align: center;
        margin-top: 50px;
        padding-top: 26px;
        padding-bottom: 26px;
        padding-left: 30px;
        padding-right: 30px;
        color: #ffffff;
        background-color: #00B4FF;
    }

    .subtitle {
        padding-top: 26px;
        padding-right: 24px;
        padding-bottom: 24px;
        padding-left: 24px;
        font-size: 28px;
        color: #757575;
    }

    .inPut {
        margin-top: 50px;
        font-size: 26px;
        width: 650px;
        height: 80px;
        padding-top: 5px;
        padding-bottom: 5px;
        padding-left: 5px;
        padding-right: 5px;
        border-width: 1px;
        border-style: solid;
        border-color: #65b4d8;
    }

    .textarea {
        font-size: 20px;
        width: 650px;
        margin-top: 20px;
        padding-top: 5px;
        padding-bottom: 5px;
        padding-left: 5px;
        padding-right: 5px;
        color: #666666;
        border-width: 2px;
        border-style: solid;
        border-color: #65b4d8;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                url: 'http://dotwe.org/raw/dist/7f90ed2135dc774fa2abb06c4fa1901f.bundle.wx',
                status: '',
                result: '',
            }
        },
        methods: {
            viewCode(str) {
                weiui.openPage({
                    url: "http://kuaifan.vip/weiui/#/" + str,
                    pageType: 'web'
                });
            },
            startAjax() {
                weiui.ajax({
                    url: 'http://dotwe.org/raw/dist/7f90ed2135dc774fa2abb06c4fa1901f.bundle.wx',
                    dataType: 'text',
                }, (res) => {
                    this.status = res.status;
                    if (res.status === "success") {
                        this.result = res.result;
                    }
                });
            },
        }
    };
</script>
