<template>
    <div class="box">

        <weiui_icon class="icon"
                    :weiui="{
                        text: 'qr-scanner',
                        textSize: '50',
                    }"
                    @click="scaner"></weiui_icon>

        <weiui_recyler class="lists">

            <text v-for="text in history" class="lists-item" @click="openUrl(text)">{{text}}</text>

        </weiui_recyler>
    </div>
</template>

<style>
    .box {
        flex: 1;
        align-items: center;
    }
    .icon {
        width: 200px;
        height: 200px;
        margin-left: 10px;
        margin-right: 10px;
    }
    .lists {
        width: 750px;
        flex: 1;
        padding-left: 20px;
        padding-right: 20px;
        border-top-width: 1px;
        border-top-style: solid;
        border-top-color: #cccccc;
    }
    .lists-item {
        padding-top: 20px;
        padding-bottom: 20px;
        border-bottom-width: 1px;
        border-bottom-style: solid;
        border-bottom-color: #cccccc;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                history: []
            }
        },

        mounted() {
            this.history = JSON.parse(weiui.getCachesString("scaner"), []);
        },

        methods: {

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
            }
        }
    };
</script>