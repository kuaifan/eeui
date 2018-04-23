package com.lljjcoder.weiui.library.city;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lljjcoder.weiui.library.city.bean.CityBean;
import com.lljjcoder.weiui.library.city.bean.DistrictBean;
import com.lljjcoder.weiui.library.city.bean.ProvinceBean;
import com.lljjcoder.weiui.library.citypickerview.R;
import com.lljjcoder.weiui.library.citypickerview.widget.CanShow;
import com.lljjcoder.weiui.library.citypickerview.widget.wheel.OnWheelChangedListener;
import com.lljjcoder.weiui.library.citypickerview.widget.wheel.WheelView;
import com.lljjcoder.weiui.library.citypickerview.widget.wheel.adapters.ArrayWheelAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 省市区三级选择
 * 作者：liji on 2015/12/17 10:40
 * 邮箱：lijiwork@sina.com
 */
public class CityPickerView implements CanShow, OnWheelChangedListener {
    
    private Context context;
    
    private PopupWindow popwindow;
    
    private View popview;
    
    private WheelView mViewProvince;
    
    private WheelView mViewCity;
    
    private WheelView mViewDistrict;
    
    private RelativeLayout mRelativeTitleBg;
    
    private TextView mTvOK;
    
    private TextView mTvTitle;
    
    private TextView mTvCancel;
    
    //省份数据
    ArrayList<ProvinceBean> mProvinceBeanArrayList = new ArrayList<>();
    
    //城市数据
    ArrayList<ArrayList<CityBean>> mCityBeanArrayList;
    
    //地区数据
    ArrayList<ArrayList<ArrayList<DistrictBean>>> mDistrictBeanArrayList;
    
    private ProvinceBean[] mProvinceBeenArray;
    
    private ProvinceBean mProvinceBean;
    
    private CityBean mCityBean;
    
    private DistrictBean mDistrictBean;
    
    /**
     * key - 省 value - 市
     */
    protected Map<String, CityBean[]> mPro_CityMap = new HashMap<String, CityBean[]>();
    
    /**
     * key - 市 values - 区
     */
    protected Map<String, DistrictBean[]> mCity_DisMap = new HashMap<String, DistrictBean[]>();
    
    /**
     * key - 区 values - 邮编
     */
    protected Map<String, DistrictBean> mDisMap = new HashMap<String, DistrictBean>();

    private OnCityItemClickListener listener;
    
    public interface OnCityItemClickListener {
        void onSelected(ProvinceBean province, CityBean city, DistrictBean district);
        
        void onCancel();
    }
    
    public void setOnCityItemClickListener(OnCityItemClickListener listener) {
        this.listener = listener;
    }
    
    /**
     * Default text color
     */
    public static final int DEFAULT_TEXT_COLOR = 0xFF585858;
    
    /**
     * Default text size
     */
    public static final int DEFAULT_TEXT_SIZE = 18;
    
    // Text settings
    private int textColor = DEFAULT_TEXT_COLOR;
    
    private int textSize = DEFAULT_TEXT_SIZE;
    
    /**
     * 滚轮显示的item个数
     */
    private static final int DEF_VISIBLE_ITEMS = 5;
    
    // Count of visible items
    private int visibleItems = DEF_VISIBLE_ITEMS;
    
    /**
     * 省滚轮是否循环滚动
     */
    private boolean isProvinceCyclic = true;
    
    /**
     * 市滚轮是否循环滚动
     */
    private boolean isCityCyclic = true;
    
    /**
     * 区滚轮是否循环滚动
     */
    private boolean isDistrictCyclic = true;
    
    /**
     * item间距
     */
    private int padding = 5;
    
    /**
     * Color.BLACK
     */
    private String cancelTextColorStr = "#000000";
    
    /**
     * Color.BLUE
     */
    private String confirmTextColorStr = "#0000FF";
    
    /**
     * 标题背景颜色
     */
    private String titleBackgroundColorStr = "#E9E9E9";
    
    /**
     * 标题颜色
     */
    private String titleTextColorStr = "#E9E9E9";
    
    /**
     * 第一次默认的显示省份，一般配合定位，使用
     */
    private String defaultProvinceName = "江苏";
    
    /**
     * 第一次默认得显示城市，一般配合定位，使用
     */
    private String defaultCityName = "常州";
    
    /**
     * 第一次默认得显示，一般配合定位，使用
     */
    private String defaultDistrict = "新北区";
    
    /**
     * 两级联动
     */
    private boolean showProvinceAndCity = false;
    
