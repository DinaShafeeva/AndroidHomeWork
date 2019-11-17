package com.example.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : Fragment() {


    private var adapter: HeroAdapter? = null
    private val dialogFragment: DialogFragment? = null;

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_second)
//
//        adapter = HeroAdapter(getDataSource()) { name, power, hero ->
//                 }
//
//        rv_hero.adapter = adapter
//    }
//
//    private fun setContentView(fragmentSecond: Int) {
//
//    }

    private fun getDataSource(): List<Hero> = arrayListOf(
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
        adapter = HeroAdapter(getDataSource()) { name, power, hero ->
        }
//        rv_hero.adapter = adapter

//        btn_show_dialog.setOnClickListener {
//            fragmentManager?.let { it1 -> dialogFragment?.show(it1, "dialog") }
//        }
//
//        sr_main.setOnRefreshListener {
//            Handler().postDelayed({
//                Log.e("Ss", "refresh")
//                sr_main.isRefreshing = false
//            }, 4000)
//        }
        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    companion object {
        fun newInstance(): SecondFragment = SecondFragment()
    }
}
