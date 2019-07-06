package com.example.viewanimation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter extends RecyclerView.Adapter {

    private final int[] mColorIntArray;
    Context mContext;
    int mItemCount;

    public MyRecyclerAdapter(Context context, int itenCount) {
        mContext = context;
        mItemCount = itenCount;
        mColorIntArray = context.getResources().getIntArray(R.array.colors);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(mColorIntArray[position % mColorIntArray.length]);
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
