package com.example.files.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.files.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class AddPostFragment extends Fragment {
    private ImageView addImage;
    private Button add, back, save;
    private EditText description, title;

    String imgString = null, loc=null;


    FragmentManager fragmentManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_add_post, container, false);
        addImage = (ImageView) rootView.findViewById(R.id.addImage);
        add = (Button) rootView.findViewById(R.id.add);
        back = (Button) rootView.findViewById(R.id.back);
        description = (EditText) rootView.findViewById(R.id.description);
        title = (EditText) rootView.findViewById(R.id.title);
        //location = (TextView) rootView.findViewById(R.id.location);




        View.OnClickListener addImageButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT , android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        };
        addImage.setOnClickListener(addImageButtonOnClickListener);


        View.OnClickListener addButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add();
            }
        };
        add.setOnClickListener(addButtonOnClickListener);
        View.OnClickListener backButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        };
        back.setOnClickListener(backButtonOnClickListener);
        View.OnClickListener locButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager =  getActivity().getSupportFragmentManager();
               // fragmentManager.beginTransaction().add(R.id.frgmCont, fr).commit();
            }
        };
      //  location.setOnClickListener(locButtonOnClickListener);
        return rootView;
    }

    private void Add(){
        ImageMusicFragment imf = (ImageMusicFragment) getActivity().getSupportFragmentManager().findFragmentByTag("imageMusic");
        Bundle bundle = new Bundle();
        bundle.putString("imgString", imgString);
        bundle.putString("title", title.getText().toString());
        bundle.putString("description", description.getText().toString());
        imf.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().show(imf).commit();
        imf.addPost();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private void Back() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        ImageMusicFragment imf = (ImageMusicFragment) getActivity().getSupportFragmentManager().findFragmentByTag("imageMusic");
        getActivity().getSupportFragmentManager().beginTransaction().show(imf).commit();
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent ReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, ReturnedIntent);

        if(requestCode == 1)
            if (resultCode == RESULT_OK) {
                imgString = ReturnedIntent.getData().toString();
                addImage.setImageURI(ReturnedIntent.getData());
            }
        if(imgString != null ){
            add.setVisibility(View.VISIBLE);
        }

    }
}