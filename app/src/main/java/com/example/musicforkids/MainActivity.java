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
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements Postman, AnimalSound{

    public static int EXTRA_SONG_ID = 0;
    public static MediaPlayer mediaPlayer;
    private LinearLayout liner;
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
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

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
        String[] rawString = new String[MusicList.list_Music.length];
        for (int i = 0; i < rawString.length; i++) {
            rawString[i] = MusicList.list_Music[i].getRawString();
        }
        int test = getResources().getIdentifier(rawString[numberOfClick], "raw", getPackageName());
        MediaClass(test);

    }

    @Override
    public void animalSound(int numberSound) {

        String[] animalSound = new String[SoundAnimalsList.soundAnimalList.length];
        for(int i=0; i<animalSound.length; i++){
            animalSound[i]=SoundAnimalsList.soundAnimalList[i].getSoundAnimal();
        }
        int animal = getResources().getIdentifier(animalSound[numberSound],"raw",getPackageName());
        Log.d("animal", "111rere");
        MediaClass(animal);
    }

    public void MediaClass(int test) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer =MediaPlayer.create(this, test);
        mediaPlayer.start();
    }

    public void MediaClassStop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
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