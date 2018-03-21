# weiui_tabbar

> `<weiui_tabbar>`标签页`tabPages`模式预览效果+示例代码

## 预览效果

![](media/ezgif-5-49d4aa7836.gif)

## 示例代码

```vue
<template>
    <div class="app">

        <weiui_tabbar
                ref="reflectName"
                class="tabbar"
                :weiui="{ tabType: 'top' }"
                :tabPages="tabPages"></weiui_tabbar>

    </div>
</template>

<style>
    .app {
        flex: 1
    }
    .tabbar {
        flex: 1;
        width: 750px;
    }
</style>

<script>
    export default {
        data() {
            return {
                tabPages: [
                    {
                        title: '首页',
                        url: 'http://dotwe.org/raw/dist/b5fd96d8d790f0100bdfc20b93eedf09.bundle.wx',
                    },
                    {
                        title: '好友',
                        url: 'http://dotwe.org/raw/dist/ba938c9aaebe41e5f60b98f90bd0bf61.bundle.wx',
                        message: 9
                    },
                    {
                        title: '圈子',
                        url: 'http://dotwe.org/raw/dist/fb6f016b0116969b6b503e1d3a35285f.bundle.wx',
                        message: 18
                    },
                    {
                        title: '设置',
                        url: 'http://dotwe.org/raw/dist/ad0045a7cff0b3a680d9de6dd4806e81.bundle.wx',
                        dot: true
                    },
                ],
            }
        }
    };
</script>
```


