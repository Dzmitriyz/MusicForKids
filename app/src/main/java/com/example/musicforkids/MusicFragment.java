package com.example.musicforkids;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MusicFragment extends Fragment {
    int[] song;
    Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView musicRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_music, container,false);
        String[] nameMusic = new String[MusicList.list_Music.length];
        int[] imageSong = new int[MusicList.list_Music.length];
        int[] songID = new int[MusicList.list_Music.length];
        for(int i=0; i<nameMusic.length; i++){
            nameMusic[i] = MusicList.list_Music[i].getNameSong();
            imageSong[i]= MusicList.list_Music[i].getImageResourceId();
            songID[i] =MusicList.list_Music[i].getSongId();
        }
        AdapterMusic adapter = new AdapterMusic(nameMusic,imageSong);
        musicRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        musicRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new AdapterMusic.Listener() {
            @Override
            public void onClick(int position) {
                try {
                    ((Postman) activity).framentMail(position);
                }catch (ClassCastException ignored){}
            }
        });
        return musicRecycler;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Activity){
            activity=(Activity) context;
        }
    }

    public void setSong(int[] song){
        this.song=song;
    }

    public int[] getSong(){
        return song;
    }
}