package com.lljjcoder.weiui.ui.module;

import android.graphics.Color;
import android.os.Handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.weex.plugin.annotation.WeexModule;
import com.lljjcoder.weiui.library.city.CityPickerView;
import com.lljjcoder.weiui.library.city.bean.CityBean;
import com.lljjcoder.weiui.library.city.bean.DistrictBean;
import com.lljjcoder.weiui.library.city.bean.ProvinceBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.extend.module.weiuiJson;


@WeexModule(name = "weiui_citypicker")
public class weiuiCitypickerModule extends WXModule {

    private static final String TAG = "weiuiCitypickerModule";

    private Handler mHandler = new Handler();

    private CityPickerView cityPicker;

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 选择地址
     * @param object
     * @param callback
     */
    @JSMethod
    public void select(String object, final JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        final String province = weiuiJson.getString(json, "province");
        final String city = weiuiJson.getString(json, "city");
        final String area = weiuiJson.getString(json, "area");
        //
        if (cityPicker == null) {
            cityPicker = new CityPickerView.Builder(mWXSDKInstance.getContext()).textSize(20)
                    .titleTextColor("#000000")
                    .backgroundPop(0xa0000000)
                    .province(province)
                    .city(city)
                    .district(area)
                    .textColor(Color.parseColor("#000000"))
                    .provinceCyclic(true)
                    .cityCyclic(false)
                    .districtCyclic(false)
                    .visibleItemsCount(7)
                    .itemPadding(10)
                    .build();
        }
        cityPicker.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                if (callback != null) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("status", "success");
                    data.put("province", province.getName());
                    data.put("city", city.getName());
                    data.put("area", district != null ? district.getName() : "");
                    callback.invoke(data);
                }
            }

            @Override
            public void onCancel() {
                if (callback != null) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("status", "cancel");
                    data.put("province", province);
                    data.put("city", city);
                    data.put("area", area);
                    callback.invoke(data);
                }
            }
        });
        cityPicker.setProvince(province);
        cityPicker.setCity(city);
        cityPicker.setDistrict(area);
        cityPicker.show();
    }
}
