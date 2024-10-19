package com.example.hostel_manaement;

public class Comp {
    String id;
    String hostel;
    String loc;
    String desc;
    String date;
    String urgency;

    public Comp(String id, String hostel, String loc, String desc, String date, String urgency) {
        this.id = id;
        this.hostel = hostel;
        this.loc = loc;
        this.desc = desc;
        this.date = date;
        this.urgency = urgency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
}
