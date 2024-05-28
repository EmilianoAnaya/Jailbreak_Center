package com.example.jailbreak_center;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Tools_InsertInfoGuide {
    public static void Insert_Info_Guide(Context context, ViewGroup parentLayout, int Date, int Site, String Logo){

        View view = LayoutInflater.from(context).inflate(R.layout.info_guides, parentLayout, true);

        TextView GuideDate = parentLayout.findViewById(R.id.guide_date);
        TextView GuideSite = parentLayout.findViewById(R.id.guide_site);
        ImageView GuideLogo = parentLayout.findViewById(R.id.logo_guide);

        Bitmap bitmap;
        bitmap = downloadImage(Logo);
        GuideDate.setText(context.getString(Date));
        GuideSite.setText(context.getString(Site));
        GuideLogo.setImageBitmap(bitmap);
    }

    public static void Insert_Info_Forums(Context context, ViewGroup parentLayout, int Logo){

        ImageView LogoForum = parentLayout.findViewById(R.id.logo_forum);
        LogoForum.setImageResource(Logo);
    }

    public static void layouts_visibility(Context context, Button button, LinearLayout requierements){
        Drawable currentDrawable = button.getCompoundDrawablesRelative()[2];
        Drawable newDrawable;

        if (currentDrawable.getConstantState().equals(ContextCompat.getDrawable(context, R.drawable.down_arrow).getConstantState())) {
            newDrawable = ContextCompat.getDrawable(context, R.drawable.up_arrow);
            requierements.setVisibility(View.VISIBLE);
        } else {
            newDrawable = ContextCompat.getDrawable(context, R.drawable.down_arrow);
            requierements.setVisibility(View.GONE);
        }
        button.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, newDrawable, null);
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap imageBitmap = null;

            try {
                InputStream inputStream = new URL(imageUrl).openStream();
                imageBitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imageBitmap;
        }
    }

    private static Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            DownloadImageTask downloadImageTask = new DownloadImageTask();
            bitmap = downloadImageTask.execute(imageUrl).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
