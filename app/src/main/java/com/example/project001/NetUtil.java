package com.example.project001;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class NetUtil {
    private static String url = "http://172.40.101.216/project001/";


    public static String register(String user, String pass){
        String res = "未知错误";
        if(user.isEmpty() || pass.isEmpty()){
            res = "存在数据空项"; // 1 存在数据空项
        }else if(user=="admin"){
            res="用户名不能为admin";
        }
        else {
            try{
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("user", user)
                        .add("pass", pass)
                        .build();

                Request request = new Request.Builder()
                        .url(url + "register.php")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();

                String data = response.body().string();
                if(data.equals("0")){
                    res = "用户名已存在"; //3用户名重复
                } else {
                    res = "注册成功"; //注册成功

                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return res;
    }

    public static String login(String user, String pass){
        String res = "未知错误";
        if(user.isEmpty() || pass.isEmpty()){
            res = "存在数据空项"; // 1 存在数据空项
        }else {
            try{
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("user", user)
                        .add("pass", pass)
                        .build();

                Request request = new Request.Builder()
                        .url(url + "login.php")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();

                String data = response.body().string();
                if(data.equals("0")){
                    res = "账号或密码错误";
                } else {
                    res = "登录成功";

                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return res;
    }




    public static String loadHousingDataName(){
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url + "fcgetname.php")
                    .build();
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            Gson gson = new Gson();
            List<String> list = gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
            Data.setHsName(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String loadTravelDataName(){
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url + "jdgetname.php")
                    .build();
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            Gson gson = new Gson();
            List<String> list = gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
            Data.setTvName(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String addHouseData(String name, String descp, String price){
        String res = "未知错误";
        if(name.isEmpty() || descp.isEmpty() || price.isEmpty()){
            res = "存在数据空项"; // 1 存在数据空项
        }else {
            try{
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("name", name)
                        .add("descp", descp)
                        .add("price", price)
                        .build();

                Request request = new Request.Builder()
                        .url(url + "addhousedata.php")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();

                String data = response.body().string();
                if(data.equals("0")){
                    res = "内容已存在"; //3用户名重复
                } else {
                    res = "数据添加成功"; //注册成功

                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return res;
    }
    public static String addTravelData(String name, String descp, String guide, String ticket){
        String res = "未知错误";
        if(name.isEmpty() || descp.isEmpty() || guide.isEmpty() || ticket.isEmpty()){
            res = "存在数据空项"; // 1 存在数据空项
        }else {
            try{
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("name", name)
                        .add("descp", descp)
                        .add("guide", guide)
                        .add("ticket", ticket)
                        .build();

                Request request = new Request.Builder()
                        .url(url + "addtraveldata.php")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();

                String data = response.body().string();
                if(data.equals("0")){
                    res = "内容已存在"; //3用户名重复
                } else {
                    res = "数据添加成功"; //注册成功

                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return res;
    }


}
