package com.visualsharp.dogsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.visualsharp.dogsapp.model.DogBreed;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<DogBreed>> dogs = new MutableLiveData<List<DogBreed>>();
    public MutableLiveData<Boolean> dogLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh(){
        DogBreed dog1 = new DogBreed("1", "corgi", "15 years", "", "", "", "", 0);
        DogBreed dog2 = new DogBreed("2", "rotwailer", "10 years", "", "", "", "", 0);
        DogBreed dog3 = new DogBreed("3", "labrador", "13 years", "", "", "", "", 0);

        ArrayList<DogBreed> dogsList = new ArrayList<>();
        dogsList.add(dog1);
        dogsList.add(dog2);
        dogsList.add(dog3);

        dogs.setValue(dogsList);
        dogLoadError.setValue(false);
        loading.setValue(false);

    }

}
