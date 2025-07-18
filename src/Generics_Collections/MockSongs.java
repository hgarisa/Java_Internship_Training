package Generics_Collections;

import java.util.ArrayList;
import java.util.List;

public class MockSongs
{


    public static List<String> getSongStrings() {
        List<String> songs = new ArrayList<>();
        songs.add("somesarsault");
        songs.add("cassidyy");
        songs.add("djti10");
        songs.add("havana");
        songs.add("Cassidyy");
        songs.add("50 Ways");
        return songs;
    }


    public static List<SongV2> getSongsV2() {
        List<SongV2> mysongs = new ArrayList<>();
        mysongs.add(new SongV2("somersault", "zero 7", 147));
        mysongs.add(new SongV2("cassidy", "grateful dead", 158));
        mysongs.add(new SongV2("$10", "hitchhiker", 140));
        mysongs.add(new SongV2("havana", "cabello", 105));
        mysongs.add(new SongV2("Cassidy", "grateful dead", 158));
        mysongs.add(new SongV2("50 ways", "simon", 102));
        return mysongs;
    }


}
