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
import com.anaheimelectronics.model.AlbumItem;
import com.anaheimelectronics.model.CustomAlbumDataHelper;

import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by zacharyli on 2016/12/3.
 */

public class CustomAlbumActivity extends AERootActivity {

    @Bind(R.id.album_grid_view) RecyclerView mAlbumGridView;

    private CustomAlbumAdapter mAdapter;

    @Override
    protected void initData(Context context, AttributeSet attrs) {
        mAdapter = new CustomAlbumAdapter(CustomAlbumActivity.this);
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
        CustomAlbumDataHelper.getInstance().getData().subscribe(new Action1<List<AlbumItem>>() {
            @Override
            public void call(List<AlbumItem> albumItems) {
                mAdapter.updateData(albumItems);
            }
        });
    }
}
