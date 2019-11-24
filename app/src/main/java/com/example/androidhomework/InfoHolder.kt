package com.example.androidhomework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_info.view.*

class InfoHolder(
    override val containerView: View,
    val context: Context?
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val tv_name_info = containerView.tv_name_info
    private val tv_description_info = containerView.tv_description_info
    private val viewPager = containerView.vp_info
    private val iv_ava_info = containerView.iv_ava_info
    //private val indicator = containerView.indicator

    fun bind(info: Info) {
        tv_name_info.text = info.nameOfHero
        tv_description_info.text = info.description
        iv_ava_info.setImageResource(info.ava)

        val thirdFragment = ThirdFragment()

        val map = thirdFragment.getViewPager()
        val imageList: List<Int> = map.getValue(info.nameOfHero)
        viewPager.adapter = ViewPager(context, imageList)
        //indicator.setViewPager(viewPager)
    }

    companion object {
        fun create(parent: ViewGroup, context: Context?) =
            InfoHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false),
                context = context
            )
    }
}
