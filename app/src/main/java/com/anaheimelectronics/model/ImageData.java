package com.anaheimelectronics.model;

/**
 * Created by zacharyli on 2016/12/6.
 */

public class ImageData{

    public String local_path = null;
    public String url = null;
    public void clear(){
        local_path = null;
        url = null;
    }
    public ImageData(){
        clear();
    }
}
