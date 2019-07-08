package com.song2.wave.AudioTest;

import android.database.Cursor;
import android.provider.MediaStore;

public class AudioItem {
    public long mId; // 오디오 고유 ID
    public long mAlbumId; // 오디오 앨범아트 ID
    public String mTitle; // 타이틀 정보
    public String mArtist; // 아티스트 정보
    public String mAlbum; // 앨범 정보
    public long mDuration; // 재생시간
    public String mDataPath; // 실제 데이터위치

    public static AudioItem bindCursor(Cursor cursor) {
        AudioItem audioItem = new AudioItem();
        audioItem.mId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID));
        audioItem.mAlbumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID));
        audioItem.mTitle = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));
        audioItem.mArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
        audioItem.mAlbum = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM));
        audioItem.mDuration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION));
        audioItem.mDataPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));
        return audioItem;
    }
}