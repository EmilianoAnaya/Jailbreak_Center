package com.example.jailbreak_center;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class WatchForum_Screen extends AppCompatActivity {

    private Database database;
    private LinearLayout AnswersContainer;
    private String IDForum;
    private User_class user_class;
    public void updateAnswersForum(){
        AnswersContainer.removeAllViews();
        List<Answers_class> AnswersForums = new ArrayList<>();
        AnswersForums = database.get_AnswersForum(IDForum);
        if(!AnswersForums.isEmpty()){
            for(Answers_class Answer : AnswersForums){
                View AnswerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.answers_template, null);
                ImageView imageView = AnswerView.findViewById(R.id.profile_answer);
                TextView textView = AnswerView.findViewById(R.id.answer_user);
                String forum_description_format = getString(R.string.Forum_insert_description,
                                                            database.get_UserUsername(Answer.get_IDUser()),
                                                            Answer.get_Answer());

                imageView.setImageBitmap(database.get_UserImage(Answer.get_IDUser()));
                textView.setText(forum_description_format);
                AnswersContainer.addView(AnswerView);

                Space space = new Space(getApplicationContext());
                space.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        60
                ));
                AnswersContainer.addView(space);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_watch_forum_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        Forum_class TMPForum = (Forum_class) intent.getSerializableExtra("TMPForum");
        int console_logo = intent.getIntExtra("console_logo",0);

        AnswersContainer = findViewById(R.id.Forum_Answers);
        database = new Database(getApplicationContext(),null,null,1);
        IDForum = TMPForum.get_IDforo();

        ImageView forum_logo = findViewById(R.id.logo_forum);
        ImageView login_user = findViewById(R.id.img_user_login);
        ImageView forum_profile = findViewById(R.id.forum_profile);
        TextView forum_title = findViewById(R.id.forum_title);
        TextView forum_description = findViewById(R.id.forum_description);
        Button btn_regresar = findViewById(R.id.regresar);

        EditText new_answer = findViewById(R.id.txt_answer);
        Button send_answer = findViewById(R.id.btn_sendanswer);

        Connect connect = new Connect(getApplicationContext());
        if(connect.check_Session()){
            user_class = connect.session_getData();
            login_user.setImageBitmap(user_class.getImage());
        }

        forum_profile.setImageBitmap(database.get_UserImage(TMPForum.get_IDusuario()));
        forum_logo.setImageResource(console_logo);
        forum_title.setText(TMPForum.get_ForumTitle());
        String forum_description_format = getString(R.string.Forum_insert_description,
                                                    database.get_UserUsername(TMPForum.get_IDusuario()),
                                                    TMPForum.get_ForumDescription());
        forum_description.setText(forum_description_format);

        updateAnswersForum();

        send_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connect.check_Session()){
                    String txt_answer = new_answer.getText().toString().trim();
                    if (!txt_answer.isEmpty()) {
                        String res = database.SendAnswer(IDForum, user_class.getId_user(), txt_answer);
                        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
                        updateAnswersForum();
                        new_answer.setText("");
                    }else{
                        Toast.makeText(getApplicationContext(),"Missing Fields to be filled in",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Tools_Navigation.login_screen(WatchForum_Screen.this);
                }
            }
        });

        login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_Navigation.login_screen(WatchForum_Screen.this);
            }
        });

        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}