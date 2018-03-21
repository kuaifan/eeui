<template>
    <div class="app">

        <text class="button" @click="swipeCaptcha">打开验证</text>

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
            swipeCaptcha() {
                weiui.swipeCaptcha(null, (res)=>{
                    switch (res.status) {
                        case "success":
                            weiui.toast("验证成功");
                            break;

                        case "failed":
                            weiui.toast("验证失败");
                            break;
                    }
                });
            },
        }
    };
</script>
