package com.example.diploma.ui.resulthistory

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diploma.AppDatabase
import com.example.diploma.AppEntity

class ResultHistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }

    val text: LiveData<String> = _text
}