package com.example.project001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
public class TravelDetailActivity extends AppCompatActivity {
    static String jdname, jddescp,jdguide,jdticket;
    private Handler handler=new Handler();
    private Button jdcomment;
    private CheckBox jdaddfav;
    private static String url = "http://172.40.101.216/project001/";
    TextView jdnameshow, jddescpshow, jdguideshow,jdticketshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);

        final Bundle bundle = this.getIntent().getExtras();
        final String username = bundle.getString("username");
        jdname = bundle.getString("jdname");
        Log.d("detail", jdname);
        jdnameshow = findViewById(R.id.travel_name_content);
        jddescpshow = findViewById(R.id.travel_descp_content);
        jdguideshow = findViewById(R.id.travel_guide_content);
        jdticketshow = findViewById(R.id.travel_ticket_content);

        jdnameshow.setText(jdname);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("jdname",jdname)
                            .build();
                    Request request = new Request.Builder()
                            .url(url + "jdget.php?jdname="+jdname)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    //Log.d("resp",response);
                    String responseData = response.body().string();
                    Log.d("resdata",responseData);
                    //HousingData housingData=new Gson().fromJson(responseData,HousingData.class);
                    TravelData travelData=new Gson().fromJson(responseData,TravelData.class);
                    jddescp=travelData.jddescp;
                    jdguide=travelData.jdguide;
                    jdticket=travelData.jdticket;
                    handler.post(runnable);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        jdcomment=findViewById(R.id.travel_comment_add);
        jdcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(TravelDetailActivity.this);
                AlertDialog dialog=builder
                        .setView(R.layout.comment_add)
                        .create();
                dialog.show();
                dialog.getWindow().findViewById(R.id.comment_push).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TravelDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            }
        });
        jdaddfav=findViewById(R.id.travel_fav_add);
        jdaddfav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(TravelDetailActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TravelDetailActivity.this,"取消收藏成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            jddescpshow.setText(jddescp);
            jdguideshow.setText(jdguide);
            jdticketshow.setText(jdticket);
        }
    };
}
