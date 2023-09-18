package com.example.musicforkids;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WhoSayFragment extends Fragment {
    Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView whyS = (RecyclerView) inflater.inflate(R.layout.fragment_what_say,container,false);
        String[] nameAnimals = new String[SoundAnimalsList.soundAnimalList.length];
        int[] imageAnimals = new int[SoundAnimalsList.soundAnimalList.length];
        for(int i=0;i<nameAnimals.length; i++){
            nameAnimals[i]=SoundAnimalsList.soundAnimalList[i].getNameAnimal();
            imageAnimals[i]=SoundAnimalsList.soundAnimalList[i].getImageAnimal();
        }
        AdapterWhySay adapterWhySay = new AdapterWhySay(nameAnimals,imageAnimals);
        whyS.setAdapter(adapterWhySay);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL,false);
        whyS.setLayoutManager(layoutManager);
        adapterWhySay.setListner(new AdapterWhySay.Listner() {
         @Override
            public void onClick(int position) {
                try{
                    ((AnimalSound) activity).animalSound(position);

           }catch
                 (ClassCastException ignored){}
            }
        });
        return whyS;

    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Activity){
            activity=(Activity) context;
        }
    }
}