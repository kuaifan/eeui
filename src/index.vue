<template>
    <div class="wrapper">


        <weiui_tabbar
                ref="myTabbar"
                class="tabbar"
                type="top|bottom"
                :weiui="tabPagesUI"
                @tabSelect="tabSelect"
                @refreshListener="refreshListener"
                @pageSelected="pageSelected">


            <weiui_tabbar_page name="AA" title="首页" message="10" :weiui="{message:3, selectedIcon:'ion-ionic'}">
                <div><text>page test</text></div>
                <div><text>page test1</text></div>
                <div><text>page test2</text></div>
                <div><text>page test3</text></div>
            </weiui_tabbar_page>


            <weiui_tabbar_page name="BB" :message="2">
                <div><text>page test2</text></div>
                <weiui_icon style="width:100px;height:100px;" iconClickColor="#ff0000" @click="LoadingIcon"></weiui_icon>
            </weiui_tabbar_page>


            <weiui_tabbar_page>
                <div><text>page test3</text></div>
                <weiui_icon style="width:100px;height:100px;" iconClickColor="#ff0000" @click="swipeCaptcha"></weiui_icon>
                <image style="width:100px;height:100px;" src="http://img.lanrentuku.com/img/allimg/1212/5-121204193R7-51.gif"></image>
            </weiui_tabbar_page>


            <weiui_tabbar_page>
                <div><text>page test4</text></div>
                <weiui_icon style="width:100px;height:100px;" iconClickColor="#ff0000" @click="scaner"></weiui_icon>
                <weiui_icon style="width:100px;height:100px;" iconClickColor="#ff0000" @click="pictureCreate"></weiui_icon>

                <image v-for="(item, index) in pictureList" :src="'file://' + item.path" @click="pictureView(index)" style="width:100px;height:100px;margin-right:10px;margin-top:10px;"></image>

            </weiui_tabbar_page>


        </weiui_tabbar>

    </div>
</template>

<style>
    .wrapper {
        flex: 1
    }
    .tabbar {
        flex: 1;
        width: 750px;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');
    const weiui_picture = weex.requireModule('weiui_picture');

    export default {
        data() {
            return {
                tabPages: [
                    {
                        name: "a",
                        title: '页面1',
                        selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                        unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                        url: 'http://dotwe.org/raw/dist/c99b8282d1c822a1eb78dc5bc94c877e.bundle.wx',
                        message: 5
                    },
                    {
                        name: "b",
                        title: '页面2',
                        selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                        unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                        url: 'http://dotwe.org/raw/dist/e1b7ff7c02bf7898d9f2619d9c1fc966.bundle.wx',
                        dot: true
                    },
                    {
                        name: "c",
                        title: '页面3',
                        selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                        unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                        url: 'http://dotwe.org/raw/dist/6087fb97013406c0623e86ef7786a532.bundle.wx',
                    },
                ],
                tabPagesUI: {},
                pictureList: [],

            }
        },
        mounted() {

            /*setTimeout(() => {
                console.log("aaaaaaaaaaaIP", weiui.screenUtils("getScreenWidth"));
                console.log("aaaaaaaaaaaIP", weiui.permissionUtils("getMatches"));

                /!*var aaa = weiui.regex("getMatches", "1(.*?)3", "1a31b31c31d3");

                console.log("aaaaaaaD", typeof aaa);
                console.log("aaaaaaaD1|", aaa.length);
                for (let key in aaa) {
                    console.log("aaaaaaaD2|", key);
                    console.log("aaaaaaaD3|", aaa[key]);
                }*!/

                /!*weiui.toast({
                    "gravity": "top",
                    "y": 100,
                    "long": true,
                    "message": "getMatches",
                });
                setTimeout(() => {
                    weiui.toast();
                }, 1000);*!/

            }, 2000);*/




            /*setTimeout(() => {
                this.$refs.myTabbar.showMsg("BB", 12);
            }, 1000);*/
            /*setTimeout(() => {
                this.$refs.myTabbar.setCurrentItem("c");
                this.$refs.myTabbar.goUrl("c", "http://dotwe.org/raw/dist/ac78df2f9db68ce3222b164d00458a4f.bundle.wx");
            }, 2000);*/
        },
        methods: {
            count(obj) {
                if (typeof obj.length === 'number') {
                    return obj.length;
                } else {
                    let i = 0, key;
                    for (key in obj) {
                        i++;
                    }
                    return i;
                }
            },
            tabSelect(params) {
                console.log("tabSelect", params);
            },
            refreshListener(params) {
                this.$refs.myTabbar.setRefreshing(params['name'], false);
            },
            pageSelected(params) {
                console.log("pageSelected", params);
            },
            scaner() {
                weiui.openScaner(null, (res) => {
                    if (res.status === "success") {
                        weiui.toast(res.status + " | " + res.text);
                    }
                });
            },
            pictureCreate() {
                weiui_picture.create({
                    selected: this.pictureList
                }, (res)=>{
                    if (res.status === "success") {
                        this.pictureList = res.lists;
                    }
                    console.log(res);
                });
            },
            pictureView(index) {
                weiui_picture.picturePreview(index, this.pictureList);
            },
            LoadingIcon() {
                weiui.loading({
                    title: '000000',
                });
                setTimeout(()=>{
                    weiui.loadingClose();
                }, 5000);
            },
            swipeCaptcha() {
                weiui.swipeCaptcha(null, (res) => {
                    weiui.toast(res.status);
                });
            }
        }
    };
</script>
