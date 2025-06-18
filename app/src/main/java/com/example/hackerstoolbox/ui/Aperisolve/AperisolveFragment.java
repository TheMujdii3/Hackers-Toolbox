package com.example.hackerstoolbox.ui.Aperisolve;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hackerstoolbox.databinding.FragmentSlideshowBinding;
import com.example.hackerstoolbox.databinding.WebshellCyberchefBinding;

public class AperisolveFragment extends Fragment {

    private  WebshellCyberchefBinding binding;

    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AperisolveViewModel slideshowViewModel =
                new ViewModelProvider(this).get(AperisolveViewModel.class);

        binding = WebshellCyberchefBinding.inflate(inflater, container, false);                       //FragmentGalleryBinding.inflate(inflater, container, false);

        WebView client = binding.webView;

        WebSettings webSettings = client.getSettings();
        String mobileUserAgent = "Mozilla/5.0 (Linux; Android 10; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36";
        webSettings.setUserAgentString(mobileUserAgent);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.getBuiltInZoomControls();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        client.getSettings().setJavaScriptEnabled(true);
        client.loadUrl("https://www.aperisolve.com/");



        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}