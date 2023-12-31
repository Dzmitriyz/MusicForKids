package com.example.musicforkids;

public class MusicList {
    private String nameSong;
    private int picSong;
    private int songId;
    private String rawString;
    public MusicList(){

    }

    public static final MusicList[] list_Music ={
            new MusicList("Вуншпунш", R.drawable.wunschpunsch2,R.raw.wunschpunsch,"wunschpunsch"),
            new MusicList("Фиксики",R.drawable.fiksiki,R.raw.fiksiki,"fiksiki"),
            new MusicList("Синий трактор", R.drawable.blue_track,R.raw.blue_track,"blue_track"),
            new MusicList("Чебурашка", R.drawable.chebyrashka, R.raw.cheburashka,"cheburashka"),
            new MusicList("TMNT", R.drawable.tmnt, R.raw.tmnt,"tmnt"),
            new MusicList("Черный плащ", R.drawable.dwduck,R.raw.blackplush,"blackplush"),
            new MusicList("Алладин", R.drawable.alladin, R.raw.alladin,"alladin"),
            new MusicList("Король лев", R.drawable.king_lion,R.raw.king_lion,"king_lion"),
            new MusicList("Акуленок", R.drawable.shark_kid,R.raw.shark,"shark"),
            new MusicList("Тимон и пумба", R.drawable.timon_and_pumba, R.raw.timon_i_pumba,"timon_i_pumba")

    };
    MusicList(String Song, int picSong, int songId, String rawString){
        this.nameSong = Song;
        this.picSong=picSong;
        this.songId=songId;
        this.rawString=rawString;

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

    public MusicList[] getArrayList(){
        return list_Music;
    }

    public String getRawString(){
        return rawString;
    }



}
