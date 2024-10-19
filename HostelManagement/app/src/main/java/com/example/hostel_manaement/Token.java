package com.example.hostel_manaement;

public class Token {
    String indate,outdate,reason,id,time;

    public Token(String indate, String outdate, String reason, String id, String time) {
        this.indate = indate;
        this.outdate = outdate;
        this.reason = reason;
        this.id = id;
        this.time = time;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getOutdate() {
        return outdate;
    }

    public void setOutdate(String outdate) {
        this.outdate = outdate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
