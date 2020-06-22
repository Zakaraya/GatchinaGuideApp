package com.robotemplates.testguide.ui.Sovet;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robotemplates.testguide.BlogViewHolder;
import com.robotemplates.testguide.R;
import com.squareup.picasso.Picasso;

public class SlideshowViewModel extends RecyclerView.ViewHolder {

    public SlideshowViewModel(@NonNull final View itemView) {
        super(itemView);
        mView=itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListner.onItemClick(v, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListner.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }

    View mView;

    public void setDetails(Context context, String title, String description, String image){
        TextView post_title=mView.findViewById(R.id.post_title);
        TextView post_desc=mView.findViewById(R.id.post_desc);
        ImageView post_image=mView.findViewById(R.id.post_imeg);

        post_title.setText(title);
        post_desc.setText(description);
        Picasso.get().load(image).into(post_image);
    }

    private BlogViewHolder.ClickListener mClickListner;
}