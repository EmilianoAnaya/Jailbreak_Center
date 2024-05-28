package com.example.jailbreak_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.List;

public class Forums_Fragment_Screen extends Fragment {

    private Button btn_newforum;
    private List<Forum_class> Forums;
    private LinearLayout forums_container;
    private String console_forum = "";
    private int console_logo = 0;

    private final ActivityResultLauncher<Intent> createForumLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    updateForumsList();
                }
            }
    );
    private void updateForumsList(){
        Database database = new Database(getActivity(),null,null,1);
        forums_container.removeAllViews();
        Forums = database.get_Forums(console_forum);
        if(!Forums.isEmpty()){
            for(Forum_class TMPForum : Forums){
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);


                ImageView imageView = new ImageView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        122,
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
                layoutParams.setMargins(50, 0, 50, 0);
                imageView.setLayoutParams(layoutParams);
                imageView.setImageBitmap(database.get_UserImage(TMPForum.get_IDusuario()));
                linearLayout.addView(imageView);

                int Btn_Text = ContextCompat.getColor(getContext(), R.color.btn_text);
                Button button = new Button(getContext());
                button.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                button.setText(TMPForum.get_ForumTitle());
                button.setBackgroundResource(R.drawable.button_state);
                button.setTextColor(Btn_Text);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(requireContext(), WatchForum_Screen.class);
                        intent.putExtra("console_logo",console_logo);
                        intent.putExtra("TMPForum",TMPForum);
                        startActivity(intent);
                    }
                });

                linearLayout.addView(button);

                forums_container.addView(linearLayout);

                Space space = new Space(getContext());
                space.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        128
                ));
                forums_container.addView(space);
            }

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_forums_guides, container, false);
        Connect connect = new Connect(getActivity());
        forums_container = view.findViewById(R.id.forums_container);
        Bundle args = getArguments();
        if (args != null){
            console_forum = args.getString("console_forum");
            console_logo = args.getInt("console_logo",0);
        }

        updateForumsList();

        btn_newforum = (Button) view.findViewById(R.id.newforum);
        btn_newforum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connect.check_Session()){
                    Intent intent = new Intent(requireContext(), CreateForum_Screen.class);
                    intent.putExtra("console_forum",console_forum);
                    intent.putExtra("console_logo",console_logo);
                    createForumLauncher.launch(intent);
                }else{
                    Tools_Navigation.open_screen(getActivity(),User_login.class);
                }

            }
        });
        return view;
    }
}