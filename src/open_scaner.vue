<template>
    <div class="app">

        <text class="button" @click="openScaner">打开二维码扫描</text>

    </div>
</template>

<style>
    .app {
        flex: 1;
        justify-content: center;
        align-items: center;
    }
    .button {
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
            openScaner() {
                weiui.openScaner(null, (res)=>{
                    switch (res.status) {
                        case "success":
                            weiui.toast("识别成功：" + res.text);
                            break;

                        case "failed":
                            weiui.toast("识别失败");
                            break;
                    }
                });
            },
        }
    };
</script>
