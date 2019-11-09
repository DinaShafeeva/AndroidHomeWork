package com.example.androidhomework

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.androidhomework.ui.ProfileFragment
import com.example.androidhomework.ui.profile.Profile2Fragment
import com.google.android.material.navigation.NavigationView

class Main2Activity : AppCompatActivity(), ProfileFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(sum: Int) {
        supportFragmentManager.also {
            it.beginTransaction().apply {
                //                 setCustomAnimations(
//                   R.anim.enter_from_right,
//                  R.anim.exit_to_left,
//                R.anim.enter_from_left,
//                R.anim.exit_to_right
//                  )
                replace(R.id.container, ProfileFragment.newInstance())
                addToBackStack(Profile2Fragment::class.java.name)
                commit()
            }
        }
    }


    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
