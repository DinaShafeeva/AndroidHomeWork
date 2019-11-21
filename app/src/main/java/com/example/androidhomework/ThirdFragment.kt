package com.example.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment : Fragment() {
    private var adapter: InfoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //     adapter = InfoAdapter(getDataSource()) { _,_ ->
        //    }
        rv_info.adapter = adapter


    }

//    private fun getDataSource(): List<Info> = arrayListOf(9
//        Info("IronMan", "Iron suit and harisma", R.drawable.ironman, ,R.drawable.ic_dashboard_black_24dp),
//    Info("CaptainAmerica", "Super patriotism and shield",R.drawable.ic_dashboard_black_24dp, null),
//    Info("Thor", "Thunder and hummer",R.drawable.ic_dashboard_black_24dp, null),
//    Info("Hulk", "Anger and smash"),
//    Info("BlackWidow", "Guns and beauty"),
//    Info("Hawkeye", "Arrows"),
//    Info("ScarletWitch", "Magic"),
//    Info("Vision", "Technology and Mind Stone")
//    )




    companion object {
        fun newInstance(): ThirdFragment = ThirdFragment()
    }
}

