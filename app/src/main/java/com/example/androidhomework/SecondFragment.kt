package com.example.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.item_hero.*

class SecondFragment : Fragment() {

    private val list: ArrayList<Hero> = getDataSource()

    private var adapter: HeroAdapter? = null

    private fun getDataSource(): ArrayList<Hero> = arrayListOf(
        Hero("IronMan", "Iron suit and harisma"),
        Hero("CaptainAmerica", "Super patriotism and shield"),
        Hero("Thor", "Thunder and hummer"),
        Hero("Hulk", "Anger and smash"),
        Hero("BlackWidow", "Guns and beauty"),
        Hero("Hawkeye", "Arrows"),
        Hero("ScarletWitch", "Magic"),
        Hero("Vision", "Technology and Mind Stone")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HeroAdapter(getDataSource()) { hero ->
            btn_delete.setOnClickListener {
                delete(hero)
            }
            setRecyclerViewItemTouchListener()
        }
        rv_hero.adapter = adapter

        btn_show_dialog.setOnClickListener {
            showDialog()
        }
    }

    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val index = viewHolder.adapterPosition
                list.removeAt(index)
                adapter?.updateList(list)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        rv_hero.addItemDecoration(itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(rv_hero)
    }

    private fun delete(hero: Hero) {
        list.remove(hero)
        adapter?.updateList(list)
    }

    private fun showDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_dialog, null)
        val builder = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
        }
        val dialog = builder?.show()
        dialogView.btn_add_dialog.setOnClickListener {
            dialog?.dismiss()
            val name = dialogView.et_name_dialog.text.toString()
            val power = dialogView.et_power_dialog.text.toString()
            var index = dialogView.et_index_dialog.text.toString().toInt() - 1
            val listSize = list.size
            if (index > listSize) {
                index = listSize
            }
            list.add(index, Hero(name, power))
            adapter?.updateList(list)
        }

        dialogView.btn_cancel_dialog.setOnClickListener {
            dialog?.dismiss()
        }
    }
    companion object {
        fun newInstance(): SecondFragment = SecondFragment()
    }
}
