package com.example.hadop;

public class Custom_list_data {
    private String name;
    private String symptoms;
    private String date;
    private String srnum;

    public Custom_list_data(String srnum,String name, String symptoms, String date){
        this.name=name;
        this.symptoms=symptoms;
        this.date=date;
        this.srnum=srnum;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
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
