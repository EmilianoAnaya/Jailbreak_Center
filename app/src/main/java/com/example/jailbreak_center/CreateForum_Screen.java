package com.example.jailbreak_center;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateForum_Screen extends AppCompatActivity {

    private ImageButton login_user;
    private Button regresar_btn, btn_SendForum;
    private EditText ForumTitle, ForumDescription;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_forum);
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

        Intent intent = getIntent();
        String console_forum = intent.getStringExtra("console_forum");
        int console_logo = intent.getIntExtra("console_logo",0);

        login_user = findViewById(R.id.img_user_login);
        regresar_btn = findViewById(R.id.regresar);

        ForumTitle = findViewById(R.id.txt_ForumTitle);
        ForumDescription = findViewById(R.id.txt_ForumDescription);
        btn_SendForum = findViewById(R.id.btn_SendForum);

        ImageView imageView = findViewById(R.id.logo_forum);
        imageView.setImageResource(console_logo);

        Connect connect = new Connect(this);
        final User_class user_class = connect.session_getData();
        login_user.setImageBitmap(user_class.getImage());

        btn_SendForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_title,txt_description;
                txt_title = ForumTitle.getText().toString().trim();
                txt_description = ForumDescription.getText().toString().trim();
                if(!txt_title.isEmpty() && !txt_description.isEmpty()){
                    Database database = new Database(getApplicationContext(),null,null,1);
                    String res = database.UploadForum(txt_title,txt_description, user_class.getId_user(), console_forum);
                    Toast.makeText(CreateForum_Screen.this,res,Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                else{
                    Toast.makeText(CreateForum_Screen.this,"Missing Fields to be filled in",Toast.LENGTH_SHORT).show();
                }
            }
        });

        login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_Navigation.login_screen(CreateForum_Screen.this);
            }
        });

        regresar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


