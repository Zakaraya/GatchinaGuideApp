package com.robotemplates.testguide;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class BlogViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public BlogViewHolder(@NonNull final View itemView) {
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

//    public void setTitle(String title){
//        TextView post_title=mView.findViewById(R.id.post_title);
//        post_title.setText(title);
//    }
//
//    public void setDescription(String description){
//        TextView post_desc=mView.findViewById(R.id.post_desc);
//        post_desc.setText(description);
//    }
//
//    public void setImage( String image){
//        ImageView post_image=mView.findViewById(R.id.post_imeg);
//        Picasso.get().load(image).into(post_image);
//    }


    public void setDetails(Context context, String title, String description, String image){
        TextView post_title=mView.findViewById(R.id.post_title);
        TextView post_desc=mView.findViewById(R.id.post_desc);
        ImageView post_image=mView.findViewById(R.id.post_imeg);

        post_title.setText(title);
        post_desc.setText(description);
        Picasso.get().load(image).into(post_image);
    }

    private BlogViewHolder.ClickListener mClickListner;

    public interface ClickListener{
        void onItemClick(View v, int position);
        void onItemLongClick(View v, int position);
    }

    public void setOnClickListener(BlogViewHolder.ClickListener clickListener){
        mClickListner=clickListener;
    }
}
