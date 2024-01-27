package it.uniba.dib.piu.softwarechasers.fitnessapp.ui.schede;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SchedeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SchedeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}