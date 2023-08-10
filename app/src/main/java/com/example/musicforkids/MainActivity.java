package com.example.musicforkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Postman{

    public static int EXTRA_SONG_ID = 0;
    MediaPlayer mediaPlayer;
    private MusicList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new MusicFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.musciFragment, fragment);
        ft.commit();
        mediaPlayer=MediaPlayer.create(this,R.raw.blackplush);
        ImageButton imageButton = findViewById(R.id.ButtonPlay);
    }

    @Override
    public void framentMail(int numberOfClick){
        TextView textView = findViewById(R.id.textSongId);
        String message = "Была выбрана песня под номером "+numberOfClick;
        textView.setText(message);

    }



    }