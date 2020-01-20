package com.example.navigation.ui.chercher;

        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

public class ChercherViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChercherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}