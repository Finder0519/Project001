package com.example.project001;

import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText username,password,password_conf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_register=(Button)findViewById(R.id.register);
        username=(EditText) findViewById(R.id.username_new);
        password=(EditText) findViewById(R.id.password_new);
        password_conf=(EditText)findViewById(R.id.password_confirm);

        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(!password.getText().toString().equals(password_conf.getText().toString())){
                            Looper.prepare();
                            Toast.makeText(RegisterActivity.this,"两次输入的密码不匹配",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            return ;
                        }
                        String res = NetUtil.register(username.getText().toString(),password.getText().toString());
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this, res, Toast.LENGTH_SHORT).show();
                        if(res.equals("注册成功")){
                            finish();
                        }
                        Looper.loop();
                    }
                }).start();
            }

        });
    }
}
