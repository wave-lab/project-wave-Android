package com.song2.wave.UI.MainPlayer.Adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.song2.wave.R


class CoverImgViewPager(private val ctx: Context, private val imageList: ArrayList<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_cover_img_viewpager, null)
        val cover_img : ImageView = view.findViewById(R.id.iv_main_player_act_cover_img)

        Glide.with(ctx).load(imageList[position]).into(cover_img)

        container.addView(view)

        return view
    }

    override fun getCount() = imageList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o as View
    }

    fun transformPage(page: View, position: Float) {
        var normalizedposition = Math.abs(Math.abs(position) - 1)


        page.setScaleX(normalizedposition / 2 + 0.5f)
        page.setScaleY(normalizedposition / 2 + 0.5f)
    }
}