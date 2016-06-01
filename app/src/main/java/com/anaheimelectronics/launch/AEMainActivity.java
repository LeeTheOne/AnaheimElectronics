package com.anaheimelectronics.launch;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.anaheimelectronics.R;
import com.anaheimelectronics.common.AERootActivity;
import com.anaheimelectronics.developing.NormalRecyclerViewAdapter;

public class AEMainActivity extends AERootActivity {

    static final String TAG = "AEMainActivity";
    private RecyclerView mRecyclerView;

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
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(this));
    }
}
