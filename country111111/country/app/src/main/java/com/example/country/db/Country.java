package com.example.country.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "country")
public class Country {
    @PrimaryKey()
    @NonNull
  public  int c_srnum;
  public  String c_name;
//  public String c_path;
    public   String c_capti;
    public  String c_regi;
    public  String c_subreg;
    public  String c_flag;
    public  String c_borders;
    public  String c_languages;
    public  String c_population;

//    public Country(int srnum, String name,String capti, String regi, String subreg,String flag, String borders,String languages, String population){
//        this.c_srnum=srnum;
//        this.c_name=name;
//        this.c_capti=capti;
//        this.c_regi=regi;
//        this.c_subreg=subreg;
//        this.c_flag=flag;
//        this.c_borders=borders;
//        this.c_languages=languages;
//        this.c_population=population;
//
//    }

    public String getcapti() {
        return c_capti;
    }
    public String getName() {
        return c_name;
    }
    public String getregion() {
        return c_regi;
    }
    public String getsubregion() {
        return c_subreg;
    }
    public String getflag() { return c_flag; }
    public String getborders() { return c_borders; }
    public String getLanguages() { return c_languages; }
    public String getpopulation() { return c_population; }
//    public String getpath() { return c_path; }
    public int getSrnum() { return c_srnum; }


}
