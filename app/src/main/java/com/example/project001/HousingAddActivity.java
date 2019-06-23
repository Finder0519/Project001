package com.example.project001;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HousingAddActivity extends AppCompatActivity {
    private EditText name,descp,price;
    private Button select_pic,savedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_add);
        name=findViewById(R.id.housing_name);
        descp=findViewById(R.id.housing_description);
        price=findViewById(R.id.housing_price);
        select_pic=findViewById(R.id.housing_pic_picker);
        select_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,null),1);
            }
        });
        savedata=findViewById(R.id.housing_data_save);
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String res = NetUtil.addHouseData(name.getText().toString(),descp.getText().toString(),price.getText().toString());
                        Looper.prepare();
                        Toast.makeText(HousingAddActivity.this, res, Toast.LENGTH_SHORT).show();
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
