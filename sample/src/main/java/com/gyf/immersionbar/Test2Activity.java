package com.gyf.immersionbar;

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
public class Test2Activity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarManager.setBarColor(this, true);
        setContentView(R.layout.activity_test2);
    }

}
