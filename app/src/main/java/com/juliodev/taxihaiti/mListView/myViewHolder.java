package com.juliodev.taxihaiti.mListView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juliodev.taxihaiti.R;

/**
 * Created by Julio on 8/19/2016.
 */
public class MyViewHolder {
    ImageView hImageUser;
    TextView hName,hAddress, hPhone,Hplaque,hTypeVehicule;
    public MyViewHolder(View itemView) {
        hImageUser = (ImageView) itemView.findViewById(R.id.ivImageUser);
        hName = (TextView) itemView.findViewById(R.id.tvName);
        hAddress = (TextView) itemView.findViewById(R.id.tvAdresse);
    }
}
