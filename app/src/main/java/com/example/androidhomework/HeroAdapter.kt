package com.example.androidhomework

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class HeroAdapter(
    private var dataSource: ArrayList<Hero>,
    private val clickLambda: (Hero) -> Unit
) : ListAdapter<Hero, HeroHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder =
        HeroHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: HeroHolder, position: Int) =
        holder.bind(dataSource[position])

    fun updateList(newList: ArrayList<Hero>) {
        androidx.recyclerview.widget.DiffUtil.calculateDiff(
            DiffUtil(this.dataSource, newList),
            true
        )
            .dispatchUpdatesTo(this)
        this.dataSource.clear()
        this.dataSource.addAll(newList)
    }
}
