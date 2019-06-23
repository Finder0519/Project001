package com.example.project001;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HousingDetailActivity extends AppCompatActivity {
    static String fcname, fcdescp,fcprice;
    private Handler handler=new Handler();
    private Button fccomment;
    private CheckBox fcaddfav;
    private static String url = "http://172.40.101.216/project001/";
    TextView fcnameshow, fcdescpshow, fcpriceshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_detail);
        final Bundle bundle = this.getIntent().getExtras();
        final String username = bundle.getString("username");
        fcname = bundle.getString("fcname");
        Log.d("detail", fcname);
        fcnameshow = findViewById(R.id.housing_name_content);
        fcdescpshow = findViewById(R.id.housing_descp_content);
        fcpriceshow = findViewById(R.id.housing_price_content);
        fcnameshow.setText(fcname);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("fcname",fcname)
                            .build();
                    Request request = new Request.Builder()
                            .url(url + "fcget.php?fcname="+fcname)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    //Log.d("resp",response);
                    String responseData = response.body().string();
                    Log.d("resdata",responseData);
                    HousingData housingData=new Gson().fromJson(responseData,HousingData.class);
                    Log.d("out",housingData.fcname);
                    Log.d("out",housingData.fcdescp);
                    Log.d("out",housingData.fcprice);
                    fcdescp=housingData.fcdescp;
                    fcprice=housingData.fcprice;
                    handler.post(runnable);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        fccomment=findViewById(R.id.housing_comment_add);
        fccomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(HousingDetailActivity.this);
                AlertDialog dialog=builder
                        .setView(R.layout.comment_add)
                        .create();
                dialog.show();
                dialog.getWindow().findViewById(R.id.comment_push).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HousingDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            }
        });
        fcaddfav=findViewById(R.id.housing_fav_add);
        fcaddfav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(HousingDetailActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(HousingDetailActivity.this,"取消收藏成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            fcdescpshow.setText(fcdescp);
            fcpriceshow.setText(fcprice);
        }
    };
}
