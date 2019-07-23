package com.song2.wave.Util.DB;

import android.provider.BaseColumns;

public class DataBases implements BaseColumns{

    public static final class CreateDB{
        public static final String SONGID = "songId";
        public static final String SONGURL = "songUrl";
        public static final String ORIGINARTIST = "originArtist";
        public static final String COVERARTIST = "coverArtist";
        public static final String SONGTITLE = "songTitle";
        public static final String _TABLENAME0 = "songtable";
        public static final String _CREATE0 = "crete table if not exists "
                + _TABLENAME0 + "("
                + _ID + " integer primary key autoincrement, "
                + SONGID + "text not null, "
                + SONGURL + "text not null, "
                + ORIGINARTIST + "originArtist"
                + COVERARTIST + "coverArtist"
                + SONGTITLE + "text not null);";
    }


/*    public static final class CreateSearchDB{
        public static final String KEYWORD = "keyword";
        public static final String _TABLENAME0 = "searchtable";
        public static final String _CREATE0 = "create table if not exists "
                + _TABLENAME0 +"("
                + _ID +" integer primary key autoincrement, "
                + KEYWORD +"text not null );";
    }*/

}
