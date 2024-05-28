package com.example.jailbreak_center;

import android.content.Context;
import android.content.Intent;

public class Tools_Navigation {
    public static void open_screen(Context context, Class<?> activityClass){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, activityClass);
                context.startActivity(intent);
            }
        }).start();
    }

    public static void login_screen(Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connect connect = new Connect(context);
                Intent intent;
                if(connect.check_Session()){
                    intent = new Intent(context, User_Profile.class);

                }else{
                    intent = new Intent(context, User_login.class);
                }
                context.startActivity(intent);
            }
        }).start();
    }

    public static void update_screens(Context context, Class<?> activityClass){
        Intent intent = new Intent(context, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
