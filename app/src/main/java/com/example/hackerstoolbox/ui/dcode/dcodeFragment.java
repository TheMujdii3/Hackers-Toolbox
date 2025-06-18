package com.example.hackerstoolbox.ui.dcode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.hackerstoolbox.databinding.WebshellCyberchefBinding;
import com.example.hackerstoolbox.databinding.FragmentGalleryBinding;
import com.example.hackerstoolbox.ui.Cyberchef.CyberchefViewModel;

public class dcodeFragment extends Fragment {
    private WebshellCyberchefBinding binding;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dcodeViewModel webshellView =
                new ViewModelProvider(this).get(dcodeViewModel.class);

        binding = WebshellCyberchefBinding.inflate(inflater, container, false);                       //FragmentGalleryBinding.inflate(inflater, container, false);
        WebView client = binding.webView;
        WebSettings webSettings = client.getSettings();
        String mobileUserAgent = "Mozilla/5.0 (Linux; Android 10; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36";
        webSettings.setUserAgentString(mobileUserAgent);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        client.setWebViewClient(new WebViewClient());

        // Set a WebChromeClient to handle "new window" requests
        client.setWebChromeClient(new WebChromeClient() {
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture) {
                // This is where new windows are requested.
                // We'll create a new WebView to handle the new window.
                // For simplicity, this example reuses the main WebView to load the new URL.
                // A more complex implementation might create a new WebView dynamically
                // or open a new Activity/Fragment.

                WebView.HitTestResult result = view.getHitTestResult();
                String url = result.getExtra();

                if (url != null) {
                    // Load the new URL in the current WebView
                    view.loadUrl(url);
                    // Tell the system we've handled the new window request
                    return true;
                }

                // If no URL, let the system handle it (though this is less common for onCreateWindow)
                return false;
            }
        });

        client.loadUrl("https://www.dcode.fr/en");

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
