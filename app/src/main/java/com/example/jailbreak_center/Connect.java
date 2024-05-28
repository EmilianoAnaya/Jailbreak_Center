package com.example.jailbreak_center;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Connect {
    private static final String SESSION = "UserSession";
    private static final String KEY_ID = "id_user";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IMAGE = "image";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Connect(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SESSION,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void session_setData(String id_user, String username, Bitmap image){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String image_user = Base64.encodeToString(byteArray,Base64.DEFAULT);

        editor.putString(KEY_ID,id_user);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_IMAGE,image_user);
        editor.apply();
    }

    public User_class session_getData(){
        String txt_userid = sharedPreferences.getString(KEY_ID,"");
        String txt_username = sharedPreferences.getString(KEY_USERNAME,"");
        String txt_image = sharedPreferences.getString(KEY_IMAGE,"");

        byte[] byteArray = Base64.decode(txt_image,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,0, byteArray.length);

        User_class TMPUser = new User_class();
        TMPUser.setId_user(txt_userid);
        TMPUser.setUsername(txt_username);
        TMPUser.setImage(bitmap);
        return TMPUser;
    }

    public Boolean check_Session(){
        return sharedPreferences.contains(KEY_USERNAME);
    }

    public void close_session(){
        editor.clear();
        editor.apply();
    }

}
