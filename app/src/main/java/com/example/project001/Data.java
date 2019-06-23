package com.example.project001;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<String> hsName=new ArrayList<>();

    public static void setHsName(List<String> hsName){
        Data.hsName=hsName;
    }

    public static List<String> getHsName(){
        return hsName;
    }

    public static List<String> tvName=new ArrayList<>();

    public static void setTvName(List<String> tvName) {
        Data.tvName = tvName;
    }

    public static List<String> getTvName() {
        return tvName;
    }
}
