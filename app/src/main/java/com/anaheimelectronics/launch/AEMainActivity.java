package com.anaheimelectronics.launch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anaheimelectronics.R;
import com.anaheimelectronics.common.AERootActivity;
import com.anaheimelectronics.developing.NormalItemDecoration;
import com.anaheimelectronics.developing.NormalRecyclerViewAdapter;
import com.anaheimelectronics.recyclerview.AERecycleViewActivity;

import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;

public class AEMainActivity extends AERootActivity implements View.OnClickListener{

    static final String TAG = "AEMainActivity";

    private CardView mEntryCard1;
    private TextView mEntryTv1;

    @Override
    protected void initData(Context context, AttributeSet attrs) {
    }

    @Override
    protected View initLayout(LayoutInflater inflater) {
//        if(getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
        setContentView(R.layout.ae_main_layout);
        return null;
    }

    @Override
    protected void bindView() {
        mEntryCard1 = (CardView)findViewById(R.id.entry_card_1);
        mEntryTv1 = (TextView)findViewById(R.id.entry_tv_1);
    }

    @Override
    protected void initView() {
        mEntryTv1.setText("RecyclerView");
        mEntryCard1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entry_card_1:
                Intent intent = new Intent(AEMainActivity.this, AERecycleViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
