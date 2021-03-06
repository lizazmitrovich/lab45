package com.example.files.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.files.Classes.JSONWriterReader;
import com.example.files.Classes.Post;
import com.example.files.Classes.PostAdapter;
import com.example.files.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ImageMusicFragment extends Fragment {
    private RecyclerView list;

    private String imgString = null;
    private String title;
    private String description;

    private ArrayList<Post> content = new ArrayList<>();
    private PostAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_image_music, container, false);

        list = (RecyclerView) rootView.findViewById(R.id.list);
        FloatingActionButton addPost = rootView.findViewById(R.id.addPost);

        View.OnClickListener addPostButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hide(new AddPostFragment());
            }
        };
        addPost.setOnClickListener(addPostButtonOnClickListener);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(list);

        OpenData();

        return rootView;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.RIGHT:
                    content.remove(position);
                    Add();
                    SaveData();
                    break;
            }
        }
    };

    public void Hide(Fragment f){
        getActivity().getSupportFragmentManager().beginTransaction().hide(this).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, f,"addPost").commit();
    }

    public void addPost(){
        Bundle bundle = new Bundle();
        bundle = getArguments();
        if (bundle != null) {
            imgString = bundle.getString("imgString");
            title = bundle.getString("title");
            description = bundle.getString("description");
            AddContent();
            Add();
        }
        else{
            imgString = null;
        }
    }

    public void Add() {

        PostAdapter.OnStateClickListener stateClickListener = new PostAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Post post, int position) {
                PostFragment postFragment = new PostFragment(post);
                Hide(postFragment);
            }
        };

        adapter = new PostAdapter(getActivity(), content, stateClickListener);
        list.setAdapter(adapter);
    }

    public void AddContent(){
        ArrayList<Post> temp = new ArrayList<>();
        temp.add(new Post(imgString, title, description));

        temp.addAll(content);
        content.clear();
        content.addAll(temp);
        SaveData();
    }

    public void SaveData(){

        boolean result = JSONWriterReader.exportToJSON(getActivity().getApplicationContext(), content);
        if(result){
            Toast.makeText(getActivity(), "??????????????", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(), "???? ??????????????", Toast.LENGTH_LONG).show();
        }
    }

    public void OpenData(){
        content = JSONWriterReader.importFromJSON(getActivity().getApplicationContext());
        if(content!=null){
            Add();
            Toast.makeText(getActivity().getApplicationContext(), "???????????? ??????????????????????????", Toast.LENGTH_LONG).show();
        }
        else{
            content = new ArrayList<>();
            Toast.makeText(getActivity().getApplicationContext(), "???? ?????????????? ??????????????", Toast.LENGTH_LONG).show();
        }
    }
}