package com.example.musicforkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements Postman {

    public static int EXTRA_SONG_ID = 0;
    public static MediaPlayer mediaPlayer;
    private MusicList list = new MusicList();
    ImageButton imageButtonPlay, imageButtonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new MusicFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.musciFragment, fragment);
        ft.commit();
        imageButtonPlay = findViewById(R.id.ButtonPlay);
        imageButtonStop = findViewById(R.id.ButtonStop);

        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.timon_i_pumba);
                    mediaPlayer.start();
                }
            }
        });
        imageButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });
    }


    @Override
    public void framentMail(int numberOfClick) {
        list.getArrayList();
        String[] rawString = new String[MusicList.list_Music.length];
        int[] song1 = new int[MusicList.list_Music.length];
        for (int i = 0; i < song1.length; i++) {
            rawString[i] = MusicList.list_Music[i].getRawString();
        }
        int test = getResources().getIdentifier(rawString[numberOfClick], "raw", getPackageName());
        MediaClass(test);

    }

    public void MediaClass(int test) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer =MediaPlayer.create(this, test);
        mediaPlayer.start();
    }
}