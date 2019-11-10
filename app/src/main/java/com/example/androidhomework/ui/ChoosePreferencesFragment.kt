package com.example.androidhomework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_choose_preferences.*
import kotlinx.android.synthetic.main.fragment_choose_preferences.view.*


class ChoosePreferencesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_choose_preferences, container, false)
        view.btn_add_genres.setOnClickListener {
            val first: String = et_first.text.toString()
            val second: String = et_second.text.toString()
            val third: String = et_third.text.toString()
            nextFragment(first, second, third)
        }
        return view
    }

    private fun nextFragment(name: String, curse: String, thing: String) {
        fragmentManager.also {
            it?.beginTransaction()?.apply {
                replace(R.id.choose_preferences_fragment, ShowPreferencesFragment.newInstance())
                fragmentManager?.popBackStack()
                addToBackStack(ShowPreferencesFragment::class.java.name)
                commit()
            }
        }
    }

    companion object {
        fun newInstance(): ChoosePreferencesFragment = ChoosePreferencesFragment()
    }
}