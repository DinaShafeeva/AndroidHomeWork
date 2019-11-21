package com.example.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import kotlinx.android.synthetic.main.fragment_second.*


class DialogFragment : androidx.fragment.app.DialogFragment() {

    private var callback: ((Int) -> Unit)? = null
    private var dialogView: View? = null
    private var adapter: HeroAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog;
        val view: View = inflater.inflate(R.layout.fragment_dialog, container, false)

        view.btn_add_dialog.setOnClickListener {
            rv_hero.adapter = adapter
        }

        view.btn_cancel_dialog.setOnClickListener {
            dialog?.let { it1 -> onDismiss(it1) }
        }

        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    companion object {
        fun show(fragmentManager: FragmentManager, onDialogFragment: ((Int) -> Unit)?):
                DialogFragment = DialogFragment().apply {
            callback = onDialogFragment
            show(fragmentManager, DialogFragment::class.java.name)

        }
    }
}
