package Generics_Collections;

import java.util.Collections;
import java.util.List;

public class Jukebox1
{


    public static void main(String[] args) {

     /*  Jukebox1 myjuke =  new Jukebox1();
       myjuke.go();*/


        new Jukebox1().go();
    }
    public void go() {
       // List<String> songList = MockSongs.getSongStrings();
        //System.out.println(songList);


        List<SongV2> songList = MockSongs.getSongsV2();
//        System.out.println(songList);
//        Collections.sort(songList);
//        System.out.println(songList);

        TitleCompare titleCompare = new TitleCompare();
        songList.sort(titleCompare);
        System.out.println(songList);

        ArtistCompare artistCompare = new ArtistCompare();
        songList.sort(artistCompare);
        System.out.println(songList);

    }
}
