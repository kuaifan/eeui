package com.lljjcoder.weiui.library.citylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lljjcoder.weiui.library.citylist.bean.CityInfoBean;
import com.lljjcoder.weiui.library.citylist.sortlistview.CharacterParser;
import com.lljjcoder.weiui.library.citylist.sortlistview.PinyinComparator;
import com.lljjcoder.weiui.library.citylist.sortlistview.SideBar;
import com.lljjcoder.weiui.library.citylist.sortlistview.SortAdapter;
import com.lljjcoder.weiui.library.citylist.sortlistview.SortModel;
import com.lljjcoder.weiui.library.citylist.utils.CityUtils;
import com.lljjcoder.weiui.library.citylist.widget.CleanableEditView;
import com.lljjcoder.weiui.library.citypickerview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityListSelectActivity extends AppCompatActivity {

    CleanableEditView mCityTextSearch;
    TextView mCurrentCityTag;
    TextView mCurrentCity;
    TextView mLocalCityTag;
    TextView mLocalCity;
    ListView sortListView;
    TextView mDialog;
    SideBar mSidrbar;
    ImageView imgBack;

    public SortAdapter adapter;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> sourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private List<CityInfoBean> cityListInfo = new ArrayList<>();

    private CityInfoBean cityInfoBean = new CityInfoBean();

    //startActivityForResult flag
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list_select);

        initView();

        initList();

        setCityData(CityUtils.getCityList());
    }

    private void initView() {
        mCityTextSearch = (CleanableEditView) findViewById(R.id.cityInputText);
        mCurrentCityTag = (TextView) findViewById(R.id.currentCityTag);
        mCurrentCity = (TextView) findViewById(R.id.currentCity);
        mLocalCityTag = (TextView) findViewById(R.id.localCityTag);
        mLocalCity = (TextView) findViewById(R.id.localCity);
        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        mDialog = (TextView) findViewById(R.id.dialog);
        mSidrbar = (SideBar) findViewById(R.id.sidrbar);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void setCityData(List<CityInfoBean> cityList) {
        cityListInfo = cityList;
        int count = cityList.size();
        String[] list = new String[count];
        for (int i = 0; i < count; i++)
            list[i] = cityList.get(i).getName();

        sourceDateList.addAll(filledData(list));
        // 根据a-z进行排序源数据
        Collections.sort(sourceDateList, pinyinComparator);
        adapter.notifyDataSetChanged();
    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;
    }


    private void initList() {
        sourceDateList = new ArrayList<SortModel>();
        adapter = new SortAdapter(CityListSelectActivity.this, sourceDateList);
        sortListView.setAdapter(adapter);

        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mSidrbar.setTextView(mDialog);
        //设置右侧触摸监听
        mSidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }
            }
        });

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String cityName = ((SortModel) adapter.getItem(position)).getName();
                cityInfoBean = CityInfoBean.findCity(cityListInfo, cityName);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("cityinfo", cityInfoBean);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //根据输入框输入值的改变来过滤搜索
        mCityTextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = sourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : sourceDateList) {
                String name = sortModel.getName();
                if (name.contains(filterStr) || characterParser.getSelling(name).startsWith(filterStr)) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

}
