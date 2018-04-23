package com.lljjcoder.weiui.library.citylist.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 作者：liji on 2017/5/19 17:07
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class CityInfoBean implements Parcelable {
    private int id;//城市Id
    private String name;//城市名称

    private String latitude;//纬度
    private String longitude;//经度
    private String fistLetter;//首字母
    private int sort;//排序字段

    public CityInfoBean( ) {
    }

    public static CityInfoBean findCity(List<CityInfoBean> list, String cityName) {
        try {
            for (int i = 0; i < list.size(); i++) {
                CityInfoBean city = list.get(i);
                if (cityName.equals(city.getName()))
                    return city;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude == null ? "" : latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude == null ? "" : longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFistLetter() {
        return fistLetter == null ? "" : fistLetter;
    }

    public void setFistLetter(String fistLetter) {
        this.fistLetter = fistLetter;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "CityInfoBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", fistLetter='" + fistLetter + '\'' +
                ", sort=" + sort +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.fistLetter);
        dest.writeInt(this.sort);
    }

    protected CityInfoBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.fistLetter = in.readString();
        this.sort = in.readInt();
    }

    public static final Parcelable.Creator<CityInfoBean> CREATOR = new Parcelable.Creator<CityInfoBean>() {
        @Override
        public CityInfoBean createFromParcel(Parcel source) {
            return new CityInfoBean(source);
        }

        @Override
        public CityInfoBean[] newArray(int size) {
            return new CityInfoBean[size];
        }
    };
}
