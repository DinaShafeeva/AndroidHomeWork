package com.example.androidhomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_info.*


class InfoHolder(
    override val containerView: View,
    private val clickLambda: (String, Info) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(info: Info) {
        tv_name_info.text = info.nameOfHero
        tv_description_info.text = info.description

        itemView.setOnClickListener {
            clickLambda(info.nameOfHero, info)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (String, Info) -> Unit) =
            InfoHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false),
                clickLambda
            )
    }
}
