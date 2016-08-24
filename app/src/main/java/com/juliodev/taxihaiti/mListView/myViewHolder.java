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
        hName = (TextView) itemView.findViewById(R.id.facTvName);
        hAddress = (TextView) itemView.findViewById(R.id.tvAdresse);
        hPhone = (TextView) itemView.findViewById(R.id.tvPhone);
        Hplaque = (TextView) itemView.findViewById(R.id.tvPlaque);
        hTypeVehicule = (TextView) itemView.findViewById(R.id.typevehicule);
    }
}
