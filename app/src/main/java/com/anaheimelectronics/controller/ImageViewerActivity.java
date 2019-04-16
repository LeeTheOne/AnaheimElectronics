package com.anaheimelectronics.controller;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.anaheimelectronics.R;
import com.anaheimelectronics.common.AEBaseActivity;
import com.anaheimelectronics.common.AETools;
import com.anaheimelectronics.model.ImageData;

import uk.co.senab.photoview.ImageViewZoomHelper;

/**
 * Created by zacharyli on 2016/12/6.
 */

public class ImageViewerActivity extends AEBaseActivity {

    public static class Extrakey{
        public static final String LOCAL_PATH = "local_path";
        public static final String URL = "url";
    }

    public static Intent obtainIntent(Context context, ImageData data){
        if(context == null){
            context = AETools.APPLICATION_CONTEXT;
        }
        if(data == null){
            data = new ImageData();
        }
        Intent intent = new Intent(context,ImageViewerActivity.class);
        intent.putExtra(Extrakey.LOCAL_PATH,data.local_path);
        intent.putExtra(Extrakey.URL,data.url);
        return intent;
    }


    ImageView imageView = findViewById(R.id.image_view );
    ProgressBar progressBar = findViewById(R.id.image_viewer_progress_bar);

    private ImageData mImageData;
    private ImageViewZoomHelper mZoomHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.image_viewer_layout);

        mZoomHelper = new ImageViewZoomHelper(imageView);
        mImageData = new ImageData();
        if(getIntent() != null){
            mImageData.local_path = getIntent().getStringExtra(Extrakey.LOCAL_PATH);
            mImageData.url = getIntent().getStringExtra(Extrakey.URL);
        }

        updateImageView(mImageData.local_path);
    }

    private void updateImageView(@NonNull final String path){


    }

    private void showImage(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
        mZoomHelper.update();
    }

    private void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void dismissProgress(){
        progressBar.setVisibility(View.GONE);
    }
}
