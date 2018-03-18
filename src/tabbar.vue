<template>
    <div class="wrapper">

        <weiui_navbar>

            <weiui_navbar_item type="middle"><text style="color:#ffffff">你妹的</text></weiui_navbar_item>

            <weiui_navbar_item type="right" style="padding-left:10px;"><text>右边</text></weiui_navbar_item>

        </weiui_navbar>


        <text @click="goTabbar()" style="height:80px;line-height:80px;width:750px;background-color:#ff0000;margin-top:20px;">click</text>

    </div>
</template>

<style scoped>
    .wrapper {
        flex: 1
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');
    export default {
        data() {
        },
        mounted() {
            setTimeout(() => {

                /*weiui.ajax({
                    url: 'http://jsfiddle.net/echo/jsonp/?callback=anything&result=content_in_response',
                }, (ret)=>{
                    console.log("aaaaaaaaaaaR", ret);
                });*/

                /*weiui.openQR(null, (ret)=>{
                    console.log("aaaaaaaaaaaQ", ret);
                });*/

            }, 2000);

            /*setTimeout(() => {
                weiui.openWin({
                    url: 'http://www.kuaifan.net',
                    type: 'web',
                    statusBarType: 'immersion',
                    swipeBack: true,
                });
            }, 4000);*/
        },
        methods: {
            goTabbar() {
                weiui.openWin({
                    url: 'http://192.168.2.134:8081/dist/tabbar.js',
                });
            },
        }
    };
</script>
