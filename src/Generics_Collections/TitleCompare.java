package Generics_Collections;


import java.util.*;
public class TitleCompare implements Comparator<SongV2>
{


    @Override
    public int compare(SongV2 o1, SongV2 o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
