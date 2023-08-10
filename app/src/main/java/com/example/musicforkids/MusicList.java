package com.example.musicforkids;

public class MusicList {
    private String nameSong;
    private int picSong;
    private int songId;

    public static final MusicList[] list_Music ={
            new MusicList("Синий трактор", R.drawable.blue_track,R.raw.blue_track),
            new MusicList("Чебурашка", R.drawable.chebyrashka, R.raw.cheburashka),
            new MusicList("Черный плащь", R.drawable.dwduck,R.raw.blackplush),
            new MusicList("Акуленнок", R.drawable.shark_kid,R.raw.shark),
            new MusicList("Тимон и пубма", R.drawable.timon_and_pumba, R.raw.timon_i_pumba)
    };
    MusicList(String Song, int picSong, int songId){
        this.nameSong = Song;
        this.picSong=picSong;
        this.songId=songId;
    }

    public String getNameSong(){
        return nameSong;
    }

    public int getImageResourceId(){
        return picSong;
    }

    public int getSongId() {
        return songId;

    }



}
