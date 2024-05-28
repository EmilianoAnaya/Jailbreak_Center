package com.example.jailbreak_center;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class N3DS_Main_Screen extends TemplateScreen_Guides {

    protected int getLayoutResource() {
        return R.layout.activity_forum_main_screen;
    }

    @Override
    protected Fragment getFirstFragment() {
        return new N3DS_Guide_Screen();
    }
    @Override
    protected Fragment getSecondFragment() {
        Forums_Fragment_Screen fragment = new Forums_Fragment_Screen();
        Bundle args = new Bundle();
        args.putString("console_forum","N3DS");
        args.putInt("console_logo",R.drawable.n3ds_logo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getDateResource() {
        return R.string.DATE_3DS;
    }

    @Override
    protected int getSiteResource() {
        return R.string.SITE_3DS;
    }

    @Override
    protected String getLogoResource() {
        return "https://i.imgur.com/zF8yfht.png";
    }
}