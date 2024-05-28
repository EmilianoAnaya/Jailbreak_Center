package com.example.jailbreak_center;

import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public abstract class TemplateScreen_Guides extends AppCompatActivity {
    private ImageButton login_user;
    private PageAdapter pagerAdapter;
    private ViewPager2 viewPager;
    private Button guide_btn, forums_btn;
    protected abstract int getLayoutResource();
    protected abstract Fragment getFirstFragment();
    protected abstract Fragment getSecondFragment();
    protected abstract int getDateResource();
    protected abstract int getSiteResource();
    protected abstract String getLogoResource();


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(getLayoutResource());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()).toPlatformInsets();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            }
            return insets;
        });

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(getFirstFragment());
        fragments.add(getSecondFragment());

        pagerAdapter = new PageAdapter(this, fragments);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        login_user = findViewById(R.id.img_user_login);
        guide_btn = findViewById(R.id.guide_btn);
        forums_btn = findViewById(R.id.forums_btn);

        Tools_InsertInfoGuide.Insert_Info_Guide(this, (ViewGroup) findViewById(android.R.id.content), getDateResource(), getSiteResource(), getLogoResource());

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    guide_btn.setBackgroundTintList(ContextCompat.getColorStateList(TemplateScreen_Guides.this, R.color.btn_on));
                    forums_btn.setBackgroundTintList(ContextCompat.getColorStateList(TemplateScreen_Guides.this, R.color.btn_off));
                } else if (position == 1) {
                    guide_btn.setBackgroundTintList(ContextCompat.getColorStateList(TemplateScreen_Guides.this, R.color.btn_off));
                    forums_btn.setBackgroundTintList(ContextCompat.getColorStateList(TemplateScreen_Guides.this, R.color.btn_on));
                }
            }
        });

        Connect connect = new Connect(this);
        User_class user_class;
        if(connect.check_Session()){
            user_class = connect.session_getData();
            login_user.setImageBitmap(user_class.getImage());
        }

        login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_Navigation.login_screen(TemplateScreen_Guides.this);
            }
        });

        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        forums_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
    }
}