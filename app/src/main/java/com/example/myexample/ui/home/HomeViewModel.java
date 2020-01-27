package com.example.myexample.ui.home;

import android.content.Intent;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
String fileUrl="";
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

          Intent downloadIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.stackoverflow.com"));


    public LiveData<String> getText() {
        return mText;
    }
}