package com.anaheimelectronics.developing;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anaheimelectronics.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zacharyMac on 16/6/1.
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder>{

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener mItemClickListener;

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;
    private ArrayList<String> mDatas;

    public NormalRecyclerViewAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mDatas = new ArrayList<>(Arrays.asList(mTitles));
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mItemClickListener = listener;
    }

    public void addData(int position){
        mDatas.add(position,"Insert one");
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void resetData(){
        mDatas = new ArrayList<>(Arrays.asList(mTitles));
        notifyDataSetChanged();
    }

    @Override
    public NormalRecyclerViewAdapter.NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.developing_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(final NormalRecyclerViewAdapter.NormalTextViewHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position));
        holder.mTextView.setHeight(100);
//        holder.mTextView.setHeight(100 + (position % 4) * 30);
        if(mItemClickListener != null){
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mItemClickListener.onItemClick(holder.mCardView,pos);
                }
            });

            holder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mItemClickListener.onItemLongClick(holder.mCardView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        CardView mCardView;
        public NormalTextViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.main_text);
            mCardView = (CardView)itemView.findViewById(R.id.card_view);
        }
    }
}
