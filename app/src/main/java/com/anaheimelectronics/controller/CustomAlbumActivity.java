package com.anaheimelectronics.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.anaheimelectronics.R;
import com.anaheimelectronics.common.AERootActivity;
import com.anaheimelectronics.model.AlbumItem;
import com.anaheimelectronics.model.CustomAlbumDataHelper;
import com.anaheimelectronics.model.ImageData;

import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by zacharyli on 2016/12/3.
 */

public class CustomAlbumActivity extends AERootActivity implements CustomAlbumAdapter.IOnAlbumItemClickListener{

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
        CustomAlbumDataHelper.getInstance().getData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<AlbumItem>>() {
            @Override
            public void call(List<AlbumItem> albumItems) {
                mAdapter.updateData(albumItems);
            }
        });

        mAdapter.setOnAlbumItemClickListener(this);
    }

    @Override
    public void onAlbumItemClick(View view, int position, AlbumItem item) {
        ImageData imageData = new ImageData();
        imageData.local_path = item.url;
        Intent intent = ImageViewerActivity.obtainIntent(this,imageData);
        startActivity(intent);
    }

    @Override
    public void onAlbumItemLongClick(View view, int position, AlbumItem item) {

    }
}
