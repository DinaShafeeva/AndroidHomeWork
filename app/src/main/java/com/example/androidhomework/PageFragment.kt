package com.example.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

val ARGUMENT_PAGE_NUMBER = "arg_page_number"
var pageNumber: Int = 0

class PageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = arguments?.getInt(ARGUMENT_PAGE_NUMBER) ?: 0

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page, container, false)

        val image = view.findViewById(R.id.iv_view_pager) as ImageView
        //image.setImageResource(R.drawable.blackwidow)
        return view
    }

    companion object {
        fun newInstance(page: Int): PageFragment {
            val pageFragment = PageFragment()
            val arguments = Bundle()
            arguments.putInt(ARGUMENT_PAGE_NUMBER, page)
            pageFragment.arguments = arguments
            return pageFragment
        }
    }
}

private class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment =
        PageFragment.newInstance(position)

    override fun getCount(): Int = pageNumber

}
