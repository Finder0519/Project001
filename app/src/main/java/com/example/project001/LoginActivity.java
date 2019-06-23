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

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {
    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_signin=(Button)findViewById(R.id.sign_in);
        Button btn_signup=(Button)findViewById(R.id.sign_up);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);

        btn_signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"正在登录，请稍候",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String res = NetUtil.login(username.getText().toString(),password.getText().toString());
                        Looper.prepare();
                        Toast.makeText(LoginActivity.this, res, Toast.LENGTH_SHORT).show();
                        if(res.equals("登录成功")){
                            Toast.makeText(LoginActivity.this,"您的登录身份是 "+username.getText().toString(),Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("username", username.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                        Looper.loop();
                    }
                }).start();
            }

        });
        btn_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

}

