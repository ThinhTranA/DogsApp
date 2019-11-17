package com.visualsharp.dogsapp.view;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.palette.graphics.Palette;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.visualsharp.dogsapp.R;
import com.visualsharp.dogsapp.databinding.FragmentDetailBinding;
import com.visualsharp.dogsapp.model.DogBreed;
import com.visualsharp.dogsapp.model.DogPalette;
import com.visualsharp.dogsapp.util.Util;
import com.visualsharp.dogsapp.viewmodel.DetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    private int dogUuid;
    private DetailViewModel viewModel;
    private FragmentDetailBinding binding;

    public DetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        this.binding = binding;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null){
            dogUuid = DetailFragmentArgs.fromBundle(getArguments()).getDogUuid();
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.fetch(dogUuid);

        observeViewModel();
    }

    private void observeViewModel(){
        viewModel.dogLiveData.observe(this, dogBreed -> {
            if(dogBreed != null && dogBreed instanceof DogBreed &&getContext() != null){
               binding.setDog(dogBreed);
               if(dogBreed.imageUrl != null){
                   setupBackgroundColor(dogBreed.imageUrl);
               }
            }
        });
    }

    private void setupBackgroundColor(String url){
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Palette.from(resource)
                                .generate(palette -> {
                                    int intColor = palette.getLightMutedSwatch().getRgb();
                                    DogPalette myPalette = new DogPalette(intColor);
                                    binding.setPalette(myPalette);
                                });
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

}
