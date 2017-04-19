package com.gyf.immersionbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gyf.barlibrary.BarManager;

/**
 * Created by gyf on 2016/10/24.
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarManager.setBarColor(this, BarManager.BarType.NAVIGATION_BAR,
                ContextCompat.getColor(this, R.color.colorPrimary), true);
        setContentView(R.layout.activity_test);
        Button btn_status_color = (Button) findViewById(R.id.btn_status_color);
        Button btn_navigation_color = (Button) findViewById(R.id.btn_navigation_color);
        btn_status_color.setOnClickListener(this);
        btn_navigation_color.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_status_color:
                BarManager.setBarColor(this, ContextCompat.getColor(this, R.color.colorAccent),
                        ContextCompat.getColor(this, R.color.colorPrimary), true);
                break;
            case R.id.btn_navigation_color:
                BarManager.setBarColor(this, BarManager.BarType.NAVIGATION_BAR,
                        ContextCompat.getColor(this, R.color.colorAccent), true);
                break;
        }
    }
}
