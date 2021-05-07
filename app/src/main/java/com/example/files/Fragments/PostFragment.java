package com.example.files.Fragments;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.files.Classes.Post;
import com.example.files.R;

import java.io.IOException;

public class PostFragment extends Fragment {
    private ImageView button;
    private TextView title;
    private TextView desc;
    private Button back;

    private Post post;


    PostFragment(Post post){
        this.post = post;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);

        button = (ImageView) rootView.findViewById(R.id.imageMusic);
        title = (TextView) rootView.findViewById(R.id.titlePost);
        desc = (TextView) rootView.findViewById(R.id.descriptionPost);
        desc.setMovementMethod(new ScrollingMovementMethod());
        back = (Button) rootView.findViewById(R.id.back);

        Initialize();

        View.OnClickListener backButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        };
        back.setOnClickListener(backButtonOnClickListener);

        return rootView;
    }

    private void Back() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        ImageMusicFragment imf = (ImageMusicFragment) getActivity().getSupportFragmentManager().findFragmentByTag("imageMusic");
        getActivity().getSupportFragmentManager().beginTransaction().show(imf).commit();
    }

    public void Initialize(){
        button.setImageURI(Uri.parse(post.getImgString()));
        title.setText(post.getTitle());
        desc.setText(post.getDescription());

    }
}