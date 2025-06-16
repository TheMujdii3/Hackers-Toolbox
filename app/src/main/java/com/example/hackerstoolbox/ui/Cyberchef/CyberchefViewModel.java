package com.example.hackerstoolbox.ui.Cyberchef;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CyberchefViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CyberchefViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}