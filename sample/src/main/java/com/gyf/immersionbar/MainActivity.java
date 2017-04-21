package com.gyf.immersionbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gyf.barlibrary.BarManager;
import com.gyf.barlibrary.OSUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_skip, btn_skip2, btn_left, btn_status_hide, btn_navigation_hide, btn_bar_hide,
            btn_bar_show, btn_bar_font_dark, btn_bar_font_light;
    private DrawerLayout drawer;
    private LinearLayout news;
//    private Button btn_skip2;
//    private Button btn_left;
//    private Button btn_status_hide;
//    private Button btn_navigation_hide;
//    private Button btn_bar_hide;
//    private Button btn_bar_show;
//    private Button btn_bar_font_dark;
//    private Button btn_bar_font_light;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarManager.setBarColor(this); //设置状态栏颜色
        //  BarManager.setBarColor(this,true);
        //  BarManager.setBarColor(this, BarManager.BarType.STATUS_BAR, ContextCompat.getColor(this, R.color.colorPrimary), false);
        //  BarManager.setBarColor(this, BarManager.BarType.NAVIGATION_BAR, ContextCompat.getColor(this,R.color.colorPrimary),false);
        //  BarManager.setBarColor(this, BarManager.BarType.ALL_BAR, ContextCompat.getColor(this,R.color.colorPrimary),false);
        //  BarManager.setBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary),
        //             ContextCompat.getColor(this, R.color.colorPrimary), true);
        setContentView(R.layout.activity_main);

        btn_skip = (Button) findViewById(R.id.btn_skip);
        btn_skip2 = (Button) findViewById(R.id.btn_skip2);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_status_hide = (Button) findViewById(R.id.btn_status_hide);
        btn_navigation_hide = (Button) findViewById(R.id.btn_navigation_hide);
        btn_bar_hide = (Button) findViewById(R.id.btn_bar_hide);
        btn_bar_show = (Button) findViewById(R.id.btn_bar_show);
        btn_bar_font_dark = (Button) findViewById(R.id.btn_bar_font_dark);
        btn_bar_font_light = (Button) findViewById(R.id.btn_bar_font_light);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        news = (LinearLayout) findViewById(R.id.news);

        btn_skip.setOnClickListener(this);
        btn_skip2.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_status_hide.setOnClickListener(this);
        btn_navigation_hide.setOnClickListener(this);
        btn_bar_hide.setOnClickListener(this);
        btn_bar_show.setOnClickListener(this);
        btn_bar_font_dark.setOnClickListener(this);
        btn_bar_font_light.setOnClickListener(this);
        news.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_skip:
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_skip2:
                Intent intent2 = new Intent(MainActivity.this, Test2Activity.class);
                MainActivity.this.startActivity(intent2);
                break;
            case R.id.btn_left:
                drawer.openDrawer(Gravity.START);
                break;
            case R.id.btn_status_hide:
                BarManager.hideBar(this, BarManager.BarHide.FLAG_HIDE_STATUS_BAR);
                break;
            case R.id.btn_navigation_hide:
                BarManager.hideBar(this, BarManager.BarHide.FLAG_HIDE_NAVIGATION_BAR);
                break;
            case R.id.btn_bar_hide:
                BarManager.hideBar(this, BarManager.BarHide.FLAG_HIDE_BAR);
                break;
            case R.id.btn_bar_show:
                BarManager.hideBar(this, BarManager.BarHide.FLAG_SHOW_BAR);
                break;
            case R.id.btn_bar_font_dark:
                BarManager.setStatusBarDarkFont(this, true);
                break;
            case R.id.btn_bar_font_light:
                BarManager.setStatusBarDarkFont(this, false);
                break;
            case R.id.news:
                Intent intent3 = new Intent(MainActivity.this, Test2Activity.class);
                MainActivity.this.startActivity(intent3);

                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        drawer.closeDrawer(Gravity.START);
    }
}
