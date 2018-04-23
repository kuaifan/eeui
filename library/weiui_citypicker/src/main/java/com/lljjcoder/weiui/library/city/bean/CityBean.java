package com.lljjcoder.weiui.library.city.bean;

import java.util.ArrayList;

/**
 * @2Do:
 * @Author M2
 * @Version v ${VERSION}
 * @Date 2017/7/7 0007.
 */
public class CityBean  {
    

    private String id; /*110101*/
    
    private String name; /*东城区*/
    
    private String pinYin; /*Dongcheng*/
    
    private Double gisGcj02Lat; /*39.9288*/
    
    private Double gisGcj02Lng; /*116.416*/
    
    private Double gisBd09Lat; /*39.935*/
    
    private Double gisBd09Lng; /*116.422*/
    
    private String zipcode;
    
    private ArrayList<DistrictBean> cityList;
    
    public String getId() {
        return id == null ? "" : id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name == null ? "" : name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPinYin() {
        return pinYin == null ? "" : pinYin;
    }
    
    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }
    
    public Double getGisGcj02Lat() {
        return gisGcj02Lat == null ? new Double(0.0d) : gisGcj02Lat;
    }
    
    public void setGisGcj02Lat(Double gisGcj02Lat) {
        this.gisGcj02Lat = gisGcj02Lat;
    }
    
    public Double getGisGcj02Lng() {
        return gisGcj02Lng == null ? new Double(0.0d) : gisGcj02Lng;
    }
    
    public void setGisGcj02Lng(Double gisGcj02Lng) {
        this.gisGcj02Lng = gisGcj02Lng;
    }
    
    public Double getGisBd09Lat() {
        return gisBd09Lat == null ? new Double(0.0d) : gisBd09Lat;
    }
    
    public void setGisBd09Lat(Double gisBd09Lat) {
        this.gisBd09Lat = gisBd09Lat;
    }
    
    public Double getGisBd09Lng() {
        return gisBd09Lng == null ? new Double(0.0d) : gisBd09Lng;
    }
    
    public void setGisBd09Lng(Double gisBd09Lng) {
        this.gisBd09Lng = gisBd09Lng;
    }
    
    public String getZipcode() {
        return zipcode == null ? "" : zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public ArrayList<DistrictBean> getCityList() {
        return cityList;
    }
    
    public void setCityList(ArrayList<DistrictBean> cityList) {
        this.cityList = cityList;
    }


    @Override
    public String toString() {
        return  name ;
    }
}
