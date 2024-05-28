package com.example.jailbreak_center;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class PSP_Main_Screen extends TemplateScreen_Guides {

    protected int getLayoutResource() {
        return R.layout.activity_forum_main_screen;
    }

    @Override
    protected Fragment getFirstFragment() {
        return new PSP_Guide_Screen();
    }

    @Override
    protected Fragment getSecondFragment() {
        Forums_Fragment_Screen fragment = new Forums_Fragment_Screen();
        Bundle args = new Bundle();
        args.putString("console_forum","PSP");
        args.putInt("console_logo",R.drawable.psp_logo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getDateResource() {
        return R.string.DATE_PSP;
    }

    @Override
    protected int getSiteResource() {
        return R.string.SITE_PSP;
    }

    @Override
    protected String getLogoResource() {
        return "https://i.imgur.com/Ozm0NC9.png";
    }
}
