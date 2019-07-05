package com.example.animationset;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private final int[] mIntArray;
    private final String[] mStringArray;
    Context mContext;

    public MyRecyclerViewAdapter(int[] intArray, String[] stringArray, Context context) {
        mIntArray = intArray;
        mStringArray = stringArray;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemNameTextView.setText(mStringArray[position]);

        final Uri uri = Uri.parse("res://" + mContext.getPackageName() + "/" + mIntArray[position]);
        DraweeController build = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.itemImg.setController(build);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, uri.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStringArray != null ? mStringArray.length : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView itemNameTextView;
        SimpleDraweeView itemImg;

        public ViewHolder(@NonNull View view) {
            super(view);

            itemView = view.findViewById(R.id.view_content);
            itemNameTextView = view.findViewById(R.id.text_view_item);
            itemImg = view.findViewById(R.id.image_item);
        }
    }
}
