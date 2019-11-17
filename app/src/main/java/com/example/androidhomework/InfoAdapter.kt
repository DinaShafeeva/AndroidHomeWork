package com.example.androidhomework

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class InfoAdapter(
    private var dataSource: List<Info>,
    private val clickLambda: (String, Info) -> Unit
) : RecyclerView.Adapter<InfoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHolder =
        InfoHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: InfoHolder, position: Int) =
        holder.bind(dataSource[position])

    fun add(info: Info, index: Int) {
        val temp = dataSource.toMutableList()
        temp.add(index, info)
        dataSource = temp
        notifyItemRangeChanged(1, temp.size)
    }

}
