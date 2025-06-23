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

import io.noties.markwon.Markwon;

public class WriteupDetailFragment extends Fragment {

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
            Log.d("WRITEUP_DETAIL", "Received content: " + content);

            if (content != null && !content.isEmpty()) {
                Markwon markwon = Markwon.create(requireContext());
                markwon.setMarkdown(markdownText, content);
                Toast.makeText(requireContext(), "Content displayed", Toast.LENGTH_SHORT).show();
            } else {
                markdownText.setText("Writeup content is empty.");
            }
        } else {
            markdownText.setText("No writeup content found.");
        }

        return view;
    }
}
