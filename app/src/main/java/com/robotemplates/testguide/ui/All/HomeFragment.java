package com.robotemplates.testguide.ui.All;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView;

import androidx.annotation.NonNull;
//import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.robotemplates.testguide.Blog;
import com.robotemplates.testguide.DetailActivity;
import com.robotemplates.testguide.R;

public class HomeFragment extends Fragment {

    private RecyclerView myRecyclerView;
    private Query DBR;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_all, container, false);
        DBR=FirebaseDatabase.getInstance().getReference().child("All");
        DBR.keepSynced(true);
        myRecyclerView=root.findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }


    @Override
    public void onStart() {
        super.onStart();

//        FirebaseRecyclerOptions<Blog> options =
//                new FirebaseRecyclerOptions.Builder<Blog>()
//                        .setQuery(DBR, Blog.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<Blog, BlogViewHolder> blogBlogViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(options){
//            @NonNull
//            @Override
//            public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.blog_row, parent, false);
//
//                return new BlogViewHolder(view);
//            }
//            @Override
//            protected void onBindViewHolder(@NonNull BlogViewHolder blogViewHolder, int i, @NonNull Blog blog) {
//                blogViewHolder.setTitle(blog.getTitle());
//                blogViewHolder.setDescription(blog.getDescription());
//                blogViewHolder.setImage(blog.getImage());
//            }
//        };myRecyclerView.setAdapter(blogBlogViewHolderFirebaseRecyclerAdapter);
//        blogBlogViewHolderFirebaseRecyclerAdapter.startListening();


        FirebaseRecyclerAdapter<Blog, HomeViewModel> blogBlogViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, HomeViewModel>(Blog.class, R.layout.blog_row, HomeViewModel.class, DBR) {

                @Override
                protected void populateViewHolder(HomeViewModel blogViewHolder, Blog blog, int i) {
                    final String PostKey=getRef(i).getKey();
//                blogViewHolder.setTitle(blog.getTitle());
//                blogViewHolder.setDescription(blog.getDescription());
//                blogViewHolder.setImage(blog.getImage());

                    blogViewHolder.setDetails(getContext(), blog.getTitle(), blog.getDescription(),blog.getImage());

                    blogViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent clickPostIntent = new Intent(getActivity(), DetailActivity.class);

                            clickPostIntent.putExtra("PostKey", PostKey);
                            startActivity(clickPostIntent);
                            //  Toast.makeText(getContext(),PostKey , Toast.LENGTH_LONG).show();
                        }
                    });
                }

        };myRecyclerView.setAdapter(blogBlogViewHolderFirebaseRecyclerAdapter);
    }

    private void firebaseSearch(String searchText) {
        Query firebaseSearchQuery = DBR.orderByChild("title").startAt(searchText).endAt(searchText + "\uf8ff");

        //KeyEvent.KEYCODE_SEARCH
        FirebaseRecyclerAdapter<Blog, HomeViewModel> blogBlogViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, HomeViewModel>(Blog.class, R.layout.blog_row, HomeViewModel.class, firebaseSearchQuery) {
            @Override
            protected void populateViewHolder(HomeViewModel blogViewHolder, Blog blog, int i) {
                final String PostKey=getRef(i).getKey();
                blogViewHolder.setDetails(getContext(), blog.getTitle(), blog.getDescription(),blog.getImage());
                blogViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent clickPostIntent = new Intent(getActivity(), DetailActivity.class);

                        clickPostIntent.putExtra("PostKey", PostKey);
                        startActivity(clickPostIntent);
                        //  Toast.makeText(getContext(),PostKey , Toast.LENGTH_LONG).show();
                    }
                });
            }
        };myRecyclerView.setAdapter(blogBlogViewHolderFirebaseRecyclerAdapter);

    }
//        FirebaseRecyclerOptions<Blog> options =
//                new FirebaseRecyclerOptions.Builder<Blog>()
//                        .setQuery(DBR, Blog.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<Blog, BlogViewHolder> blogBlogViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull BlogViewHolder blogViewHolder, int i, @NonNull Blog blog) {
//                blogViewHolder.setTitle(blog.getTitle());
//                blogViewHolder.setDescription(blog.getDescription());
//                blogViewHolder.setImage(blog.getImage());
//            }
//
//            @NonNull
//            @Override
//            public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//              // BlogViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
//
//                final View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.blog_row, parent, false);
//
//                BlogViewHolder viewHolder=super.onCreateViewHolder(parent, viewType);
//
//                viewHolder.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        TextView mTitle=v.findViewById(R.id.post_title_detail);
//                        TextView mDesc=v.findViewById(R.id.post_desc_detail);
//                        ImageView mImage=v.findViewById(R.id.post_imeg_detail);
//
//                        String mTitleget=mTitle.getText().toString();
//                        String mDescget=mDesc.getText().toString();
//                        Drawable drawable=mImage.getDrawable();
//                        Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
//
//                        Intent intent=new Intent(v.getContext(), DetailActivity.class);
//                        ByteArrayOutputStream stream=new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                        byte[] bytes=stream.toByteArray();
//                        intent.putExtra("image", bytes);
//                        intent.putExtra("title", mTitleget);
//                        intent.putExtra("description", mDescget);
//                        startActivity(intent);
//                    }
//                });
//
//
//                return new BlogViewHolder(view);
//            }
//        };
//        myRecyclerView.setAdapter(blogBlogViewHolderFirebaseRecyclerAdapter);
//         blogBlogViewHolderFirebaseRecyclerAdapter.startListening();
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            //    query=query.substring(0,1).toUpperCase()+query.substring(1);
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
              //  newText=newText.substring(0,1).toUpperCase()+newText.substring(1);
                firebaseSearch(newText);

                return true;
            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d("LOG_TAG,", "Закрыто");
                FirebaseRecyclerAdapter<Blog, HomeViewModel> blogBlogViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, HomeViewModel>(Blog.class, R.layout.blog_row, HomeViewModel.class, DBR) {

                    @Override
                    protected void populateViewHolder(HomeViewModel blogViewHolder, Blog blog, int i) {
                        final String PostKey=getRef(i).getKey();
//                blogViewHolder.setTitle(blog.getTitle());
//                blogViewHolder.setDescription(blog.getDescription());
//                blogViewHolder.setImage(blog.getImage());

                        blogViewHolder.setDetails(getContext(), blog.getTitle(), blog.getDescription(),blog.getImage());

                        blogViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent clickPostIntent = new Intent(getActivity(), DetailActivity.class);

                                clickPostIntent.putExtra("PostKey", PostKey);
                                startActivity(clickPostIntent);
                                //  Toast.makeText(getContext(),PostKey , Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                };myRecyclerView.setAdapter(blogBlogViewHolderFirebaseRecyclerAdapter);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
