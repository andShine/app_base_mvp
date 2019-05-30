package com.tourong.app.common;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.vlayout.DelegateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用适配器
 * Created by zhy on 16/4/9.
 */
public abstract class CommonDelegateAdapter<T> extends DelegateAdapter.Adapter<ViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected LayoutInflater mInflater;
    protected List<T> mDatas;
    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    public CommonDelegateAdapter(final Context context, @LayoutRes final int layoutId, @Nullable List<T> datas) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        if (datas != null) {
            mDatas = datas;
        } else {
            mDatas = new ArrayList<>();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(mLayoutId, parent, false);
        ViewHolder holder = ViewHolder.createViewHolder(mContext, rootView);
        onViewHolderCreated(holder, holder.getConvertView());
        return holder;
    }

    public void onViewHolderCreated(ViewHolder holder, View itemView) {
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    protected void setListener(final ViewHolder viewHolder, int viewType, final int position) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });
        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    return mOnItemLongClickListener.onItemLongClick(v, viewHolder, position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setListener(holder, getItemViewType(position), position);
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        int itemCount = mDatas.size();
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public void addDatas(List<T> datas) {
        int size = this.mDatas.size();
        this.mDatas.addAll(datas);
        notifyItemRangeChanged(size - 1, datas.size());
    }

    public void setData(T data, int position) {
        if (this.mDatas.size() < position + 1) {
            return;
        }
        notifyItemChanged(position);
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    protected abstract void convert(ViewHolder holder, T t, int position);
}