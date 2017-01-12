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

/**
 * Created by wangguanghong on 2017/1/12.
 */
public class StaggerAdapter2 extends RecyclerView.Adapter<StaggerAdapter2.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mListDatas;
    private List<Integer> mHeights;
    private OnClickListener mListener = null;

    public void setListener(OnClickListener listener) {
        this.mListener = listener;
    }


    public interface OnClickListener {
        void onItemClickListener(View view, int position);
        void onItemLongClickListener(View view, int position);
    }

    public StaggerAdapter2() {
    }

    public StaggerAdapter2(Context context, List<String> data) {
        this.mContext = context;
        this.mListDatas = data;
        mLayoutInflater = LayoutInflater.from(mContext);
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mListDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public StaggerAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final StaggerAdapter2.ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        holder.mView.setText(mListDatas.get(position));
        if(null!=mListener){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClickListener(view,holder.getLayoutPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mListener.onItemLongClickListener(view,holder.getLayoutPosition());
                    return true;
                }
            });
        }

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

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = (TextView) itemView.findViewById(R.id.list_item);
        }
    }

}
