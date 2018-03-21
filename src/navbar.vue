<template>

    <div>

        <!--样式①-->
        <weiui_navbar class="navbar">

            <weiui_navbar_item type="back"></weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text>标题</text>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式②-->
        <weiui_navbar class="navbar" :weiui="{titleType:'left'}">

            <weiui_navbar_item type="back"></weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text>标题</text>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式③-->
        <weiui_navbar class="navbar">

            <weiui_navbar_item type="back"></weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text>标题</text>
            </weiui_navbar_item>

            <weiui_navbar_item type="right">
                <weiui_icon icon="refresh" class="icon"></weiui_icon>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式④-->
        <weiui_navbar class="navbar">

            <weiui_navbar_item type="left">
                <weiui_icon icon="navicon-round" class="icon"></weiui_icon>
            </weiui_navbar_item>

            <weiui_navbar_item type="title">
                <text>标题</text>
            </weiui_navbar_item>

            <weiui_navbar_item type="right">
                <weiui_icon icon="refresh" class="icon"></weiui_icon>
            </weiui_navbar_item>

        </weiui_navbar>

        <!--样式⑤-->
        <weiui_navbar class="navbar">

            <!--返回按钮-->
            <weiui_navbar_item type="back"></weiui_navbar_item>

            <!--左边内容-->
            <weiui_navbar_item type="left">
                <text>左边内容</text>
            </weiui_navbar_item>

            <!--中间内容(标题内容)-->
            <weiui_navbar_item type="title">
                <text>中间(标题)内容</text>
            </weiui_navbar_item>

            <!--右边内容-->
            <weiui_navbar_item type="right">
                <text>右边内容</text>
            </weiui_navbar_item>

        </weiui_navbar>

    </div>

</template>

<style>
    .navbar {
        width: 750px;
        height: 100px;
        margin-bottom: 50px;
    }
    .icon {
        width: 100px;
        height: 100px;
    }
</style>