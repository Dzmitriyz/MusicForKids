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
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements Postman, AnimalSound, SeekBar.OnSeekBarChangeListener {

    public static MediaPlayer mediaPlayer;
    private int globalInt=R.raw.blue_track;
    private LinearLayout liner;
    private TextView txtSound,txtStartSong,txtEndSong;
    boolean ButtonPlay = false;
    private Button btnF1, btnF2;
    private SeekBar seekBar;
    private MusicList list = new MusicList();
    private ImageButton imageButtonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MusicForKids);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        CustomViewPager pager = (CustomViewPager) findViewById(R.id.musciFragment);
        pager.setAdapter(pagerAdapter);
        liner = (LinearLayout)findViewById(R.id.main_liner);

        Fragment fragment = new MusicFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.musciFragment, fragment);
        ft.commit();
        init();


        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ddd", "onClick btnPlay");
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

    private void init(){
        txtSound = findViewById(R.id.textSongId);
        imageButtonPlay = findViewById(R.id.ButtonPlay);
        btnF1 = findViewById(R.id.btnFisrtActivity);
        btnF2 = findViewById(R.id.btnSecondActivity);
        txtStartSong = findViewById(R.id.txtStartSong);
        txtEndSong = findViewById(R.id.txtEndSong);
        seekBar = findViewById(R.id.seekBar);
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
        String duration = millisecondToString(mediaPlayer.getDuration());
        Log.d("MyLog ",duration);
        txtStartSong.setText(duration);
        mediaPlayer.start();
        getSeekBarStatus();
    }

    private void getSeekBarStatus(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int currentPosisiton=0;
                int total =mediaPlayer.getDuration();
                seekBar.setMax(total);
                while (mediaPlayer != null && currentPosisiton < total){
                    try {
                        Thread.sleep(1000);
                        currentPosisiton = mediaPlayer.getCurrentPosition();
                    }catch (InterruptedException e){
                        return;
                    }
                    seekBar.setProgress(currentPosisiton);
                }
            }
        }).start();
    }



    public void MediaClassStop() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            mediaPlayer.seekTo(progress);
            seekBar.setProgress(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

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

    public String millisecondToString(int time){
        String elapsedTime = "";
        int minutes = time/1000/60;
        int seconds = time/1000 % 60;
        elapsedTime = minutes+":";
        if(seconds<10){
            elapsedTime+="0";
        }else{
            elapsedTime+=seconds;
        }
        return elapsedTime;
    }

    @Override
    protected void onStop( ){
        super.onStop();
        MediaClassStop();
        imageButtonPlay.setImageResource(R.drawable.play);
    }


}