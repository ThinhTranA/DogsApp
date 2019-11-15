package com.visualsharp.dogsapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.visualsharp.dogsapp.R;
import com.visualsharp.dogsapp.databinding.ItemDogBinding;
import com.visualsharp.dogsapp.model.DogBreed;
import com.visualsharp.dogsapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.DogViewHolder> {

    private ArrayList<DogBreed> dogsList;
    public  DogsListAdapter(ArrayList<DogBreed> dogList){
        this.dogsList = dogList;
    }

    public void updateDogsList(List<DogBreed> newDogsList){
        dogsList.clear();
        dogsList.addAll(newDogsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        ItemDogBinding view = DataBindingUtil.inflate(inflater, R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        holder.itemView.setDog(dogsList.get(position));

//        ImageView image = holder.itemView.findViewById(R.id.imageView);
//        TextView name = holder.itemView.findViewById(R.id.name);
//        TextView lifespan = holder.itemView.findViewById(R.id.lifespan);
//        LinearLayout layout = holder.itemView.findViewById(R.id.dogLayout);
//
//        name.setText(dogsList.get(position).dogBreed);
//        lifespan.setText(dogsList.get(position).lifeSpan);
//        Util.loadImage(image, dogsList.get(position).imageUrl, Util.getProgressDrawable(image.getContext()));
//        layout.setOnClickListener(v -> {
//            ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
//            action.setDogUuid(dogsList.get(position).uuui);
//            Navigation.findNavController(layout).navigate(action);
//        });
    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    class DogViewHolder extends RecyclerView.ViewHolder{

        public ItemDogBinding itemView;

        public DogViewHolder(@NonNull ItemDogBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