    /**
     * 标题
     */
    private String mTitle = "选择地区";
    
    /**
     * 设置popwindow的背景
     */
    private int backgroundPop = 0xa0000000;
    
    private CityPickerView(Builder builder) {
        this.textColor = builder.textColor;
        this.textSize = builder.textSize;
        this.visibleItems = builder.visibleItems;
        this.isProvinceCyclic = builder.isProvinceCyclic;
        this.isDistrictCyclic = builder.isDistrictCyclic;
        this.isCityCyclic = builder.isCityCyclic;
        this.context = builder.mContext;
        this.padding = builder.padding;
        this.mTitle = builder.mTitle;
        this.titleBackgroundColorStr = builder.titleBackgroundColorStr;
        this.confirmTextColorStr = builder.confirmTextColorStr;
        this.cancelTextColorStr = builder.cancelTextColorStr;
        
        this.defaultDistrict = builder.defaultDistrict;
        this.defaultCityName = builder.defaultCityName;
        this.defaultProvinceName = builder.defaultProvinceName;
        
        this.showProvinceAndCity = builder.showProvinceAndCity;
        this.backgroundPop = builder.backgroundPop;
        this.titleTextColorStr = builder.titleTextColorStr;
        
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        popview = layoutInflater.inflate(R.layout.pop_citypicker, null);
        
        mViewProvince = (WheelView) popview.findViewById(R.id.id_province);
        mViewCity = (WheelView) popview.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) popview.findViewById(R.id.id_district);
        mRelativeTitleBg = (RelativeLayout) popview.findViewById(R.id.rl_title);
        mTvOK = (TextView) popview.findViewById(R.id.tv_confirm);
        mTvTitle = (TextView) popview.findViewById(R.id.tv_title);
        mTvCancel = (TextView) popview.findViewById(R.id.tv_cancel);
        
        popwindow = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popwindow.setBackgroundDrawable(new ColorDrawable(backgroundPop));
        popwindow.setAnimationStyle(R.style.AnimBottom);
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(false);
        popwindow.setFocusable(true);
        popwindow.setClippingEnabled(false);

        /**
         * 设置标题背景颜色
         */
        if (!TextUtils.isEmpty(this.titleBackgroundColorStr)) {
            mRelativeTitleBg.setBackgroundColor(Color.parseColor(this.titleBackgroundColorStr));
        }
        
        /**
         * 设置标题
         */
        if (!TextUtils.isEmpty(this.mTitle)) {
            mTvTitle.setText(this.mTitle);
        }
        
        //设置确认按钮文字颜色
        if (!TextUtils.isEmpty(this.titleTextColorStr)) {
            mTvTitle.setTextColor(Color.parseColor(this.titleTextColorStr));
        }
        
        //设置确认按钮文字颜色
        if (!TextUtils.isEmpty(this.confirmTextColorStr)) {
            mTvOK.setTextColor(Color.parseColor(this.confirmTextColorStr));
        }
        
        //设置取消按钮文字颜色
        if (!TextUtils.isEmpty(this.cancelTextColorStr)) {
            mTvCancel.setTextColor(Color.parseColor(this.cancelTextColorStr));
        }
        
        //只显示省市两级联动
        if (this.showProvinceAndCity) {
            mViewDistrict.setVisibility(View.GONE);
        }
        else {
            mViewDistrict.setVisibility(View.VISIBLE);
        }
        
