package com.anaheimelectronics.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by zacharyMac on 16/5/31.
 */
public abstract class AERootActivity extends AppCompatActivity{

    static final String TAG = "AERootActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout(LayoutInflater.from(this));
        bindView();
        initData(this, null);
        initView();
    }

    protected abstract void initData(Context context, AttributeSet attrs);


    protected abstract View initLayout(LayoutInflater inflater);


    protected abstract void bindView();

    protected abstract void initView();


}
