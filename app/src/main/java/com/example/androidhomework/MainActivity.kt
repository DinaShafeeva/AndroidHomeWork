package com.example.androidhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: FilmsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FilmsAdapter(getDataSource()) { film ->
            navigateToSecondActivity(film.name, film.producer, film.description, film.image)
        }

        rv_films.adapter = adapter
    }

    private fun navigateToSecondActivity(
        name: String,
        producer: String,
        description: String,
        image: Int
    ) {
        startActivity(SecondActivity.createIntent(this, name, producer, description, image))
    }

    private fun getDataSource(): List<Film> = arrayListOf(
        Film("Avengers", "Russo", "Very good film about superheroes", R.drawable.telepuziki),
        Film("Malifecent", "Stromberg", "Well, well, well...", R.drawable.telepuziki),
        Film("StarWars", "Lucas", "Luke, I'm your father", R.drawable.telepuziki),
        Film("Smeshariki", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Telepuziki", "Not genius", "Vacuum cleaner", R.drawable.telepuziki),
        Film("Smeshariki3", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Smeshariki4", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Smeshariki4", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Smeshariki4", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Smeshariki4", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Smesha", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Smeshariki4", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Smeshariki4", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Sme", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Smeshariki4", "Genius", "Really deep mult", R.drawable.telepuziki),
        Film("Smeshariki4", "Genius", "Really deep mult", R.drawable.telepuziki)
    )
}
