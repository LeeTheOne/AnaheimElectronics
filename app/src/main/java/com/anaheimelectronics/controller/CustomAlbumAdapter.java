package com.anaheimelectronics.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anaheimelectronics.R;
import com.anaheimelectronics.model.AlbumItem;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zacharyli on 2016/12/5.
 */

public class CustomAlbumAdapter extends RecyclerView.Adapter<CustomAlbumAdapter.CustomAlbumViewHolder>{

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private ArrayList<AlbumItem> mDatas;

    public CustomAlbumAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = new ArrayList<AlbumItem>();
    }

    public void updateData(List<AlbumItem> items){
        if(items == null){
            return;
        }
//        int testSize = items.size() > 100 ? 100:items.size();
        mDatas.clear();
        mDatas.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public CustomAlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomAlbumViewHolder(mLayoutInflater.inflate(R.layout.custom_album_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(final CustomAlbumViewHolder holder, int position) {

        Observable.just(position)
        .observeOn(Schedulers.io())
        .map(new Func1<Integer, Bitmap>() {
            @Override
            public Bitmap call(Integer position) {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                return BitmapFactory.decodeFile(mDatas.get(position).url,options);
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                holder.mImageView.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mDatas != null){
            return mDatas.size();
        }
        return 0;
    }

    public static class CustomAlbumViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        ImageView mImageView;
        CustomAlbumViewHolder(View itemView) {
            super(itemView);
            mCardView   = (CardView)itemView.findViewById(R.id.card_view);
            mImageView  = (ImageView)itemView.findViewById(R.id.image_view);
        }
    }
}
