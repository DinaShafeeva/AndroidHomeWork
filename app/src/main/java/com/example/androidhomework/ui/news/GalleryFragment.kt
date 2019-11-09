package com.example.androidhomework.ui.news


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment


import com.example.androidhomework.R

class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_news2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sum = arguments?.getInt(ARG_SUM) ?: 0
        Toast.makeText(context, sum.toString(), Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val ARG_SUM = "sum"

        fun newInstance(sum: Int = 0): GalleryFragment = GalleryFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_SUM, sum)
            }
        }
    }
}