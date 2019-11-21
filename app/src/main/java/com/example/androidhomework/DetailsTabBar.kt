package com.example.androidhomework

//
//
//import android.content.Context
//import android.util.AttributeSet
//import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
//import java.util.Collections.emptyList
//import android.view.View
//import com.google.android.material.tabs.TabLayout
//import android.widget.FrameLayout
//import java.util.*
//
//class DetailsTabBar : FrameLayout, DividerView{
//
//        @BindView(R.id.tabs)
//        internal var tabLayout: TabLayout? = null
//        @BindView(R.id.section_divider)
//        internal var divider: View? = null
//
//        private var lastTabs = Collections.emptyList()
//
//        constructor(context: Context) : super(context) {
//            init()
//        }
//
//        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//            init()
//        }
//
//        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
//            context,
//            attrs,
//            defStyleAttr
//        ) {
//            init()
//        }
//
//        private fun init() {
//            View.inflate(context, R.layout.wl_details_tab_bar, this)
//            ButterKnife.bind(this)
//        }
//
//        fun bind(
//            tabs: List<WLTab>,
//            selectedPosition: Int,
//            onTabSelectedListener: OnTabSelectedListener
//        ) {
//            tabLayout!!.clearOnTabSelectedListeners()
//
//            if (lastTabs != tabs) {
//                setNewTabs(tabs)
//            }
//
//            // We don't set the position if it is the same, since the layout will reanimate back to that position
//            if (selectedPosition != tabLayout!!.selectedTabPosition) {
//                tabLayout!!.getTabAt(selectedPosition)!!.select()
//            }
//
//            // Add the listener after selecting the tab so the listener isn't called
//            tabLayout!!.addOnTabSelectedListener(onTabSelectedListener)
//
//            lastTabs = ImmutableList.copyOf(tabs)
//        }
//
//        private fun setNewTabs(tabs: List<WLTab>) {
//            tabLayout!!.removeAllTabs()
//
//            for (tab in tabs) {
//                tabLayout!!.addTab(
//                    tabLayout!!.newTab()
//                        .setCustomView(R.layout.wl_details_tab_bar_item)
//                        .setText(tab.titleRes)
//                )
//            }
//        }
//
//        fun showDivider(showDivider: Boolean) {
//            setVisibleIf(divider, showDivider)
//        }
//
//}