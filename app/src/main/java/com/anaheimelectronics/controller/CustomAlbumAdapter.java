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


/**
 * Created by zacharyli on 2016/12/5.
 */

public class CustomAlbumAdapter extends RecyclerView.Adapter<CustomAlbumAdapter.CustomAlbumViewHolder>{

    public interface IOnAlbumItemClickListener{
        void onAlbumItemClick(View view, int position, AlbumItem item);
        void onAlbumItemLongClick(View view, int position, AlbumItem item);
    }
    private IOnAlbumItemClickListener onAlbumItemClickListener;

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

    public void setOnAlbumItemClickListener(IOnAlbumItemClickListener listener){
        onAlbumItemClickListener = listener;
    }

    @Override
    public CustomAlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomAlbumViewHolder(mLayoutInflater.inflate(R.layout.custom_album_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(final CustomAlbumViewHolder holder, final int position) {


        //bind listener
        if(onAlbumItemClickListener != null){
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onAlbumItemClickListener.onAlbumItemClick(v,pos,mDatas.get(pos));
                }
            });
            holder.mImageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onAlbumItemClickListener.onAlbumItemLongClick(v,pos,mDatas.get(pos));
                    return false;
                }
            });
        }
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
