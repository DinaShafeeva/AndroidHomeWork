package com.example.androidhomework

import androidx.recyclerview.widget.DiffUtil

class DiffUtil(private val oldList: List<Hero>, private val newList: List<Hero>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size


}