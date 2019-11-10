package com.example.androidhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidhomework.ui.ProfileFragment
import com.google.android.material.navigation.NavigationView

class Main2Activity : AppCompatActivity(), ProfileFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(sum: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = this.findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_profile, R.id.nav_news, R.id.nav_reviews,
                R.id.nav_preferences
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}

//    override fun onFragmentInteraction(sum: Int) {
//        supportFragmentManager.also {
//            it.beginTransaction().apply {
//                //                 setCustomAnimations(
////                   R.anim.exit_from_right,
////                  R.anim.exit_to_left,
////                R.anim.enter_from_left,
////                R.anim.exit_to_right
////                  )
//                replace(R.id.container, ProfileFragment.newInstance())
//                addToBackStack(Profile2Fragment::class.java.name)
//                commit()
//            }
//        }
//    }
//
//
//    private lateinit var appBarConfiguration: AppBarConfiguration
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
//
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        val navView: NavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
//
//
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main2, menu)
//        return true
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
//
//}


//    var navigationPosition: Int = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
//
//        setSupportActionBar(toolbar)
//        setUpDrawerLayout()
//        navigationPosition = R.id.nav_profile
//
//        nav_view.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_profile -> {
//                    toolbar.title = getString(R.string.menu_profile)
//                    navigationPosition = R.id.nav_profile
//                    navigateToFragment(ProfileFragment.newInstance())
//                }
//                R.id.nav_news -> {
//                    toolbar.title = getString(R.string.menu_news)
//                    navigationPosition = R.id.nav_news
//                    navigateToFragment(NewsFragment.newInstance())
//                }
//                R.id.nav_reviews -> {
//                    toolbar.title = getString(R.string.menu_reviews)
//                    navigationPosition = R.id.nav_reviews
//                    navigateToFragment(ReviewsFragment.newInstance())
//                }
//                R.id.nav_preferences -> {
//                    toolbar.title = getString(R.string.menu_preferences)
//                    navigationPosition = R.id.nav_preferences
//                    navigateToFragment(PreferencesFragment.newInstance())
//                }
//            }
//            drawer_layout.closeDrawer(GravityCompat.START)
//            true
//        }

//    private fun setUpDrawerLayout() {
//        val toggle = ActionBarDrawerToggle(
//            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//    }
//
////    override fun onFragmentInteraction(sum: Int) {
////        supportFragmentManager.also {
////            it.beginTransaction().apply {
////                //                 setCustomAnimations(
//////                   R.anim.exit_from_right,
//////                  R.anim.exit_to_left,
//////                R.anim.enter_from_left,
//////                R.anim.exit_to_right
//////                  )
////                replace(R.id.container, ProfileFragment.newInstance())
////                addToBackStack(Profile2Fragment::class.java.name)
////                commit()
////            }
////        }
////    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.activity_main2_drawer, menu)
//        return true
//    }
//
//   // private lateinit var appBarConfiguration: AppBarConfiguration
//
//
//            private fun navigateToFragment(fragmentToNavigate: Fragment): Boolean {
//                val transaction = supportFragmentManager.beginTransaction()
//                transaction.replace(R.id.frameLayout, fragmentToNavigate)
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                transaction.addToBackStack(null)
//                transaction.commit()
//                return true
//            }
//
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//       // val navView: NavigationView = findViewById(R.id.nav_view)
//       // val navController = findNavController(R.id.nav_host_fragment)
//
//    override fun onBackPressed() {
////        when {
////            drawerLayout.isDrawerOpen(GravityCompat.START) -> drawerLayout.closeDrawer(GravityCompat.START)
////            fragmentManager.backStackEntryCount > 0 -> fragmentManager.popBackStack()
////            else -> super.onBackPressed()
//
//
//            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                drawerLayout.closeDrawer(GravityCompat.START)
//            }
//
//                if (navigationPosition == R.id.nav_profile) {
//                finish()
//            } else {
//            navigationPosition = R.id.nav_profile
//            navigateToFragment(ProfileFragment.newInstance())
//            nav_view.setCheckedItem(navigationPosition)
//            toolbar.title = getString(R.string.menu_profile)
//        }
//        }
//        }

////    override fun onSupportNavigateUp(): Boolean {
////        val navController = findNavController(R.id.nav_host_fragment)
////        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
////    }
//
//
