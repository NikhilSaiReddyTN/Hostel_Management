package com.example.hostel_manaement;

public class Student {
    String name;
    String id;
    String phone;
    String hostel;
    String room;
    String parent;
    String pass;
    public Student(String name, String id, String phone, String hostel, String room, String parent, String pass) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.hostel = hostel;
        this.room = room;
        this.parent = parent;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
