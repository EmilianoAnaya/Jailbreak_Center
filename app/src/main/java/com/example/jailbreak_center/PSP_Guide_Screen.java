package com.example.jailbreak_center;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class PSP_Guide_Screen extends Fragment {
    private Button btn_requierements_psp, btn_section1_psp, btn_section2_psp,btn_section3_psp,btn_section4_psp,btn_section5_psp,btn_section6_psp,btn_section7_psp;
    private LinearLayout layout_requierements_psp, layout_section1_psp, layout_section2_psp,layout_section3_psp,layout_section4_psp,layout_section5_psp,layout_section6_psp,layout_section7_psp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_psp_guide_screen, container, false);

        btn_requierements_psp = (Button) view.findViewById(R.id.button_psp);
        btn_section1_psp = (Button) view.findViewById(R.id.btn_section1);
        btn_section2_psp = (Button) view.findViewById(R.id.btn_section2_psp);
        btn_section3_psp = (Button) view.findViewById(R.id.btn_section3_psp);
        btn_section4_psp = (Button) view.findViewById(R.id.btn_section4_psp);
        btn_section5_psp = (Button) view.findViewById(R.id.btn_section5_psp);
        btn_section6_psp = (Button) view.findViewById(R.id.btn_section6_psp);
        btn_section7_psp = (Button) view.findViewById(R.id.btn_section7_psp);
        layout_requierements_psp = (LinearLayout) view.findViewById(R.id.layout_requierements_psp);
        layout_requierements_psp.setVisibility(View.GONE);
        layout_section1_psp = (LinearLayout) view.findViewById(R.id.layout_section1_psp);
        layout_section1_psp.setVisibility(View.GONE);
        layout_section2_psp = (LinearLayout) view.findViewById(R.id.layout_section2_psp);
        layout_section2_psp.setVisibility(View.GONE);
        layout_section3_psp = (LinearLayout) view.findViewById(R.id.layout_section3_psp);
        layout_section3_psp.setVisibility(View.GONE);
        layout_section4_psp = (LinearLayout) view.findViewById(R.id.layout_section4_psp);
        layout_section4_psp.setVisibility(View.GONE);
        layout_section5_psp = (LinearLayout) view.findViewById(R.id.layout_section5_psp);
        layout_section5_psp.setVisibility(View.GONE);
        layout_section6_psp = (LinearLayout) view.findViewById(R.id.layout_section6_psp);
        layout_section6_psp.setVisibility(View.GONE);
        layout_section7_psp = (LinearLayout) view.findViewById(R.id.layout_section7_psp);
        layout_section7_psp.setVisibility(View.GONE);



        btn_requierements_psp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_requierements_psp,layout_requierements_psp);
            }
        });
        btn_section1_psp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section1_psp,layout_section1_psp);
            }
        });
        btn_section2_psp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section2_psp,layout_section2_psp);
            }
        });
        btn_section3_psp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section3_psp,layout_section3_psp);
            }
        });
        btn_section4_psp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section4_psp,layout_section4_psp);
            }
        });
        btn_section5_psp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section5_psp,layout_section5_psp);
            }
        });
        btn_section6_psp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section6_psp,layout_section6_psp);
            }
        });
        btn_section7_psp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section7_psp,layout_section7_psp);
            }
        });





        return view;
    }
}
