package com.example.country;

public class Custom_list_data {
    String name;
    String capti;
    String regi;
    String subreg;
    String flag;
    String borders;
    String languages;
    String population,srnum;

    public Custom_list_data(String srnum, String name,String capti, String regi, String subreg,String flag, String borders,String languages, String population){
        this.name=name;
        this.capti=capti;
        this.regi=regi;
        this.subreg=subreg;
        this.flag=flag;
        this.borders=borders;
        this.languages=languages;
        this.population=population;
        this.srnum=srnum;
    }

    public String getcapti() {
        return capti;
    }
    public String getName() {
        return name;
    }
    public String getregion() {
        return regi;
    }
    public String getsubregion() {
        return subreg;
    }
    public String getflag() { return flag; }
    public String getborders() { return borders; }
    public String getLanguages() { return languages; }
    public String getpopulation() { return population; }

    public String getSrnum() { return srnum; }

}
