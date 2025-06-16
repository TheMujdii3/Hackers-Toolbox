package com.example.hackerstoolbox.ui.Aperisolve;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AperisolveViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AperisolveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}