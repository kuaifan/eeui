<template>

    <div class="app">

        <weiui_navbar>
            <weiui_navbar_item type="back"></weiui_navbar_item>
            <weiui_navbar_item type="title">
                <text class="title">分页网格容器</text>
            </weiui_navbar_item>
            <weiui_navbar_item type="right" @click="viewCode('component/weiui_grid')">
                <weiui_icon icon="code-working" class="iconr"></weiui_icon>
            </weiui_navbar_item>
        </weiui_navbar>

        <weiui_grid class="grid">

            <div class="grid-item" v-for="item in gridLists">
                <image class="item-image" resize="cover"
                       :src="'http://kuaifan.vip/weiui/assets/grid/' + item + '.jpg'"></image>
                <text class="item-title">{{item}}</text>
            </div>

        </weiui_grid>

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

    .grid {
        width: 750px;
        margin-top: 10px;
        height: 570px;
    }

    .grid-item {
        width: 250px;
        height: 180px;
        align-items: center;
    }

    .item-image {
        margin-top: 10px;
        width: 120px;
        height: 120px;
        border-radius: 90px;
    }

    .item-title {
        width: 250px;
        height: 50px;
        line-height: 50px;
        text-align: center;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                gridLists: ["grid_1", "grid_2", "grid_3", "grid_4", "grid_5", "grid_6", "grid_7", "grid_8", "grid_9", "grid_10", "grid_11", "grid_12", "grid_13", "grid_14", "grid_15", "grid_16", "grid_17", "grid_18", "grid_19", "grid_20", "grid_21", "grid_22", "grid_23", "grid_24", "grid_25", "grid_26", "grid_27", "grid_28", "grid_29", "grid_30", "grid_31", "grid_32"],
            }
        },

        methods: {
            viewCode(str) {
                weiui.openPage({
                    url: "http://kuaifan.vip/weiui/#/" + str,
                    pageType: 'web'
                });
            },
        }
    };
</script>