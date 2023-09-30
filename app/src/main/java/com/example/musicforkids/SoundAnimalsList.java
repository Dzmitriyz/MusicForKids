package com.example.musicforkids;

public class SoundAnimalsList {
    private String nameAnimal;
    private int  imageAnimal;
    private String soundAnimal;

    public static final SoundAnimalsList[] soundAnimalList={
            new SoundAnimalsList("Кошка",R.drawable.cat,"cat"),
            new SoundAnimalsList("Собачка",R.drawable.dog,"dog"),
            new SoundAnimalsList("Корова",R.drawable.cow,"cow"),
            new SoundAnimalsList("Даша",R.drawable.daria, "daria")
    };

    SoundAnimalsList(String name, int image, String sound){
        this.nameAnimal=name;
        this.imageAnimal=image;
        this.soundAnimal=sound;
    }

    public String getNameAnimal() {
        return nameAnimal;
    }

    public int getImageAnimal() {
        return imageAnimal;
    }

    public String getSoundAnimal() {
        return soundAnimal;
    }
}
