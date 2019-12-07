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
    ): View? = inflater.inflate(R.layout.fragment_show_preferences, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val first: String = arguments?.getString("first").toString()
        val second: String = arguments?.getString("second").toString()
        val third: String = arguments?.getString("third").toString()
        tv_first.text = first
        tv_second.text = second
        tv_third.text = third
    }

    companion object {

        private const val ARG_FIRST = "first"
        private const val ARG_SECOND = "second"
        private const val ARG_THIRD = "third"

        fun newInstance(first: String = "NULL", second: String = "NULL", third: String = "NULL"):
                ShowPreferencesFragment = ShowPreferencesFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_FIRST, first)
                putString(ARG_SECOND, second)
                putString(ARG_THIRD, third)
            }
        }
    }
}
