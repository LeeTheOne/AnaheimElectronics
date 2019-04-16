package com.anaheimelectronics.model;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.anaheimelectronics.common.AETools;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zacharyli on 2016/12/5.
 */

public class CustomAlbumDataHelper {

    private static CustomAlbumDataHelper sInstance;
    private CustomAlbumDataHelper(){

    }

    public static CustomAlbumDataHelper getInstance(){
        if(sInstance == null){
            synchronized (CustomAlbumDataHelper.class) {
                if (sInstance == null) {
                    sInstance = new CustomAlbumDataHelper();
                }
            }
        }
        return sInstance;
    }


    private List<AlbumItem> obtainData(){
        ArrayList<AlbumItem> list = new ArrayList<AlbumItem>();
        String columns[] = new String[] { MediaStore.Images.Media._ID, MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.PICASA_ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.SIZE, MediaStore.Images.Media.BUCKET_DISPLAY_NAME,  MediaStore.Images.Media.DATE_MODIFIED};
        Cursor cur = AETools.APPLICATION_CONTEXT.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null,
                MediaStore.Images.Media.DATE_MODIFIED+" desc");
        if(cur != null){
            if (cur.moveToFirst()) {
                int photoIDIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
                int photoPathIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                int bucketDisplayNameIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                int bucketIdIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID);
                int photoModifyDateIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED);
                int photoSizeIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);
                do {
                    int _id = cur.getInt(photoIDIndex);
                    String path = cur.getString(photoPathIndex);
                    String bucketName = cur.getString(bucketDisplayNameIndex);
                    String bucketId = cur.getString(bucketIdIndex);
                    long modifyDate = cur.getLong(photoModifyDateIndex);
                    long photoSize = cur.getLong(photoSizeIndex);
                    AlbumItem albumItem = new AlbumItem();
                    albumItem._id = _id;
                    albumItem.url = path;
                    list.add(albumItem);
                }while(cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }
}
