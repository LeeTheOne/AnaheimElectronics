package com.anaheimelectronics.controller;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.anaheimelectronics.R;
import com.anaheimelectronics.common.AERootActivity;
import com.anaheimelectronics.developing.NormalRecyclerViewAdapter;

import butterknife.Bind;

/**
 * Created by zacharyli on 2016/12/3.
 */

public class CustomAlbumActivity extends AERootActivity {

    @Bind(R.id.album_grid_view) RecyclerView mAlbumGridView;

    private NormalRecyclerViewAdapter mAdapter;

    @Override
    protected void initData(Context context, AttributeSet attrs) {
        mAdapter = new NormalRecyclerViewAdapter(CustomAlbumActivity.this);
    }

    @Override
    protected View initLayout(LayoutInflater inflater) {
        setContentView(R.layout.custom_album_layout);
        return null;
    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void initView() {
        mAlbumGridView.setLayoutManager(new GridLayoutManager(this, 3));
        mAlbumGridView.setAdapter(mAdapter);
    }
}
