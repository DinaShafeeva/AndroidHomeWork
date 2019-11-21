package com.example.androidhomework

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

object Diff : DiffUtil.ItemCallback<Hero>() {

    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean =
        oldItem.power == newItem.power

    override fun getChangePayload(oldItem: Hero, newItem: Hero): Any? {
        val diffBundle = Bundle()
        if (oldItem.name != newItem.name) {
            diffBundle.putString("name", newItem.name)
        }
        if (oldItem.power != newItem.power) {
            diffBundle.putString("power", newItem.power)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }

}