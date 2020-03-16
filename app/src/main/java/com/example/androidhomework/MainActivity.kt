package com.example.androidhomework

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.recycler.WAdapter
import com.example.androidhomework.respone.Weather
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.IOException

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var service: WeatherService
    private var adapter: WAdapter? = null;
    val permissionRequestCode = 1
    var longitude: Double = 0.0
    var latitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: ArrayList<Weather> = ArrayList()
        adapter = WAdapter(list) { weather ->
            navigateToSecondActivity(weather.id)
        }
        rv_weather.layoutManager = LinearLayoutManager(this)
        rv_weather.adapter = adapter
        service = ApiFactory.weatherService
        checkPerm()
    }

    private fun navigateToSecondActivity(id: Int) {
        startActivity(SecondActivity.createIntent(this, id))
    }

    fun getCities() {
        launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.weatherByLatLon(latitude, longitude, cityCount)
                }
                adapter?.weatherList = response.list
                adapter?.notifyDataSetChanged()
            } catch (ex: IOException) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "City wasn't found",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu);
        val searchItem: MenuItem = menu.findItem(R.id.search);
        val searchView: SearchView = searchItem.actionView as SearchView;
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                queryTextSubmit(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        });
        return true;
    }

    fun queryTextSubmit(query: String): Boolean {
        launch {
            try {
                val response = withContext(context = Dispatchers.IO) {
                    service.weatherByName(query)
                }
                startActivity(SecondActivity.createIntent(this@MainActivity, response.id))
            } catch (e: IOException) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Город не найден",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        return false;
    }

    fun checkPerm() {
        if (ContextCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionWithRationale()
        } else {
            setLocation()
        }
    }

    fun setLocation() {
        val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient
            .lastLocation
            .addOnSuccessListener(this) {
                if (it != null) {
                    longitude = it.longitude
                    latitude = it.latitude
                }
                getCities()

            }
    }

    fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                ACCESS_FINE_LOCATION
            ) && ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                ACCESS_COARSE_LOCATION
            )
        ) {
            val message = getString(R.string.snack_message)
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction(
                    getString(R.string.grant).toUpperCase()
                ) { requestPerms() }
                .show()
        } else {
            requestPerms()
        }
    }

    private fun requestPerms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissions =
                arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
            this.requestPermissions(permissions, permissionRequestCode)
        }
    }

    companion object {
        const val cityCount: Int = 10
    }
}
