package com.song2.wave.AudioTest;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.song2.wave.R;
import com.song2.wave.UI.MainPlayer.MainPlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AudioAdapter extends CursorRecyclerViewAdapter<RecyclerView.ViewHolder> {

    Context context;

    private String selectedTitle;
    private Uri selectedImageUri;
    private String selectedArtist;

    public AudioAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        AudioItem audioItem = AudioItem.bindCursor(cursor);
        ((AudioViewHolder) viewHolder).setAudioItem(audioItem, cursor.getPosition());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_audio, parent, false);
        return new AudioViewHolder(v);
    }

    public static class AudioItem {
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
            Log.v("바인드", "바인드 id = " + MediaStore.Audio.AudioColumns._ID);
            Log.v("바인드", "바인드 컬럼 ㅍid = " + cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID));
            Log.v("바인드", "바인드 전체 id = " + cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID)));
            audioItem.mAlbumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID));
            audioItem.mTitle = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));
            Log.v("바인드", "바인드 title = " + MediaStore.Audio.AudioColumns.TITLE);
            Log.v("바인드", "바인드 컬럼 title = " + cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));
            Log.v("바인드", "바인드 전체 title = " + cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE)));
            Log.v("바인드", "바인드 전체 title2 = " + cursor.getString(1));
            audioItem.mArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
            Log.v("바인드", "바인드 Artist = " + MediaStore.Audio.AudioColumns.ARTIST);
            Log.v("바인드", "바인드 컬럼 Artist = " + cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
            Log.v("바인드", "바인드 전체 Artist = " + cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST)));
            audioItem.mAlbum = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM));
            audioItem.mDuration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION));
            audioItem.mDataPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));
            return audioItem;
        }
    }

    public ArrayList<Long> getAudioIds() {
        int count = getItemCount();
        ArrayList<Long> audioIds = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            audioIds.add(getItemId(i));
        }
        return audioIds;
    }

    private class AudioViewHolder extends RecyclerView.ViewHolder {
        private final Uri artworkUri = Uri.parse("content://media/external/audio/albumart");
        private ImageView mImgAlbumArt;
        private TextView mTxtTitle;
        private TextView mTxtSubTitle;
        private TextView mTxtDuration;
        private AudioItem mItem;
        private int mPosition;

        private AudioViewHolder(View view) {
            super(view);
            mImgAlbumArt = (ImageView) view.findViewById(R.id.img_albumart);
            mTxtTitle = (TextView) view.findViewById(R.id.txt_title);
            mTxtSubTitle = (TextView) view.findViewById(R.id.txt_sub_title);
            mTxtDuration = (TextView) view.findViewById(R.id.txt_duration);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    AudioApplication.getInstance().getServiceInterface().setPlayList(getAudioIds()); // 재생목록등록
//                    Log.v("asdf","텟값 = " + getAudioIds());
//                    AudioApplication.getInstance().getServiceInterface().play(mPosition); // 선택한 오디오재생
                    AudioApplication.getInstance().getServiceInterface().play(); // 선택한 오디오재생
                    Log.v("asdf","텟값2 = " + mPosition);
                    Intent intent = new Intent(context, MainPlayerActivity.class);
//                    intent.putExtra("mPosition", mPosition);
                    intent.putExtra("songUrl", "sadf");
                    context.startActivity(intent);
                }
            });
        }

        public void setAudioItem(AudioItem item, int position) {
            mItem = item;
            mPosition = position;
            mTxtTitle.setText(item.mTitle);
            mTxtSubTitle.setText(item.mArtist + "(" + item.mAlbum + ")");
            mTxtDuration.setText(DateFormat.format("mm:ss", item.mDuration));
            Uri albumArtUri = ContentUris.withAppendedId(artworkUri, item.mAlbumId);
            Picasso.with(itemView.getContext()).load(albumArtUri).error(R.drawable.kakao_default_profile_image).into(mImgAlbumArt);
        }
    }
}
