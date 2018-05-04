# locationTool

> 需要的模块

```js
const weiui = weex.requireModule('weiui');
```

## 判断Gps是否可用
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.locationTool('isGpsEnabled')
```

## 判断定位是否可用
```js
/**
* @参数一      固定值
* 
* @返回 true|false
 */
let variable = weiui.locationTool('isLocationEnabled')
```

## 打开Gps设置界面
```js
/**
* @参数一      固定值
 */
weiui.locationTool('openGpsSettings')
```

## 注册Location
```js
/**
* @参数一      固定值
* @参数二      位置信息更新周期（单位：毫秒）
* @参数三      位置变化最小距离：当位置距离变化超过此值时，将更新位置信息（单位：米）
* @参数四      位置刷新的回调接口
* 
* @返回 true|false
 */
let variable = weiui.locationTool('registerLocation', minTime, minDistance, callback(result))
```

> `参数四`回调`result`说明

```js
{
    status: 'lastKnown',  //状态
    //lastKnown：最后一次保留的坐标，changed：坐标改变，unregister：注销Location
     
    lat: '',    //纬度
    long: '',   //经度
}
```

## 注销Location
```js
/**
* @参数一      固定值
 */
weiui.locationTool('unRegisterLocation')
```

## 根据经纬度获取地理位置
```js
/**
* @参数一      固定值
* @参数二      纬度
* @参数三      经度
* 
* @返回 JSON
 */
let variable = weiui.locationTool('getAddress', latitude, longitude)
```

## 根据经纬度获取所在国家
```js
/**
* @参数一      固定值
* @参数二      纬度
* @参数三      经度
* 
* @返回 String
 */
let variable = weiui.locationTool('getCountryName', latitude, longitude)
```

## 根据经纬度获取所在地
```js
/**
* @参数一      固定值
* @参数二      纬度
* @参数三      经度
* 
* @返回 String
 */
let variable = weiui.locationTool('getLocality', latitude, longitude)
```

## 根据经纬度获取所在街道
```js
/**
* @参数一      固定值
* @参数二      纬度
* @参数三      经度
* 
* @返回 String
 */
let variable = weiui.locationTool('getStreet', latitude, longitude)
```


