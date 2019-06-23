package com.example.project001;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TravelAddActivity extends AppCompatActivity {

    private EditText name,descp,guide,ticket;
    private Button select_pic,savedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_add);
        name=findViewById(R.id.travel_name);
        descp=findViewById(R.id.travel_description);
        guide=findViewById(R.id.travel_guide);
        ticket=findViewById(R.id.travel_ticket);
        select_pic=findViewById(R.id.travel_pic_picker);
        select_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,null),1);
            }
        });
        savedata=findViewById(R.id.travel_data_save);
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String res = NetUtil.addTravelData(name.getText().toString(),descp.getText().toString(),guide.getText().toString(),ticket.getText().toString());
                        Looper.prepare();
                        Toast.makeText(TravelAddActivity.this, res, Toast.LENGTH_SHORT).show();
                        if(res.equals("数据添加成功")){
                            finish();
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });
    }
}
