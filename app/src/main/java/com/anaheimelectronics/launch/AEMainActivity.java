package com.anaheimelectronics.launch;

import android.content.Context;
import android.graphics.Canvas;
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
import android.widget.Toast;

import com.anaheimelectronics.R;
import com.anaheimelectronics.common.AERootActivity;
import com.anaheimelectronics.developing.NormalItemDecoration;
import com.anaheimelectronics.developing.NormalRecyclerViewAdapter;

import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;

public class AEMainActivity extends AERootActivity {

    static final String TAG = "AEMainActivity";
    private RecyclerView mRecyclerView;
    private NormalRecyclerViewAdapter mAdapter;

    @Override
    protected void initData(Context context, AttributeSet attrs) {
        mAdapter = new NormalRecyclerViewAdapter(this);
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
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new NormalItemDecoration(AEMainActivity.this, OrientationHelper.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(new NormalRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(AEMainActivity.this,"click"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(AEMainActivity.this,"long click"+position,Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ae_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                mAdapter.addData(1);
                return true;
            case R.id.action_delete:
                mAdapter.removeData(1);
                return true;
            case R.id.action_reset:
                mAdapter.resetData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
