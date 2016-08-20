package com.juliodev.taxihaiti.mFirebase;

import android.content.Context;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.juliodev.taxihaiti.mListView.CustomAdapter;
import com.juliodev.taxihaiti.model.InsertUser;

import java.util.ArrayList;

/**
 * Created by Julio on 8/19/2016.
 */
public class Fire {
    Context c;
    CustomAdapter adapter;
    ListView lvMessage;
    Firebase firebase;
    String DB_URL;
    ArrayList<InsertUser> insertUsers = new ArrayList<>();

    public Fire(Context c, ListView lvMessage, String DB_URL) {
        this.c = c;
        this.lvMessage = lvMessage;
        this.DB_URL = DB_URL;

        //INITIALISE
        Firebase.setAndroidContext(c);
        firebase = new Firebase(DB_URL);

    }
    // Savedata
    public void saveOnline( String nomcomplet, String telephone, String addresse, String plaque, String typevehicule, String photoUser){
        InsertUser insert = new InsertUser();
        insert.setNomcomplet(nomcomplet);
        insert.setTelephone(telephone);
        insert.setAddresse(addresse);
        insert.setPlaque(plaque);
        insert.setPhotoUser(photoUser);
    }
    //Retrive data
    public void retriveData()
    {
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchUpdate(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchUpdate(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    private void fetchUpdate(DataSnapshot dataSnapshot){
        insertUsers.clear();
        for (DataSnapshot ds: dataSnapshot.getChildren()){
            InsertUser ins = new InsertUser();
            ins.setPhotoUser(ds.getValue(InsertUser.class).getPhotoUser());
            ins.setNomcomplet(ds.getValue(InsertUser.class).getNomcomplet());
            ins.setAddresse(ds.getValue(InsertUser.class).getAddresse());
            insertUsers.add(ins);
        }
        if (insertUsers.size()>0){
            adapter=  new CustomAdapter(c,insertUsers);
            lvMessage.setAdapter(adapter);

        }
    }

    // Gestion de Recherche avec un query
    //Gestion details onSetItemListerner
}
