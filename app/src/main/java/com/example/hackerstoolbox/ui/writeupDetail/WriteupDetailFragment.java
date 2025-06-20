package com.example.hackerstoolbox.ui.writeupDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hackerstoolbox.R;
import com.example.hackerstoolbox.Writeup;

import io.noties.markwon.Markwon;

public class WriteupDetailFragment extends Fragment {

    private static final String ARG_WRITEUP = "writeup";

    public static WriteupDetailFragment newInstance(Writeup writeup) {
        WriteupDetailFragment fragment = new WriteupDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_WRITEUP, writeup); // ✅ Fixed: removed invalid line
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_writeup_detail, container, false);
        TextView markdownText = view.findViewById(R.id.markdownText);

        Bundle args = getArguments();
        if (args != null && args.containsKey("content")) {
            String content = args.getString("content");
            if (content != null && !content.isEmpty()) {
                Markwon markwon = Markwon.create(requireContext());
                markwon.setMarkdown(markdownText, content); // ✅ Proper use of Markwon
            } else {
                markdownText.setText("⚠️ Error: Content is empty.");
            }
        } else {
            markdownText.setText("⚠️ Error: No content found.");
        }

        return view;
    }
}
