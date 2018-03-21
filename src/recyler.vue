<template>
    <div class="box">

        <weiui_recyler
                ref="reflectName"
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
                @itemClick="itemClick"
                @itemSwipeClick="itemSwipeClick"
                @pullLoadListener="pullLoadListener"
                @refreshListener="refreshListener">
            <div class="panel" v-for="num in lists">
                <div class="panel-item">
                    <text class="panel-text">{{num}}</text>
                </div>
            </div>
        </weiui_recyler>

    </div>
</template>

<style scoped>
    .box {
        width: 750px;
        flex: 1
    }

    .recyler {
        width: 750px;
        flex: 1
    }

    .panel {
        width: 750px;
        height: 100px;
    }

    .panel-item {
        width: 750px;
        height: 100px;
        background-color: #ff9e14;
        flex-direction: column;
        justify-content: center;
    }

    .panel-text {
        font-size: 50px;
        text-align: center;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');

    export default {
        data() {
            return {
                lists: [],
            }
        },
        mounted() {
            for (let i = 1; i <= 20; i++) {
                this.lists.push(i);
            }
            this.$refs.reflectName.setHasMore(true);
            //
            setTimeout(() => {
                this.lists.splice(2, 1, "改变第三项文字")
                // splice 详细用法http://www.w3school.com.cn/jsref/jsref_splice.asp
            }, 1000);
        },
        methods: {
            itemClick(params) {
                weiui.toast("点击了" + (params.position + 1) + "项");
            },
            itemSwipeClick(params) {
                weiui.toast("点击了" + (params.position + 1) + "项侧滑的第" + (params.swipePosition + 1) + "个菜单");
            },
            pullLoadListener() {
                let count = this.lists.length;
                if (count >= 100) {
                    this.$refs.reflectName.setHasMore(false);
                    return;
                }
                setTimeout(() => {
                    for (let i = 1; i <= 20; i++) {
                        this.lists.push(count + i);
                    }
                    this.$refs.reflectName.pullloaded();
                    weiui.toast("加载" + (count + 1) + "~" + this.lists.length + "数据成功");
                }, 1000);

            },
            refreshListener() {
                let newList = [];
                for (let i = 1; i <= 20; i++) {
                    newList.push(i);
                }
                setTimeout(() => {
                    this.lists = newList;
                    this.$refs.reflectName.setHasMore(true);
                    this.$refs.reflectName.refreshed();
                    weiui.toast("刷新数据成功");
                }, 1000);

            },
        }
    };
</script>
