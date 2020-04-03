package com.projects.bargavsai.myapplication;

public class Tasks {

    //int id;
    String title;
    String descr;
    String dd;

    public Tasks(){

    }

    public Tasks(String title,String descr,String dd){
        //this.id = id;
        this.title = title;
        this.descr = descr;
        this.dd = dd;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public  String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }
}
