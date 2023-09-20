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
import android.widget.Toast;

public class WhySayActivity extends AppCompatActivity {
    private ImageView imageView;
    private  MediaPlayer mediaPlayer;
    int[] listImage = new int[SoundAnimalsList.soundAnimalList.length];
    int[]  sound = new int[SoundAnimalsList.soundAnimalList.length];
    String[] soundAnimal = new String[SoundAnimalsList.soundAnimalList.length];
    Button btnPlus, btnMinus,btnFirstActivity,btnSecondActivity;
    ImageButton btnPlay;
    private static int imageCount =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_why_say);
        imageView = findViewById(R.id.Image);
        btnPlay = findViewById(R.id.btnSound);
        btnFirstActivity = findViewById(R.id.btnFisrtActivity);
        btnSecondActivity = findViewById(R.id.btnSecondActivity);
        btnPlus = (Button) findViewById(R.id.btbPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);

        for(int i=0; i<listImage.length; i++){
            listImage[i]=SoundAnimalsList.soundAnimalList[i].getImageAnimal();
            sound[i] = getResources().getIdentifier(soundAnimal[i]=SoundAnimalsList.soundAnimalList[i].getSoundAnimal(),"raw",getPackageName());
        }
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
                Toast.makeText(WhySayActivity.this,"okokok",Toast.LENGTH_SHORT).show();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("minus", imageCount+"");
                if(imageCount<listImage.length-1)
                    imageCount++;
                Log.d("minus", imageCount+"");
                imageView.setImageResource(listImage[imageCount]);

            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("minus", imageCount+"");
                if(imageCount>0) {
                    Log.d("minus", imageCount+"");
                    imageCount--;
                    imageView.setImageResource(listImage[imageCount]);
                }
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