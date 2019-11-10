package com.example.androidhomework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_show_preferences.*


class ShowPreferencesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_show_preferences, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val first: String = arguments?.getString("name").toString()
        val second: String = arguments?.getString("curse").toString()
        val third: String = arguments?.getString("thing").toString()
        tv_first.text = first
        tv_second.text = second
        tv_third.text = third
    }

    companion object {

        private const val FIRST = "first"
        private const val SECOND = "second"
        private const val THIRD = "third"

        fun newInstance(first: String = "NULL", second: String = "NULL", third: String = "NULL"):
                ShowPreferencesFragment = newInstance().apply {
            arguments = Bundle().apply {
                putString(FIRST, first)
                putString(SECOND, second)
                putString(THIRD, third)
            }
        }
    }
}