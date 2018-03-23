<template>
    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">图片选择器</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('module/third/pictureSelector/install')">
                <weiui_icon icon="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <div class="content">

            <weiui_list v-if="lists.length > 0"
                        :style="{width:'750px', height: (Math.ceil(lists.length / 5) * 150) + 'px'}"
                        :weiui="{row:5,pullTips:false}">
                <div v-for="(item, position) in lists" class="imgbox" @click="pictureView(position)">
                    <image :src="'file://' + item.path" class="image" resize="cover"></image>
                </div>
            </weiui_list>

            <text class="button" @click="openPicture">选择照片</text>
            <text v-if="lists.length > 0" class="button2" @click="lists=[]">清空选择</text>

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

    .imgbox {
        width: 150px;
        height: 150px;
    }

    .image {
        width: 130px;
        height: 130px;
        margin-top: 10px;
        margin-bottom: 10px;
        margin-right: 10px;
        margin-left: 10px;
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

    .button2 {
        margin-top: 20px;
        color: #00B4FF;
        border-bottom-width: 1px;
        border-bottom-style: solid;
        border-bottom-color: #ff9b39;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');
    const weiui_picture = weex.requireModule('weiui_picture');

    export default {
        data() {
            return {
                lists: []
            }
        },
        methods: {
            viewCode(str) {
                weiui.openPage({
                    url: "http://kuaifan.vip/weiui/#/" + str,
                    pageType: 'web'
                });
            },
            openPicture() {
                const weiui_picture = weex.requireModule('weiui_picture');
                weiui_picture.create({
                    gallery: 1,
                    selected: this.lists
                }, (result) => {
                    console.log("aaaaaaaaaa", result);
                    if (result.status == "success") {
                        this.lists = result.lists;
                    }
                });
            },
            pictureView(position) {
                weiui_picture.picturePreview(position, this.lists);
            }
        }
    };
</script>
