package com.example.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment : Fragment() {
    private var adapter: InfoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_info.layoutManager = LinearLayoutManager(context)
        adapter = InfoAdapter(getDataSource(), context)
        rv_info.adapter = adapter

    }

    private fun getDataSource(): List<Info> = arrayListOf(
        Info("IronMan", "Iron suit and harisma", R.drawable.ironman),
        Info("CaptainAmerica", "Super patriotism and shield", R.drawable.cap),
        Info("Thor", "Thunder and hummer", R.drawable.hulk),
        Info("Hulk", "Anger and smash", R.drawable.hulk),
        Info("BlackWidow", "Guns and beauty", R.drawable.blackwidow),
        Info("Hawkeye", "Arrows", R.drawable.hawkeye),
        Info("ScarletWitch", "Magic", R.drawable.scarletwitch),
        Info("Vision", "Technology and Mind Stone", R.drawable.hulk)
    )

    fun getViewPager(): Map<String, ArrayList<Int>> = mapOf(
        "IronMan" to arrayListOf(R.drawable.ironman, R.drawable.av1, R.drawable.av2),
        "CaptainAmerica" to arrayListOf(
            R.drawable.cap,
            R.drawable.av2,
            R.drawable.av3,
            R.drawable.av1
        ),
        "Thor" to arrayListOf(R.drawable.cap, R.drawable.av2, R.drawable.av3, R.drawable.av1),
        "Hulk" to arrayListOf(R.drawable.hulk, R.drawable.av2, R.drawable.av3, R.drawable.av1),
        "BlackWidow" to arrayListOf(
            R.drawable.blackwidow,
            R.drawable.av2,
            R.drawable.av3,
            R.drawable.av1
        ),
        "Hawkeye" to arrayListOf(
            R.drawable.hawkeye,
            R.drawable.av2,
            R.drawable.av3,
            R.drawable.av1
        ),
        "ScarletWitch" to arrayListOf(
            R.drawable.scarletwitch,
            R.drawable.av2,
            R.drawable.av3,
            R.drawable.av1
        ),
        "Vision" to arrayListOf(R.drawable.scarletwitch, R.drawable.av2)
    )

    companion object {
        fun newInstance(): ThirdFragment = ThirdFragment()
    }
}

