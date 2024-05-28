package com.example.jailbreak_center;

import static android.view.View.GONE;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class N3DS_Guide_Screen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_n3ds_guide_screen, container, false);

        VideoView videoView = view.findViewById(R.id.videoView);
        videoView.setVisibility(GONE);
        String videoPath = "android.resource://"+requireActivity().getPackageName()+"/"+R.raw.n3ds_guide;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        Button reproducir = view.findViewById(R.id.Reproducir);
        Button btn_section0 = view.findViewById(R.id.btn_section0);
        Button btn_section1 = view.findViewById(R.id.btn_section1);
        Button btn_section2 = view.findViewById(R.id.btn_section2);

        LinearLayout layout_section0 = view.findViewById(R.id.layout_section0);
        layout_section0.setVisibility(GONE);
        LinearLayout layout_section1 = view.findViewById(R.id.layout_section1);
        layout_section1.setVisibility(GONE);
        LinearLayout layout_section2 = view.findViewById(R.id.layout_section2);
        layout_section2.setVisibility(GONE);

        btn_section0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section0,layout_section0);
            }
        });

        btn_section1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section1,layout_section1);
            }
        });

        btn_section2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools_InsertInfoGuide.layouts_visibility(requireContext(),btn_section2,layout_section2);
            }
        });

        reproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVisibility(View.VISIBLE);
                videoView.start();
            }
        });

        return view;
    }
}