package com.example.hackerstoolbox.ui.Aperisolve;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient; // Keep this for general page navigation

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hackerstoolbox.databinding.WebshellCyberchefBinding;

public class AperisolveFragment extends Fragment {

    private WebshellCyberchefBinding binding;
    private WebView webView;


    private ValueCallback<Uri[]> filePathCallback;
    private ActivityResultLauncher<Intent> fileChooserLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        fileChooserLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (filePathCallback == null) {
                        return;
                    }
                    Uri[] results = null;
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String dataString = data.getDataString();
                            if (dataString != null) {
                                results = new Uri[]{Uri.parse(dataString)};
                            }
                        }
                    }
                    filePathCallback.onReceiveValue(results);
                    filePathCallback = null;
                });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AperisolveViewModel slideshowViewModel =
                new ViewModelProvider(this).get(AperisolveViewModel.class);


        binding = WebshellCyberchefBinding.inflate(inflater, container, false);
        webView = binding.webView;

        WebSettings webSettings = webView.getSettings();
        String mobileUserAgent = "Mozilla/5.0 (Linux; Android 10; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36";
        webSettings.setUserAgentString(mobileUserAgent);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        webSettings.setJavaScriptEnabled(true); // javascript on


        webSettings.setAllowFileAccess(true); // asta e ca sa foloseasca perimisunea
        webSettings.setAllowContentAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); // pop ups on
        webSettings.setSupportMultipleWindows(true); // ceva necesar god knows why

        //se face web client recursiv pe stack
        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if (AperisolveFragment.this.filePathCallback != null) {
                    AperisolveFragment.this.filePathCallback.onReceiveValue(null);
                }
                AperisolveFragment.this.filePathCallback = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try {
                    fileChooserLauncher.launch(intent);
                } catch (Exception e) {
                    AperisolveFragment.this.filePathCallback = null;
                    return false;
                }
                return true;
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, android.os.Message resultMsg) {

                WebView.HitTestResult result = view.getHitTestResult();
                String url = result.getExtra();
                if (url != null) {
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }
        });

        webView.loadUrl("https://www.aperisolve.com/");

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (webView != null) {

        }
        binding = null;
        filePathCallback = null;
    }
}