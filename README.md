# 开始使用

> 一个基于 [Weex](https://github.com/apache/incubator-weex) 的富交互、轻量级、高性能的 UI 组件库（目前仅支持安卓端）

## 演示

<a href="http://kuaifan.vip/weiui/app/android.apk" target="_blank"><img src="http://kuaifan.vip/weiui/app/android.png?_t=001" width="220px"></a>

<a href="javascript:alert('iOS玩命开发中，请下载Android体验！');"><img src="http://kuaifan.vip/weiui/app/ios.png?_t=001" width="220px"></a>

<img src="http://kuaifan.vip/weiui/app/demo.png" width="640px">

## 支持

* 在你的公司或个人项目中使用 weiui
* 如果你觉得 weui 还不错，可以通过 Star 来表示你的喜欢

## 协议

* 遵循 [MIT 协议](http://opensource.org/licenses/MIT)
* 请自由地享受和参与开源

# Android 接入

> 注意对版本的要求如下（>=）：

* minSdkVersion  = 16
* targetSdkVersion = 27
* supportLibVersion = 27.1.0

## 集成方式

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

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        /**
         * ① 必须在 Application 的 onCreate 方法中执行 weiui.init 来初始化工程
         * 第一个参数：应用程序上下文
         */
        weiui.init(this);
        
         /**
         * ② 这个也是必须的，加载当前应用中集成的所有插件
         */
        WeexPluginContainer.loadAll(this);
    }
}
```
## 使用

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

### 使用效果

![](http://kuaifan.vip/weiui/document/start/media/ezgif-5-c26b50f717.gif)

# iOS 接入
> `weiui` 暂不支持iOS系统

# H5 接入
> `weiui` 暂不支持H5系统


