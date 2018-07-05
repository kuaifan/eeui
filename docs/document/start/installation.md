# installation 安装

## Android 接入

> 注意对版本的要求如下（>=）：

* minSdkVersion  = 16
* targetSdkVersion = 27
* supportLibVersion = 27.1.0

### 1.添加 Gradle 依赖
[![Download](https://api.bintray.com/packages/kuaifan/maven/weiui/images/download.svg)](https://bintray.com/kuaifan/maven/weiui/_latestVersion) weiui 后面的「latestVersion」指的是左边这个 Download 徽章后面的「数字」，请自行替换。

```groovy
dependencies {
    ......
    implementation 'vip.kuaifan:weiui:latestVersion'
}
```

以下是使用到的依赖库
```groovy
dependencies {
    ......
    // 依赖库（换成己工程里依赖的版本）
    implementation 'com.android.support:support-v4:27.1.0'
    implementation 'com.android.support:recyclerview-v7:27.1.0'
    implementation 'com.android.support:design:27.1.0'
    implementation 'com.alibaba:fastjson:1.2.46'
    implementation 'com.taobao.android:weex_sdk:0.18.0'
    implementation 'com.taobao.android:weexplugin-loader:1.3'
    implementation 'com.taobao.android:weexplugin-processor:1.3'
}
```

### 2.必须在 Application 的 onCreate 方法中执行 weiui.init 来初始化工程

```js
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        //.....
        
        /**
         * 第一个参数：应用程序上下文
         */
        weiui.init(this);
    }
}
```

## iOS 接入
> `weiui` 暂不支持iOS系统


## 使用示例

```vue
<template>
    <div class="box">

        <weiui_icon class="icon"
                    :weiui="{
                        text: 'refresh',
                        textSize: '50',
                        textClickColor: '#ff0000'
                    }"
                    @click="iconClicked"></weiui_icon>
        <weiui_icon class="icon"
                    text="earth"
                    textSize="50"
                    textClickColor="#00ff00"
                    @click="iconClicked2"></weiui_icon>

    </div>
</template>

<style>
    .box {
        flex: 1;
        flex-direction: row;
        justify-content: center;
        align-items: center;
    }
    .icon {
        width: 200px;
        height: 200px;
        margin-left: 10px;
        margin-right: 10px;
    }
</style>

<script>
    const weiui = weex.requireModule('weiui');

    module.exports = {
        methods: {
            iconClicked() {
                weiui.loading({
                    title: '正在加载...',
                    style: 'rotatingplane',
                    duration: 3000
                });
            },
            iconClicked2() {
                weiui.openWeb("http://kuaifan.vip");
            },
        }
    };
</script>
```

## 使用效果

![](http://kuaifan.vip/weiui/document/start/media/ezgif-5-c26b50f717.gif)


