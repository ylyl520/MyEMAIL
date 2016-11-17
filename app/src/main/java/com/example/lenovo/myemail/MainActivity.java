package com.example.lenovo.myemail;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener{

    Button send,get,first,second,add;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        send = (Button) findViewById(R.id.button1);
        send.setOnClickListener(this);
        get = (Button) findViewById(R.id.button2);
        get.setOnClickListener(this);
        first = (Button) findViewById(R.id.first);
        first.setOnClickListener(this);
        second = (Button) findViewById(R.id.second);
        second.setOnClickListener(this);
        add = (Button) findViewById(R.id.button3);
        add.setOnClickListener(this);
        drawer = (DrawerLayout) findViewById(R.id.menu_drawer);
        //Window window =drawer.ge
        //ListView menulist = (ListView) findViewById(R.id.menu_list);
        List<Map<String, Object>> menuItem = new ArrayList<Map<String, Object>>();
        //0
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menu_icon",R.drawable.smile);
        map.put("menu_title","写信");
        menuItem.add(map);
        //1
        map = new HashMap<String, Object>();
        map.put("menu_icon",R.drawable.sorrow);
        map.put("menu_title","收信");
        menuItem.add(map);
        //2
        map = new HashMap<String, Object>();
        map.put("menu_icon",R.drawable.logo);
        map.put("menu_title","返回主界面");
        menuItem.add(map);
        SimpleAdapter menuAdapter = new SimpleAdapter(this,menuItem,R.layout.menu_item,
                new String[]{"menu_icon","menu_title"},
                new int[]{R.id.menu_icon,R.id.menu_title});

        ListView menulist = (ListView) findViewById(R.id.menu_alist);
        if (menulist == null) {
            Log.i("s","wpccca");
        }
        menulist.setAdapter(menuAdapter);
        menulist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, setemail.class);
                    startActivity(intent);
                }
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, Sendemail.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onClick(View view)
    {
        if (view== send)
        {
            Intent intent = new Intent(this, Sendemail.class);
            startActivity(intent);

        }
        else if(view== get)
        {
            Intent intent = new Intent(this, setemail.class);
            startActivity(intent);
        }
        else if(view== first)
        {
            System.exit(0);
        }
        else if(view== second)
        {
            System.exit(0);
        }
        else if(view== add)
        {
            onBackPressed();
        }
    }
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.menu_drawer);
        if (drawer.isDrawerOpen(Gravity.LEFT))
        {
            drawer.closeDrawer(Gravity.LEFT);
        } else
        {
            drawer.openDrawer(Gravity.LEFT);
        }
    }

}

