package com.example.androidhomework

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.bottom_navigation.*


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.btv_main)

        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                R.id.first -> {
                    val firstFragment = FirstFragment.newInstance()
                    openFragment(firstFragment)
                    true
                }
                R.id.second -> {
                    val secondFragment: SecondFragment = SecondFragment.newInstance()
                    openFragment(secondFragment)
                    true
                }
                R.id.third -> {
                    val thirdFragment = ThirdFragment.newInstance()
                    openFragment(thirdFragment)
                    true
                }

                else -> false
            }
        }
        btv_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction().apply { }
        transaction.replace(R.id.container_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
