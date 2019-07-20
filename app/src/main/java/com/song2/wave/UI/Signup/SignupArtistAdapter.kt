package com.song2.wave.UI.Signup

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.song2.wave.Data.model.RealArtistData
import com.song2.wave.R
import com.song2.wave.UI.Main.Search.Adapter.CoverArtistSearchViewHolder

class SignupArtistAdapter (private var realArtistData : ArrayList<RealArtistData>, var requestManager: RequestManager) : RecyclerView.Adapter<SignupArtistViewHolder>(){

    val artistNumArr = BooleanArray(101)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignupArtistViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist_select, parent, false)
        return SignupArtistViewHolder(mainView)
    }

    override fun getItemCount(): Int = realArtistData.size

    override fun onBindViewHolder(holder: SignupArtistViewHolder, position: Int) {

        holder.realArtistImg.setOnClickListener {
            requestManager.load(realArtistData[position].realArtistImgUrl).into(holder.realArtistImg)
            if(artistNumArr[position+1]){
                Log.v("asdf","아티스트 선택 = "+(position+1) + "삭제")
                SignupSelectArtistActivity.signupSelectArtistActivity.selectedArtistArr.remove((position.toString()))
                artistNumArr[position+1] = false
            }
            else{
                artistNumArr[position+1] = true
                Log.v("asdf","아티스트 선택 = "+(position+1) + "추가")
                SignupSelectArtistActivity.signupSelectArtistActivity.selectedArtistArr.add((position.toString()))
            }
        }
        holder.realArtistName.text = realArtistData[position].realArtistName

    }
}