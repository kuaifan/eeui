# 安装接入

## 简介

融云通讯能力库 - IMLib

## Android 接入

### 1、下载SDK

SDK已上传至github，[https://github.com/kuaifan/weiui](https://github.com/kuaifan/weiui)，`weiui_rongim`SDK路径为：library/weiui_rongim

### 2、导入SDK

![](media/01.png)
![](media/02.png)
![](media/03.png)
![](media/04.png)
![](media/05.png)
![](media/06.png)
![](media/07.png)


### 3、初始化SDK

> 可在应用中任何地方执行 weiui_rongim.init 来初始化工程，**但必须在 weiui.init 之后**

```js
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    
        weiui.init(this);
        
         /**
         * 必须在【weiui.init】之后执行 weiui_rongim.init 来初始化工程
         * 第一个参数：融云注册应用的AppKey
         * 第一个参数：融云注册应用的AppSecret
         */
        weiui_rongim.init(String appKey, String appSecret);
        
    }
}
```


