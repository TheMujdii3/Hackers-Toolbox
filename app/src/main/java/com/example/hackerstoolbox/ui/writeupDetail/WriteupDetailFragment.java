package com.example.hackerstoolbox.ui.writeupDetail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hackerstoolbox.R;
import com.example.hackerstoolbox.Writeup;

import io.noties.markwon.Markwon;

public class WriteupDetailFragment extends Fragment {

    private static final String ARG_WRITEUP = "writeup";


    //final Markwon panamea = Markwon.create(requireContext());
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
        Toast.makeText(requireContext(), "da crash dupa constructoru panamea", Toast.LENGTH_SHORT).show();
        Bundle args = getArguments();
        if (args != null && args.containsKey("content")) {
            Markwon markwon = Markwon.create(requireContext());
            String content = args.getString("content");
            Log.d("DEBUG DETAIL CONTENT WRITEUP", "Writeup content: " + content);
            if (content != null && !content.isEmpty()) {
                //panamea.setMarkdown(markdownText,content);
                markwon.setMarkdown(markdownText, "#uwu"); // ✅ Proper use of Markwon
                Toast.makeText(requireContext(), "contentu a fost gasit si setat", Toast.LENGTH_SHORT).show();
            }
            else {
                markdownText.setText("⚠️ Error: Content is empty.");
                Toast.makeText(requireContext(), "contentu e empty", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            markdownText.setText("⚠️ Error: No content found.");
            Toast.makeText(requireContext(), "contentu nu a fost gasit", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}