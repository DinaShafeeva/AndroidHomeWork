package com.example.androidhomework

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HeroAdapter(
    private var dataSource: List<Hero>,
    private val clickLambda: (String, String, Hero) -> Unit
) : RecyclerView.Adapter<HeroHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder =
        HeroHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: HeroHolder, position: Int) =
        holder.bind(dataSource[position])

    fun add(name: String, power: String, index: Int) {
        val temp = dataSource.toMutableList()
        val hero: Hero = Hero(name, power);
        temp.add(index, hero)
        dataSource = temp
        notifyItemRangeChanged(1, temp.size)
    }

    fun delete(index: Int) {
        val list: MutableList<Hero> = dataSource.toMutableList()
        list.removeAt(index)
        dataSource = list
        notifyItemRangeChanged(0, list.size)
    }

}
