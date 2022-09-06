package com.example.javaproj;


public class dataa {

    String bname;
    String from;
    String destination;
    String fare;
    String time;
    String coloe;

    public dataa(String bname,String destination,String from, String fare, String time,String coloe) {
        this.bname = bname;
        this.destination = destination;
        this.fare = fare;
        this.from=from;
        this.time=time;
        this.coloe=coloe;
    }

    public String getName() {
        return bname;
    }

    public String getDestination() {
        return destination;
    }

    public String getfare() {
        return fare;
    }

    public String gettime() {
        return time;
    }

    public String getfrom() {
        return from;
    }
    public String getColoe() {
        return coloe;
    }
}