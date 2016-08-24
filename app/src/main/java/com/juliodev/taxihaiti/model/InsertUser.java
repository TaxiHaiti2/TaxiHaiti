package com.juliodev.taxihaiti.model;

import java.io.Serializable;

/**
 * Created by Julio on 8/11/2016.
 */
public class InsertUser implements Serializable {

    public String getNomcomplet() {
        return nomcomplet;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddresse() {
        return addresse;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public void setTypevehicule(String typevehicule) {
        this.typevehicule = typevehicule;
    }

    String nomcomplet;
    String telephone;
    String addresse;
    String plaque;
    String typevehicule;
    String photoUser;

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }

    public String getTypevehicule() {
        return typevehicule;
    }



    public InsertUser() {
    }

    public InsertUser(String nomcomplet, String telephone, String addresse, String plaque , String typevehicule) {
        this.nomcomplet = nomcomplet;
        this.telephone = telephone;
        this.addresse = addresse;
        this.plaque = plaque;
        this.typevehicule = typevehicule;
    }

}
