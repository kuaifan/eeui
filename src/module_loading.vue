<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">等待弹窗</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/loading')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <weiui_list class="content" :weiui="{row:2,  pullTips:false}">
            <div class="item" v-for="(item, index) in lists" :key="index">
                <text class="button" @click="loading(item)">样式:{{item}}</text>
            </div>
        </weiui_list>

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
        width: 750px;
        flex: 1;
        padding-top: 20px;
    }

    .item {
        width: 375px;
        align-items: center;
    }

    .button {
        width: 320px;
        font-size: 24px;
        text-align: center;
        margin-top: 20px;
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
                lists: [
                    'default',
                    'RotatingPlane',
                    'DoubleBounce',
                    'Wave',
                    'WanderingCubes',
                    'Pulse',
                    'ChasingDots',
                    'ThreeBounce',
                    'Circle',
                    'CubeGrid',
                    'FadingCircle',
                    'FoldingCube',
                    'RotatingCircle'
                ],
            }
        },
        methods: {
            viewCode(str) {
                openViewCode(str);
            },
            loading(style) {
                weiui.loading({
                    style: style,
                });
            },
        }
    };
</script>
