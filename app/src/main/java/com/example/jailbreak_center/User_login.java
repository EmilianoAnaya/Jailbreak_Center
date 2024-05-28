package com.example.jailbreak_center;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class User_login extends AppCompatActivity{

    private Button iniciar_sesion, register;
    private EditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        iniciar_sesion = (Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_register);
        Connect connect = new Connect(User_login.this);

        if(connect.check_Session()){Tools_Navigation.open_screen(User_login.this,User_Profile.class);}

        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username, txt_password;
                txt_username = username.getText().toString().trim();
                txt_password = password.getText().toString().trim();
                if(!txt_password.isEmpty() && !txt_username.isEmpty()){
                    Database db = new Database(getApplicationContext(), null, null, 1);
                    User_class info_user = db.Login(txt_username,txt_password);
                    if(info_user.getImage() != null){
                        Toast.makeText(User_login.this,"User Login Success",Toast.LENGTH_SHORT).show();
                        connect.session_setData(info_user.getId_user(), info_user.getUsername(), info_user.getImage());
                        Tools_Navigation.update_screens(User_login.this,MenuSelection.class);
                        finish();
                    }else{
                        Toast.makeText(User_login.this,"This Username or Password are incorrect",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(User_login.this,"Missing fields to be filled in",Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_Navigation.open_screen(User_login.this,User_Register.class);
            }
        });
    }
}