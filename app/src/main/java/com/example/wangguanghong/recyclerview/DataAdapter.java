package com.example.wangguanghong.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wangguanghong on 2017/1/12.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mListDatas;

    public DataAdapter() {
    }

    public DataAdapter(Context context,List<String> data) {
        this.mContext=context;
        this.mListDatas=data;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.mView.setText(mListDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mListDatas.size();
    }

    public void addItem() {//0的话看不到变化,除非加入画面移动的代码
        mListDatas.add(1,"add_item");
        notifyItemInserted(1);
    }

    public void deleteItem() {
        mListDatas.remove(1);
        notifyItemRemoved(1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mView;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=(TextView) itemView.findViewById(R.id.list_item);
        }
    }

}
