package com.example.hackerstoolbox.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hackerstoolbox.databinding.WebshellCyberchefBinding;
import com.example.hackerstoolbox.databinding.FragmentHomeBinding;
import com.example.hackerstoolbox.ui.Cyberchef.CyberchefViewModel;

public class HomeFragment extends Fragment {

    private WebshellCyberchefBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        CyberchefViewModel galleryViewModel =
//                new ViewModelProvider(this).get(CyberchefViewModel.class);

        binding = WebshellCyberchefBinding.inflate(inflater, container, false);                       //FragmentGalleryBinding.inflate(inflater, container, false);
        WebView client = binding.webView;
        WebSettings webSettings = client.getSettings();
        String mobileUserAgent = "Mozilla/5.0 (Linux; Android 10; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36";
        webSettings.setUserAgentString(mobileUserAgent);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        client.getSettings().setJavaScriptEnabled(true);
        client.loadUrl("https://github.com/TheMujdii3");
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}