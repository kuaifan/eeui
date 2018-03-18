<template>
    <div class="wrapper">

        <scroller>

            <weiui_navbar>

                <weiui_navbar_item type="back"></weiui_navbar_item>

                <weiui_navbar_item type="middle"><text style="color:#ffffff">╮(╯_╰)╭</text></weiui_navbar_item>


            </weiui_navbar>

            <weiui_scroll_text
                    style="height:500px"
                    :text="scrollText"
                    :weiui="{
                        speed: 2,
                        fontSize: 16,
                        color: '#ff0000',
                        backgroundColor: '#00ffff'
                    }"
                    @itemClick="itemClickScroll"
            ></weiui_scroll_text>

            <weiui_side_panel
                    ref="mySidePanel"
                    class="side_panel">

                <weiui_side_menu
                        :weiui="{
                        scrollbar: false,
                        width: 400,
                        backgroundColor: '#00ffff'
                    }">
                    <text>bbbb</text>
                    <text>bbbb2</text>
                    <texwt>bbbb3</texwt>
                    <text>bbbb3-1</text>
                    <div>
                        <text>bbbb4</text>
                    </div>
                    <text>bbbb</text>
                    <text>bbbb2</text>
                    <texwt>bbbb3</texwt>
                    <text>bbbb3-1</text>
                    <div>
                        <text>bbbb4</text>
                    </div>
                    <text>bbbb</text>
                    <text>bbbb2</text>
                    <texwt>bbbb3</texwt>
                    <text>bbbb3-1</text>
                    <div>
                        <text>bbbb4</text>
                    </div>
                    <text>bbbb</text>
                    <text>bbbb2</text>
                    <texwt>bbbb3</texwt>
                    <text>bbbb3-1</text>
                    <div>
                        <text>bbbb4</text>
                    </div>
                    <text>bbbb</text>
                    <text>bbbb2</text>
                    <texwt>bbbb3</texwt>
                    <text>bbbb3-1</text>
                    <div>
                        <text>bbbb4</text>
                    </div>
                </weiui_side_menu>

                <weiui_recyler
                        ref="myRecyler"
                        class="recyler"
                        :weiui="{
                        row: 1,
                        pullTips: true,
                        dividerColor: '#ffffff',
                        dividerHeight: '1',
                    }"
                        :swipe="[
                    {
                        text: '选项1',
                        size: '12',
                        padding: '20',
                        color: '#ffff00',
                        backgroundColor: '#ff0000',
                    },{
                        text: '选项2',
                        size: '12',
                        padding: '30',
                        color: '#ecedf0',
                        backgroundColor: '#00ffff',
                    }
                    ]"
                        @itemClick="itemClickRecyler"
                        @itemLongClick="itemLongClickRecyler"
                        @itemSwipeClick="itemSwipeClickRecyler"
                        @pullLoadListener="pullloadListenerRecyler"
                        @refreshListener="refreshListenerRecyler">
                    <div class="panel" v-for="num in lists">
                        <div class="panel-item">
                            <text class="text">{{num}}</text>
                        </div>
                    </div>
                </weiui_recyler>

            </weiui_side_panel>




            <weiui_tabbar
                    ref="myTabbar"
                    class="tabbar"
                    :weiui="tabPagesUI"
                    :pages="tabPages"
                    @refreshListener="refreshListener"
                    @pageSelected="pageSelected"></weiui_tabbar>

        </scroller>

    </div>
</template>

<style scoped>
    .wrapper {
        flex: 1
    }

    .side_panel {
        width: 750px;
        height: 500px;
        background-color: olive;
    }

    .tabbar {
        width: 750px;
        height: 500px
    }

    .recyler {
        width: 750px;
        height: 500px;
    }

    .panel {
        width: 750px;
        height: 110px;
        background-color: #ff9e14;
    }

    .panel-item {
        width: 600px;
        height: 80px;
        margin-left: 75px;
        margin-top: 15px;
        margin-bottom: 15px;
        flex-direction: column;
        justify-content: center;
        background-color: #2af700;
    }

    .text {
        font-size: 50px;
        text-align: center;
        color: #000000;
    }
</style>

<script>
    export default {
        data() {
            return {
                lists: [

                ],

                tabPages: [
                    {
                        title: '页面1',
                        selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                        unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                        url: 'http://dotwe.org/raw/dist/c99b8282d1c822a1eb78dc5bc94c877e.bundle.wx',
                        message: 5
                    },
                    {
                        title: '页面2',
                        selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                        unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                        url: 'http://dotwe.org/raw/dist/e1b7ff7c02bf7898d9f2619d9c1fc966.bundle.wx',
                        dot: true
                    },
                    {
                        title: '页面3',
                        selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                        unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                        url: 'http://dotwe.org/raw/dist/6087fb97013406c0623e86ef7786a532.bundle.wx',
                    },
                ],
                tabPagesUI: {},

                scrollText: "aaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbcccccccccccaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbccccccccccc",
            }
        },
        mounted() {
            setTimeout(() => {
                this.$refs.myTabbar.setCurrentItem(2);
                this.$refs.myTabbar.goUrl(2, "http://dotwe.org/raw/dist/ac78df2f9db68ce3222b164d00458a4f.bundle.wx");
            }, 1000);

            for (let i = 0; i <= 10; i++) {
                this.lists.push(i);
            }

            setTimeout(() => {
                this.lists.splice(1, 1, "aaa")
            }, 1000);

            setTimeout(() => {
                this.scrollText = "http://dotwe.org/raw/dist/ac78df2f9db68ce3222b164d00458a4f.bundle.wx";
            }, 5000);

            this.$refs.myRecyler.setHasMore(true);
        },
        methods: {
            refreshListener(params) {
                this.$refs.myTabbar.setRefreshing(params['position'], false);
            },
            pageSelected(params) {
                console.log("pageSelected", params);
            },
            itemClickScroll(params) {
                console.log("itemClickScroll", params);
            },
            itemClickRecyler(params) {
                console.log("itemClickRecyler", params);
            },
            itemLongClickRecyler(params) {
                console.log("itemLongClickRecyler", params);
            },
            itemSwipeClickRecyler(params) {
                console.log("itemSwipeClickRecyler", params);
            },
            pullloadListenerRecyler(params) {
                if (this.lists.length > 50) {
                    this.$refs.myRecyler.setHasMore(false);
                    return;
                }
                for (let i = 1; i <= 10; i++) {
                    this.lists.push(i);
                }
                this.$refs.myRecyler.pullloaded();
            },
            refreshListenerRecyler(params) {
                let tmp = [];
                for (let i = 1; i <= 11; i++) {
                    tmp.push(i);
                }
                this.lists = tmp;
                this.$refs.myRecyler.setHasMore(true);
                this.$refs.myRecyler.refreshed();
            },
        }
    };
</script>
