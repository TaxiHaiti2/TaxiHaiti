package com.juliodev.taxihaiti.mListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.juliodev.taxihaiti.R;
import com.juliodev.taxihaiti.mPicasso.PicassoClient;
import com.juliodev.taxihaiti.model.InsertUser;

import java.util.ArrayList;

/**
 * Created by Julio on 8/19/2016.
 */
public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<InsertUser> insertUsers;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<InsertUser> insertUsers) {
        this.c = c;
        this.insertUsers = insertUsers;
    }
    @Override
    public int getCount() {
        return insertUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return insertUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.model,parent,false);
        }
        MyViewHolder holder = new MyViewHolder(convertView);
        //BIDING DADA
        holder.hName.setText(insertUsers.get(position).getNomcomplet());
        holder.hAddress.setText(insertUsers.get(position).getAddresse());
        //Picasso
        PicassoClient.downloadImage(c,insertUsers.get(position).getPhotoUser(),holder.hImageUser);
        return convertView;
    }
}
