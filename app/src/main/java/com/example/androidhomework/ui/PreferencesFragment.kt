package com.example.androidhomework.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_preferences.view.*

class PreferencesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_preferences, container, false)
        view.btn_preferences.setOnClickListener {
            nextFragment()
        }
        return view
    }

    private fun nextFragment() {
        fragmentManager.also {
            it?.beginTransaction()?.apply {
                replace(R.id.nav_host_fragment, ChoosePreferencesFragment.newInstance())
                fragmentManager?.popBackStack()
                addToBackStack(ChoosePreferencesFragment::class.java.name)
                commit()
            }
        }
    }

    companion object {
        fun newInstance(): PreferencesFragment = PreferencesFragment()
    }
}
