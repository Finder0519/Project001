package com.example.project001;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Bundle bundle=this.getIntent().getExtras();
        final String username=bundle.getString("username");
        Log.d("test",username);


        Button newsHome=findViewById(R.id.news_home);
        newsHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(MainActivity.this,NewsHomeActivity.class);
                String news_app="androidnews.kiloproject";
                PackageManager packageManager= getApplicationContext().getPackageManager();
                Intent intent=packageManager.getLaunchIntentForPackage(news_app);
                if(intent!=null){
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"请检查是否安装该app",Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button weatherHome=findViewById(R.id.weather_home);
        weatherHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weather_app="com.example.stardream.coolweather";
                PackageManager packageManager= getApplicationContext().getPackageManager();
                Intent intent=packageManager.getLaunchIntentForPackage(weather_app);
                if(intent!=null){
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"请检查是否安装该app",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button housingHome=findViewById(R.id.housing_home);
        housingHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HousingListActivity.class);
                Bundle bundle1=new Bundle();
                bundle1.putString("username",username);
                intent.putExtras(bundle1);
                startActivity(intent);

            }
        });
        Button travelHome=findViewById(R.id.travel_home);
        travelHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TravelListActivity.class);
                Bundle bundle2=new Bundle();
                bundle2.putString("username",username);
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });

    }
}
