package Generics_Collections;

import java.util.*;

public class ArtistCompare  implements Comparator<SongV2>
{

    public int compare(SongV2 one, SongV2 two) {
        return one.getArtist().compareTo(two.getArtist());
    }

}
