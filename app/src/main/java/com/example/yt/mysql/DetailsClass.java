package com.example.yt.mysql;

/**
 * Created by yt on 16-Dec-17.
 */

public class DetailsClass {
    String id;
    String name;
    String surname;
    String marks;

    public DetailsClass(String iid, String iname,String isurname, String imarks){
        id=iid;
        name=iname;
        surname=isurname;
        marks=imarks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMarks() {
        return marks;
    }
}
