package com.example.androidhomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_hero.*

class HeroHolder(
    override val containerView: View,
    private val clickLambda: (Hero) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(hero: Hero) {
        tv_name.text = hero.name
        tv_power.text = hero.power
        itemView.setOnClickListener {
            clickLambda(hero)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (Hero) -> Unit) =
            HeroHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false),
                clickLambda
            )
    }
}
