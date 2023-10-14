package com.example.musicforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WhySayActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView countText, countTextName;
    private  MediaPlayer mediaPlayer;
    int[] listImage = new int[SoundAnimalsList.soundAnimalList.length];
    int[]  sound = new int[SoundAnimalsList.soundAnimalList.length];
    String[] soundAnimal = new String[SoundAnimalsList.soundAnimalList.length];
    Button btnFirstActivity,btnSecondActivity;
    ImageButton btnPlus, btnMinus,btnPlay;
    private static int imageCount =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MusicForKids);
        setContentView(R.layout.activity_why_say);
        imageView = findViewById(R.id.Image);
        btnPlay = findViewById(R.id.btnSound);
        btnFirstActivity = findViewById(R.id.btnFisrtActivity);
        btnSecondActivity = findViewById(R.id.btnSecondActivity);
        btnPlus = (ImageButton) findViewById(R.id.btnPlus);
        btnMinus = (ImageButton) findViewById(R.id.btnMinus);
        countText = (TextView) findViewById(R.id.counText);
        countTextName = (TextView) findViewById(R.id.counTextName);


        for(int i=0; i<listImage.length; i++){
            listImage[i]=SoundAnimalsList.soundAnimalList[i].getImageAnimal();
            sound[i] = getResources().getIdentifier(soundAnimal[i]=SoundAnimalsList.soundAnimalList[i].getSoundAnimal(),"raw",getPackageName());
        }
        countTextName.setText(SoundAnimalsList.soundAnimalList[imageCount].getNameAnimal());
        countText.setText((imageCount+1)+"/"+SoundAnimalsList.soundAnimalList.length);
        imageView.setImageResource(listImage[0]);
        btnFirstActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhySayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WhySayActivity.this,"O_O",Toast.LENGTH_SHORT).show();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCount++;
                if(imageCount<=listImage.length-1) {
                    imageView.setImageResource(listImage[imageCount]);
                    countTextName.setText(SoundAnimalsList.soundAnimalList[imageCount].getNameAnimal());
                    countText.setText((imageCount+1)+"/"+SoundAnimalsList.soundAnimalList.length);
                }
                if(imageCount==listImage.length){
                    imageCount=0;
                    imageView.setImageResource(listImage[imageCount]);
                    countTextName.setText(SoundAnimalsList.soundAnimalList[imageCount].getNameAnimal());
                    countText.setText((imageCount+1)+"/"+SoundAnimalsList.soundAnimalList.length);
                }

            }


        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test ", "befor "+imageCount+"");
                imageCount--;
                Log.d("test ", "after "+imageCount+"");
                if(imageCount>=0) {
                    imageView.setImageResource(listImage[imageCount]);
                    countText.setText(SoundAnimalsList.soundAnimalList[imageCount].getNameAnimal()+" \n "+(imageCount+1)+"/"+SoundAnimalsList.soundAnimalList.length);
                }
                if(imageCount==-1) {
                    imageCount = listImage.length-1;
                    imageView.setImageResource(listImage[imageCount]);
                    countText.setText(SoundAnimalsList.soundAnimalList[imageCount].getNameAnimal()+" \n "+(imageCount+1)+"/"+SoundAnimalsList.soundAnimalList.length);
                }
                Log.d("test ", "end method " +imageCount);
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaClass(sound[imageCount]);
            }
        });
    }

    private void getToIntFromString(){

    }

    private void MediaClass(int test) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer =MediaPlayer.create(this, test);
        mediaPlayer.start();
    }

    private void MediaClassStop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}