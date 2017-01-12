package com.example.wangguanghong.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangguanghong on 2017/1/12.
 */
public class StaggerAdapter extends RecyclerView.Adapter<StaggerAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mListDatas;
    private List<Integer> mHeights;
    private OnItemClickListener mListener = null;
    private OnItemLongClickListener mLongListener = null;

    public void setListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setLongListener(OnItemLongClickListener listener) {
        this.mLongListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(View view, int position);
    }

    public StaggerAdapter() {
    }

    public StaggerAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mListDatas = data;
        mLayoutInflater = LayoutInflater.from(mContext);
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mListDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public StaggerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setOnLongClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StaggerAdapter.ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        holder.mView.setText(mListDatas.get(position));
        int pos = holder.getLayoutPosition();
        holder.itemView.setTag(pos);
    }

    @Override
    public int getItemCount() {
        return mListDatas.size();
    }

    public void addItem() {
        mListDatas.add(1, "add_item");
        mHeights.add(1, (int) (100 + Math.random() * 300));
        notifyItemInserted(1);
    }

    public void deleteItem() {
        mListDatas.remove(1);
        mHeights.remove(1);
        notifyItemRemoved(1);
    }

    @Override
    public void onClick(View view) {
        if (null != mListener) {
            mListener.onItemClickListener(view, (Integer) view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (null != mLongListener) {
            mLongListener.onItemLongClickListener(view, (Integer) view.getTag());
        }
        return true;//true拦截了行为，长按不会触发短点击
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = (TextView) itemView.findViewById(R.id.list_item);
        }
    }

}
