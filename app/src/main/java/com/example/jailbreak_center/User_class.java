package com.example.jailbreak_center;

import android.graphics.Bitmap;

public class User_class {

    private String id_user;
    private String username;
    private Bitmap image;

    public User_class(){
        this.id_user = "";
        this.image = null;
        this.username = "";
    }
    public void setId_user(String id_user){
        this.id_user = id_user;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setImage(Bitmap image){
        this.image = image;
    }
    public String getId_user(){
        return id_user;
    }
    public String getUsername(){
        return username;
    }
    public Bitmap getImage() {
        return image;
    }
}
