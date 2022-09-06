package com.example.pyproj;

public class Custom_list_data {
    private String disea_hus;
    private String symptoms;
    private String date;
    private String time;
    private String srnum;

    public Custom_list_data(String srnum, String dise_hi, String symptoms, String date,String time){
        this.disea_hus=dise_hi;
        this.symptoms=symptoms;
        this.date=date;
        this.srnum=srnum;
        this.time=time;
    }

    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

    public String getName() {
        return disea_hus;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String disea_hu) {
        this.disea_hus = disea_hu;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getSrnum() {
        return srnum;
    }

    public void setSrnum(String srnum) {
        this.srnum = srnum;
    }
}
