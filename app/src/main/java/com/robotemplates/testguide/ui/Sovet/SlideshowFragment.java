package com.robotemplates.testguide.ui.Sovet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.robotemplates.testguide.Blog;
import com.robotemplates.testguide.DetailActivity;
import com.robotemplates.testguide.R;

public class SlideshowFragment extends Fragment {


    private RecyclerView myRecyclerView;

    public Query DBR;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sovet, container, false);

        DBR= FirebaseDatabase.getInstance().getReference().child("All").orderByChild("idcat").equalTo(2);   //  !!!!! ТЕСТОВАЯ !!!!!
        DBR.keepSynced(true);

        myRecyclerView=root.findViewById(R.id.recyclerView2);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, SlideshowViewModel> blogBlogViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, SlideshowViewModel>(Blog.class, R.layout.blog_row, SlideshowViewModel.class, DBR) {

            @Override
            protected void populateViewHolder(SlideshowViewModel blogViewHolder, Blog blog, int i) {
                final String PostKey=getRef(i).getKey();

                blogViewHolder.setDetails(getContext(), blog.getTitle(), blog.getDescription(),blog.getImage());

                blogViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent clickPostIntent = new Intent(getActivity(), DetailActivity.class);

                        clickPostIntent.putExtra("PostKey", PostKey);
                        startActivity(clickPostIntent);
                    }
                });
            }


        };myRecyclerView.setAdapter(blogBlogViewHolderFirebaseRecyclerAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //    firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // firebaseSearch(newText);
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
