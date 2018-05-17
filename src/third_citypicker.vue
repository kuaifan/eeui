<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">城市选择器</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/third/citypicker/install')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">

            <text class="address">省份：{{province}}</text>
            <text class="address">城市：{{city}}</text>
            <text class="address">区域：{{area}}</text>

            <text class="button" @click="citypicker">选择地址</text>

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
        justify-content: center;
        align-items: center;
    }

    .address {
        height: 56px;
        line-height: 56px;
        text-align: center;
        font-size: 26px;
    }

    .button {
        font-size: 24px;
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
    import {openViewCode} from "../statics/js/app";

    const weiui_citypicker = weex.requireModule('weiui_citypicker');

    export default {
        data() {
            return {
                province: '浙江省',
                city: '杭州',
                area: '市辖区'
            }
        },
        methods: {
            viewCode(str) {
                openViewCode(str);
            },
            citypicker() {
                weiui_citypicker.select({
                    province: this.province,
                    city: this.city,
                    area: this.area
                }, (result) => {
                    this.province = result.province;
                    this.city = result.city;
                    this.area = result.area;
                });
            }
        }
    };
</script>
