package com.sodo.kumail.salahhelper;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by kumail on 7/16/2016.
 */

public class VolleySingleton {

    private static RequestQueue requestQueue=null;

    private static ImageLoader imageLoader=null;

    public VolleySingleton()
    {
        if (requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(MyApplication.getMyApplication().getApplicationContext());


        }

        if (imageLoader==null)
        {
            imageLoader=new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

                LruCache<String,Bitmap> lruCache= new LruCache<>(20);
                @Override
                public Bitmap getBitmap(String url) {
                    return lruCache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {

                    lruCache.put(url,bitmap);
                }
            });
        }



    }
    public static RequestQueue getRequestQueue()
    {
        VolleySingleton volleySingleton= new VolleySingleton();

        return requestQueue;
    }
    public static ImageLoader getImageLoader()
    {
        VolleySingleton volleySingleton= new VolleySingleton();


        return imageLoader;

    }

}
