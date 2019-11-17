package com.example.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import kotlinx.android.synthetic.main.fragment_second.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DialogFragment : androidx.fragment.app.DialogFragment() {

    private var callback: ((Int) -> Unit)? = null
    private var dialogView: View? = null
    private var adapter: HeroAdapter? = null

    private var param1: String? = null
    private var param2: String? = null
    //  private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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
            adapter?.add(
                et_name_dialog.text.toString(),
                et_power_dialog.text.toString(),
                et_index_dialog.text.toString().toInt()
            )
        }

        view.btn_cancel_dialog.setOnClickListener {
            dialog?.let { it1 -> onDismiss(it1) }
        }

        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }
//
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }

    companion object {
        fun show(fragmentManager: FragmentManager, onDialogFragment: ((Int) -> Unit)?):
                DialogFragment = DialogFragment().apply {
            callback = onDialogFragment
            show(fragmentManager, DialogFragment::class.java.name)

        }
    }
}
