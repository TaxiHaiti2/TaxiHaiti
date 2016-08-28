package com.juliodev.taxihaiti.mPicasso;

import android.content.Context;
import android.widget.ImageView;

import com.juliodev.taxihaiti.R;
import com.juliodev.taxihaiti.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

/**
 * Created by Julio on 8/19/2016.
 */
public class PicassoClient {
    public static void downloadImage(Context c, String url , ImageView img){
        if (url!=null && url.length()>0){
            Picasso.with(c).load(url).placeholder(R.drawable.nopix).transform(new RoundedTransformation(120, 0)).centerCrop().into(img);
        }else{
            Picasso.with(c).load(R.drawable.nopix).transform(new RoundedTransformation(120, 0)).fit().centerCrop().into(img);
        }
    }
}
