package com.anaheimelectronics.launch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.anaheimelectronics.R;
import com.anaheimelectronics.common.AERootActivity;

public class AEMainActivity extends AERootActivity {


    @Override
    protected void initData(Context context, AttributeSet attrs) {

    }

    @Override
    protected View initLayout(LayoutInflater inflater) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.ae_main_layout);
        return null;
    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void initView() {

    }
}
