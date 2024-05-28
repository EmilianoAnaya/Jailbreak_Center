package com.example.jailbreak_center;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuSelection extends AppCompatActivity {

    private Button screen_3ds, screen_ps2,screen_psp;
    private ImageButton login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textView = findViewById(R.id.textView);
        textView.setText("Presionaste el Boton: "+getIntent().getIntExtra("data",0));

        screen_3ds = (Button) findViewById(R.id.btn_n3ds);
        screen_ps2 = (Button) findViewById(R.id.btn_ps2);
        screen_psp = (Button) findViewById(R.id.btn_psp);
        login_btn = (ImageButton) findViewById(R.id.img_user_login);

        Connect connect = new Connect(this);
        User_class user_class;
        if(connect.check_Session()){
            user_class = connect.session_getData();
            login_btn.setImageBitmap(user_class.getImage());
        }

        screen_3ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_Navigation.open_screen(MenuSelection.this, N3DS_Main_Screen.class);
            }
        });

        screen_ps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_Navigation.open_screen(MenuSelection.this, PS2_Main_Screen.class);
            }
        });

        screen_psp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_Navigation.open_screen(MenuSelection.this, PSP_Main_Screen.class);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_Navigation.login_screen(MenuSelection.this);
            }
        });
    }
}