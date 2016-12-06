package com.anaheimelectronics.controller;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.ImageView;

import com.anaheimelectronics.R;
import com.anaheimelectronics.common.AEBaseActivity;
import com.anaheimelectronics.common.AETools;
import com.anaheimelectronics.model.ImageData;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
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


    @Bind(R.id.image_view) ImageView imageView;

    private ImageData mImageData;
    private ImageViewZoomHelper mZoomHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.image_viewer_layout);
        ButterKnife.bind(this);

        mZoomHelper = new ImageViewZoomHelper(imageView);
        mImageData = new ImageData();
        if(getIntent() != null){
            mImageData.local_path = getIntent().getStringExtra(Extrakey.LOCAL_PATH);
            mImageData.url = getIntent().getStringExtra(Extrakey.URL);
        }

        updateImageView(mImageData.local_path);
    }

    private void updateImageView(@Nullable final String path){

        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {

                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 128;
                Bitmap bitmap = BitmapFactory.decodeFile(path,options);
                subscriber.onNext(bitmap);

                options.inSampleSize = 2;
                Bitmap srcBitmap = BitmapFactory.decodeFile(path,options);
                subscriber.onNext(srcBitmap);

                subscriber.onCompleted();
                if(bitmap != null && bitmap.isRecycled()){
                    bitmap = null;
                }
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                showImage(bitmap);
            }
        });
    }

    private void showImage(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
        mZoomHelper.update();
    }
}
