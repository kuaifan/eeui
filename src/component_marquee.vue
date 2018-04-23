<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">跑马文字</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('component/weiui_marquee')">
                <weiui_icon content="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <weiui_marquee
                ref="reflectName"
                class="marquee"
                style="width:750px"
                :weiui="{
                        text: scrollText,
                        fontSize: 24,
                    }"
        ></weiui_marquee>

        <weiui_marquee
                ref="reflectName2"
                class="marquee"
                style="width:375px"
                :weiui="{
                        text: scrollText,
                        fontSize: 24,
                        color: '#ff0000',
                        backgroundColor: '#00ffff'
                    }"
        ></weiui_marquee>

        <weiui_marquee
                ref="reflectName3"
                class="marquee"
                style="width:200px"
                :weiui="{
                        text: scrollText,
                        fontSize: 24,
                        color: '#6e0a92',
                        backgroundColor: '#c8e7ff'
                    }"
        ></weiui_marquee>

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

    .marquee {
        margin-top: 50px;
        height: 60px;
        background-color: #00B4FF;
    }
</style>

<script>
    import {openViewCode} from "../statics/js/app";

    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                scrollText: "地方不够放时才滚动！",
            }
        },
        methods: {
            viewCode(str) {
                openViewCode(str);
            },
        }
    };
</script>