        initProvinceDatas(context);

        
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancel();
                hide();
            }
        });
        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelected(mProvinceBean, mCityBean, mDistrictBean);
                hide();
            }
        });
        
    }
    
    public static class Builder {
        /**
         * Default text color
         */
        public static final int DEFAULT_TEXT_COLOR = 0xFF585858;
        
        /**
         * Default text size
         */
        public static final int DEFAULT_TEXT_SIZE = 18;
        
        // Text settings
        private int textColor = DEFAULT_TEXT_COLOR;
        
        private int textSize = DEFAULT_TEXT_SIZE;
        
        /**
         * 滚轮显示的item个数
         */
        private static final int DEF_VISIBLE_ITEMS = 5;
        
        // Count of visible items
        private int visibleItems = DEF_VISIBLE_ITEMS;
        
        /**
         * 省滚轮是否循环滚动
         */
        private boolean isProvinceCyclic = true;
        
        /**
         * 市滚轮是否循环滚动
         */
        private boolean isCityCyclic = true;
        
        /**
         * 区滚轮是否循环滚动
         */
        private boolean isDistrictCyclic = true;
        
        private Context mContext;
        
        /**
         * item间距
         */
        private int padding = 5;
        
        /**
         * Color.BLACK
         */
        private String cancelTextColorStr = "#000000";
        
        /**
         * Color.BLUE
         */
        private String confirmTextColorStr = "#0000FF";
        
        /**
         * 标题背景颜色
         */
        private String titleBackgroundColorStr = "#E9E9E9";
        
        /**
         * 标题颜色
         */
        private String titleTextColorStr = "#E9E9E9";
        
        /**
         * 第一次默认的显示省份，一般配合定位，使用
         */
        private String defaultProvinceName = "江苏";
        
        /**
         * 第一次默认得显示城市，一般配合定位，使用
         */
        private String defaultCityName = "常州";
        
        /**
         * 第一次默认得显示，一般配合定位，使用
         */
        private String defaultDistrict = "新北区";
        
        /**
         * 标题
         */
        private String mTitle = "选择地区";
        
        /**
         * 两级联动
         */
        private boolean showProvinceAndCity = false;
        
        /**
         * 设置popwindow的背景
         */
        private int backgroundPop = 0xa0000000;

        public Builder(Context context) {
            this.mContext = context;
        }
        
        /**
         * 设置popwindow的背景
         *
         * @param backgroundPopColor
         * @return
         */
        public Builder backgroundPop(int backgroundPopColor) {
            this.backgroundPop = backgroundPopColor;
            return this;
        }
        
        /**
         * 设置标题背景颜色
         *
         * @param colorBg
         * @return
         */
        public Builder titleBackgroundColor(String colorBg) {
            this.titleBackgroundColorStr = colorBg;
            return this;
        }
        
        /**
         * 设置标题背景颜色
         *
         * @param titleTextColorStr
         * @return
         */
        public Builder titleTextColor(String titleTextColorStr) {
            this.titleTextColorStr = titleTextColorStr;
            return this;
        }
        
        /**
         * 设置标题
         *
         * @param mtitle
         * @return
         */
        public Builder title(String mtitle) {
            this.mTitle = mtitle;
            return this;
        }
        
        /**
         * 是否只显示省市两级联动
         *
         * @param flag
         * @return
         */
        public Builder onlyShowProvinceAndCity(boolean flag) {
            this.showProvinceAndCity = flag;
            return this;
        }
        
        /**
         * 第一次默认的显示省份，一般配合定位，使用
         *
         * @param defaultProvinceName
         * @return
         */
        public Builder province(String defaultProvinceName) {
            this.defaultProvinceName = defaultProvinceName;
            return this;
        }
        
        /**
         * 第一次默认得显示城市，一般配合定位，使用
         *
         * @param defaultCityName
         * @return
         */
        public Builder city(String defaultCityName) {
            this.defaultCityName = defaultCityName;
            return this;
        }
        
        /**
         * 第一次默认地区显示，一般配合定位，使用
         *
         * @param defaultDistrict
         * @return
         */
        public Builder district(String defaultDistrict) {
            this.defaultDistrict = defaultDistrict;
            return this;
        }
        
        //        /**
        //         * 确认按钮文字颜色
        //         * @param color
        //         * @return
        //         */
        //        public Builder confirTextColor(int color) {
        //            this.confirmTextColor = color;
        //            return this;
        //        }
        
        /**
         * 确认按钮文字颜色
         *
         * @param color
         * @return
         */
        public Builder confirTextColor(String color) {
            this.confirmTextColorStr = color;
            return this;
        }
        
        //        /**
        //         * 取消按钮文字颜色
        //         * @param color
        //         * @return
        //         */
        //        public Builder cancelTextColor(int color) {
        //            this.cancelTextColor = color;
        //            return this;
        //        }
        
        /**
         * 取消按钮文字颜色
         *
         * @param color
         * @return
         */
        public Builder cancelTextColor(String color) {
            this.cancelTextColorStr = color;
            return this;
        }
        
        /**
         * item文字颜色
         *
         * @param textColor
         * @return
         */
        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }
        
        /**
         * item文字大小
         *
         * @param textSize
         * @return
         */
        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }
        
        /**
         * 滚轮显示的item个数
         *
         * @param visibleItems
         * @return
         */
        public Builder visibleItemsCount(int visibleItems) {
            this.visibleItems = visibleItems;
            return this;
        }
        
        /**
         * 省滚轮是否循环滚动
         *
         * @param isProvinceCyclic
         * @return
         */
        public Builder provinceCyclic(boolean isProvinceCyclic) {
            this.isProvinceCyclic = isProvinceCyclic;
            return this;
        }
        
        /**
         * 市滚轮是否循环滚动
         *
         * @param isCityCyclic
         * @return
         */
        public Builder cityCyclic(boolean isCityCyclic) {
            this.isCityCyclic = isCityCyclic;
            return this;
        }
        
        /**
         * 区滚轮是否循环滚动
         *
         * @param isDistrictCyclic
         * @return
         */
        public Builder districtCyclic(boolean isDistrictCyclic) {
            this.isDistrictCyclic = isDistrictCyclic;
            return this;
        }
        
        /**
         * item间距
         *
         * @param itemPadding
         * @return
         */
        public Builder itemPadding(int itemPadding) {
            this.padding = itemPadding;
            return this;
        }
        
        public CityPickerView build() {
            CityPickerView cityPicker = new CityPickerView(this);
            return cityPicker;
        }
        
    }
    
    private void setUpData() {
        int provinceDefault = -1;
        if (!TextUtils.isEmpty(defaultProvinceName) && mProvinceBeenArray.length > 0) {
            for (int i = 0; i < mProvinceBeenArray.length; i++) {
                if (mProvinceBeenArray[i].getName().contains(defaultProvinceName)) {
                    provinceDefault = i;
                    break;
                }
            }
        }
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<ProvinceBean>(context, mProvinceBeenArray);
        mViewProvince.setViewAdapter(arrayWheelAdapter);
        //获取所设置的省的位置，直接定位到该位置
        if (-1 != provinceDefault) {
            mViewProvince.setCurrentItem(provinceDefault);
        }
        // 设置可见条目数量
        mViewProvince.setVisibleItems(visibleItems);
        mViewCity.setVisibleItems(visibleItems);
        mViewDistrict.setVisibleItems(visibleItems);
        mViewProvince.setCyclic(isProvinceCyclic);
        mViewCity.setCyclic(isCityCyclic);
        mViewDistrict.setCyclic(isDistrictCyclic);
        arrayWheelAdapter.setPadding(padding);
        arrayWheelAdapter.setTextColor(textColor);
        arrayWheelAdapter.setTextSize(textSize);
        
        updateCities();
        updateAreas();
    }
    
    /**
     * 解析省市区的XML数据
     */
    
    protected void initProvinceDatas(Context context) {
        
        String cityJson = utils.getJson(context, "city.json");
        Type type = new TypeToken<ArrayList<ProvinceBean>>() {
        }.getType();
        
        mProvinceBeanArrayList = new Gson().fromJson(cityJson, type);
        mCityBeanArrayList = new ArrayList<>(mProvinceBeanArrayList.size());
        mDistrictBeanArrayList = new ArrayList<>(mProvinceBeanArrayList.size());
        
        //*/ 初始化默认选中的省、市、区，默认选中第一个省份的第一个市区中的第一个区县
        if (mProvinceBeanArrayList != null && !mProvinceBeanArrayList.isEmpty()) {
            mProvinceBean = mProvinceBeanArrayList.get(0);
            List<CityBean> cityList = mProvinceBean.getCityList();
            if (cityList != null && !cityList.isEmpty() && cityList.size() > 0) {
                mCityBean = cityList.get(0);
                List<DistrictBean> districtList = mCityBean.getCityList();
                if (districtList != null && !districtList.isEmpty() && districtList.size() > 0) {
                    mDistrictBean = districtList.get(0);
                }
            }
        }
        
        //省份数据
        mProvinceBeenArray = new ProvinceBean[mProvinceBeanArrayList.size()];
        
        for (int p = 0; p < mProvinceBeanArrayList.size(); p++) {
            
            //遍历每个省份
            ProvinceBean itemProvince = mProvinceBeanArrayList.get(p);
            
            //每个省份对应下面的市
            ArrayList<CityBean> cityList = itemProvince.getCityList();
            
            //当前省份下面的所有城市
            CityBean[] cityNames = new CityBean[cityList.size()];
            
            //遍历当前省份下面城市的所有数据
            for (int j = 0; j < cityList.size(); j++) {
                cityNames[j] = cityList.get(j);
                
                //当前省份下面每个城市下面再次对应的区或者县
                List<DistrictBean> districtList = cityList.get(j).getCityList();
                
                DistrictBean[] distrinctArray = new DistrictBean[districtList.size()];
                
                for (int k = 0; k < districtList.size(); k++) {
                    
                    // 遍历市下面所有区/县的数据
                    DistrictBean districtModel = districtList.get(k);
                    
                    //存放 省市区-区 数据
                    mDisMap.put(itemProvince.getName() + cityNames[j].getName() + districtList.get(k).getName(),
                            districtModel);
                    
                    distrinctArray[k] = districtModel;
                    
                }
                // 市-区/县的数据，保存到mDistrictDatasMap
                mCity_DisMap.put(itemProvince.getName() + cityNames[j].getName(), distrinctArray);
                
            }
            
            // 省-市的数据，保存到mCitisDatasMap
            mPro_CityMap.put(itemProvince.getName(), cityNames);
            
            mCityBeanArrayList.add(cityList);
            
            ArrayList<ArrayList<DistrictBean>> array2DistrictLists = new ArrayList<>(cityList.size());
            
            for (int c = 0; c < cityList.size(); c++) {
                CityBean cityBean = cityList.get(c);
                array2DistrictLists.add(cityBean.getCityList());
            }
            mDistrictBeanArrayList.add(array2DistrictLists);
            
            //赋值所有省份的名称
            mProvinceBeenArray[p] = itemProvince;
            
        }
    }
    
    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        //省份滚轮滑动的当前位置
        int pCurrent = mViewProvince.getCurrentItem();
        //省份选中的名称
        mProvinceBean = mProvinceBeenArray[pCurrent];
        
        CityBean[] cities = mPro_CityMap.get(mProvinceBean.getName());
        if (cities == null) {
            return;
        }
        
        //设置最初的默认城市
        int cityDefault = -1;
        if (!TextUtils.isEmpty(defaultCityName) && cities.length > 0) {
            for (int i = 0; i < cities.length; i++) {
                if (defaultCityName.contains(cities[i].getName())) {
                    cityDefault = i;
                    break;
                }
            }
        }
        
        ArrayWheelAdapter cityWheel = new ArrayWheelAdapter<CityBean>(context, cities);
        // 设置可见条目数量
        cityWheel.setTextColor(textColor);
        cityWheel.setTextSize(textSize);
        mViewCity.setViewAdapter(cityWheel);
        if (-1 != cityDefault) {
            mViewCity.setCurrentItem(cityDefault);
        }
        else {
            mViewCity.setCurrentItem(0);
        }
        
        cityWheel.setPadding(padding);
        updateAreas();
    }
    
    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        
        int pCurrent = mViewCity.getCurrentItem();
        
        mCityBean = mPro_CityMap.get(mProvinceBean.getName())[pCurrent];
        
        DistrictBean[] areas = mCity_DisMap.get(mProvinceBean.getName() + mCityBean.getName());
        
        if (areas == null) {
            return;
        }
        
        int districtDefault = -1;
        if (!TextUtils.isEmpty(defaultDistrict) && areas.length > 0) {
            for (int i = 0; i < areas.length; i++) {
                if (defaultDistrict.contains(areas[i].getName())) {
                    districtDefault = i;
                    break;
                }
            }
        }
        
        ArrayWheelAdapter districtWheel = new ArrayWheelAdapter<DistrictBean>(context, areas);
        // 设置可见条目数量
        districtWheel.setTextColor(textColor);
        districtWheel.setTextSize(textSize);
        mViewDistrict.setViewAdapter(districtWheel);
        
        if (-1 != districtDefault) {
            mViewDistrict.setCurrentItem(districtDefault);
            //获取第一个区名称
            mDistrictBean = mDisMap.get(mProvinceBean.getName() + mCityBean.getName() + defaultDistrict);
        }
        else {
            mViewDistrict.setCurrentItem(0);
            if (areas.length > 0) {
                mDistrictBean = areas[0];
            }
        }
        districtWheel.setPadding(padding);
        
    }
    
    @Override
    public void setType(int type) {
    }
    
    @Override
    public void show() {
        if (!isShow()) {
            setUpData();
            popwindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);
        }
    }
    
    @Override
    public void hide() {
        if (isShow()) {
            popwindow.dismiss();
        }
    }
    
    @Override
    public boolean isShow() {
        return popwindow.isShowing();
    }
    
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        }
        else if (wheel == mViewCity) {
            updateAreas();
        }
        else if (wheel == mViewDistrict) {
            mDistrictBean = mCity_DisMap.get(mProvinceBean.getName() + mCityBean.getName())[newValue];
        }
    }

    public void setProvince(String var) {
        this.defaultProvinceName = var;
    }

    public void setCity(String var) {
        this.defaultCityName = var;
    }

    public void setDistrict(String var) {
        this.defaultDistrict = var;
    }
}
