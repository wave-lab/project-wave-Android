package com.song2.wave.UI.Artist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.ArtistPlaylistSongData
import com.song2.wave.R
import com.song2.wave.UI.Artist.Adapter.ArtistPlaylistAdapter
import kotlinx.android.synthetic.main.fragment_artist_library_playlist.*


class ArtistPlaylistFragment : Fragment() {

    lateinit var artistPlaylist : ArrayList<ArtistPlaylistSongData>
    lateinit var requestManager : RequestManager

    lateinit var artistPlaylistAdapter : ArtistPlaylistAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_artist_library_playlist, container, false)
        requestManager = Glide.with(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        artistPlaylist = arrayListOf()

        var songCoverImgArr: ArrayList<String>  = arrayListOf("https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E",
                "https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E",
                "https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E",
                "https://images.otwojob.com/product/E/a/n/EandNVOq2rIbOu0.png/o2j/resize/900%3E")
        artistPlaylist.add(ArtistPlaylistSongData(songCoverImgArr, "sssungeun List", "웨이부"))
        artistPlaylist.add(ArtistPlaylistSongData(songCoverImgArr, "sun List", "웨이부"))
        artistPlaylist.add(ArtistPlaylistSongData(songCoverImgArr, "seungheui List", "웨이부"))
        artistPlaylist.add(ArtistPlaylistSongData(songCoverImgArr, "jjemin List", "웨이부"))
        artistPlaylist.add(ArtistPlaylistSongData(songCoverImgArr, "yyejin List", "웨이부"))
        artistPlaylist.add(ArtistPlaylistSongData(songCoverImgArr, "eunhye List", "웨이부"))
        artistPlaylist.add(ArtistPlaylistSongData(songCoverImgArr, "jihun List", "웨이부"))
        artistPlaylist.add(ArtistPlaylistSongData(songCoverImgArr, "sinil List", "웨이부"))
        artistPlaylist.add(ArtistPlaylistSongData(songCoverImgArr, "seongsoo List", "웨이부"))

        artistPlaylistAdapter = ArtistPlaylistAdapter(context!!, artistPlaylist)
        rv_artist_library_playlist.adapter = artistPlaylistAdapter
        rv_artist_library_playlist.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)


    }
}