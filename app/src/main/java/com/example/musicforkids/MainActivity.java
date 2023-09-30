package com.example.musicforkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements Postman, AnimalSound{

    public static int EXTRA_SONG_ID = 0;
    public static MediaPlayer mediaPlayer;
    private int globalInt=R.raw.blue_track;
    private LinearLayout liner;
    private TextView txtSound;
    boolean ButtonPlay = false;
    private Button btnF1, btnF2;
    private MusicList list = new MusicList();
    ImageButton imageButtonPlay, imageButtonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        CustomViewPager pager = (CustomViewPager) findViewById(R.id.musciFragment);
        pager.setAdapter(pagerAdapter);
        liner = (LinearLayout)findViewById(R.id.main_liner);

        Fragment fragment = new MusicFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.musciFragment, fragment);
        ft.commit();
        txtSound = findViewById(R.id.textSongId);
        imageButtonPlay = findViewById(R.id.ButtonPlay);
        btnF1 = findViewById(R.id.btnFisrtActivity);
        btnF2 = findViewById(R.id.btnSecondActivity);

        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer == null){
                    MediaClass(globalInt);
                    imageButtonPlay.setImageResource(R.drawable.pause);
                }else if (mediaPlayer.isPlaying()) {
                    MediaClassStop();
                    imageButtonPlay.setImageResource(R.drawable.play);
                }else if (mediaPlayer != null){
                    mediaPlayer.start();
                    imageButtonPlay.setImageResource(R.drawable.pause);
                }
            }
        });
        btnF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaClassStop();
                Intent intent = new Intent(MainActivity.this, WhySayActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void framentMail(int numberOfClick) {
        globalInt = numberOfClick;
        String[] rawString = new String[MusicList.list_Music.length];
        for (int i = 0; i < rawString.length; i++) {
            rawString[i] = MusicList.list_Music[i].getRawString();
        }
        int test = getResources().getIdentifier(rawString[numberOfClick], "raw", getPackageName());
        txtSound.setText(MusicList.list_Music[numberOfClick].getNameSong());
        imageButtonPlay.setImageResource(R.drawable.pause);
        ButtonPlay=true;
        MediaClass(test);

    }

    @Override
    public void animalSound(int numberSound) {

        String[] animalSound = new String[SoundAnimalsList.soundAnimalList.length];
        for(int i=0; i<animalSound.length; i++){
            animalSound[i]=SoundAnimalsList.soundAnimalList[i].getSoundAnimal();
        }
        int animal = getResources().getIdentifier(animalSound[numberSound],"raw",getPackageName());
        MediaClass(animal);
    }

    public void MediaClass(int test) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        mediaPlayer =MediaPlayer.create(this, test);
        mediaPlayer.start();
    }

    public void MediaClassStop() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
    private class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter( FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    MediaClassStop();
                    return new MusicFragment();
                case 1:
                    MediaClassStop();
                    return new WhoSayFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return getResources().getText(R.string.music);
                case 1:
                    return getResources().getText(R.string.how_say);
                case 2:
                    return "New Activity";
            }
            return null;
        }
    }
}