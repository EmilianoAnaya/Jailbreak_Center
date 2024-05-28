package com.example.jailbreak_center;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PS2_Guide_Screen extends Fragment {

    private Button btn_requierements, btn_section1, btn_section2,btn_section3;
    private LinearLayout lay_requierements, lay_section1, lay_section2,lay_section3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_ps2_guide_screen, container, false);

        TextView textViewLink = view.findViewById(R.id.textView6);
        btn_requierements = (Button) view.findViewById(R.id.button);
        btn_section1 = (Button) view.findViewById(R.id.btn_section1);
        btn_section2 = (Button) view.findViewById(R.id.btn_section2);
        btn_section3 = (Button) view.findViewById(R.id.btn_section3);
        lay_requierements = (LinearLayout) view.findViewById(R.id.layout_requierements);
        lay_requierements.setVisibility(View.GONE);
        lay_section1 = (LinearLayout) view.findViewById(R.id.layout_section1);
        lay_section1.setVisibility(View.GONE);
        lay_section2 = (LinearLayout) view.findViewById(R.id.layout_section2);
        lay_section2.setVisibility(View.GONE);
        lay_section3 = (LinearLayout) view.findViewById(R.id.layout_section3);
        lay_section3.setVisibility(View.GONE);

        btn_requierements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_requierements,lay_requierements);
            }
        });

        btn_section1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section1,lay_section1);
            }
        });

        btn_section2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section2,lay_section2);

            }
        });

        btn_section3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section3,lay_section3);

            }
        });

        textViewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://mega.co.nz/#!3hYQGTTZ!-WXfusQuwIjKqlvwYGhTl1d2BMxiE7VPHGZ-azMcEAY";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        return view;
    }
}