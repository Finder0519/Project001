package com.example.project001;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HousingListActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ArrayAdapter adapter;
    private List<String> housingDataList=new ArrayList<>();
    private Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_list);
        final Bundle bundle=this.getIntent().getExtras();
        final String username=bundle.getString("username");
        Log.d("test1",username);
        drawerLayout=findViewById(R.id.housing_drawer_layout);
        NavigationView navigationView=findViewById(R.id.housing_nav);
        navigationView.setCheckedItem(R.id.housing_fav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                Intent intent=new Intent(HousingListActivity.this,HousingFavActivity.class);
                startActivity(intent);
                return true;
            }
        });
        FloatingActionButton fab=findViewById(R.id.add_housing);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.equals("admin")){
                    //Looper.prepare();
                    Toast.makeText(HousingListActivity.this,"没有权限。",Toast.LENGTH_SHORT).show();
                    //Looper.loop();
                    return ;
                }else{
                    Intent intent=new Intent(HousingListActivity.this,HousingAddActivity.class);
                    startActivity(intent);
                }

            }
        });

        listView=findViewById(R.id.list_housing);

        adapter = new ArrayAdapter<>(HousingListActivity.this, android.R.layout.simple_list_item_1, housingDataList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle1 =new Bundle();
                Intent intent = new Intent(HousingListActivity.this, HousingDetailActivity.class);
                bundle1.putString("username",username);
                bundle1.putString("fcname",housingDataList.get(i));
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });


        handler = new Handler();

        flashHousingData();

    }

    private void flashHousingData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetUtil.loadHousingDataName();
                housingDataList.clear();
                housingDataList.addAll(Data.getHsName());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

}
