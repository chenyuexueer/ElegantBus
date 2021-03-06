/*
 * ************************************************************
 * 文件：ElegantBusDemoActivity.java  模块：app  项目：ElegantBus
 * 当前修改时间：2020年06月15日 00:35:24
 * 上次修改时间：2020年06月15日 00:30:33
 * 作者：Cody.yi   https://github.com/codyer
 *
 * 描述：app
 * Copyright (c) 2020
 * ************************************************************
 */

package com.cody.bus.example;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cody.bus.example.cody.MyAppEventBus;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cody.bus.ObserverWrapper;


public class ElegantBusDemoActivity extends AppCompatActivity {
    private static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_event_bus_demo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            count++;
            Snackbar.make(view, "发送事件监听" + count, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            MyAppEventBus.testBean().post(new TestBean("name" + count, "code" + count));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(ElegantBusDemoActivity.this, "注册事件监听", Toast.LENGTH_SHORT).show();

            MyAppEventBus.testBean().observe(ElegantBusDemoActivity.this, new ObserverWrapper<TestBean>() {
                @Override
                public void onChanged(TestBean testBean) {
                    Toast.makeText(ElegantBusDemoActivity.this, "事件监听" + testBean.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
