package com.example.hackerstoolbox.ui.writeup_showpage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class writeup_showpageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public writeup_showpageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public writeup_showpageViewModel(MutableLiveData<String> mText) {
        this.mText = mText;
    }

    public LiveData<String> getText() {
        return mText;
    }
}
