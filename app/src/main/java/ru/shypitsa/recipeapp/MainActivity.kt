package ru.shypitsa.recipeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.shypitsa.recipeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {

            showCategories()

            binding.btnCategories.setOnClickListener {
                showCategories()
            }

            binding.btnFavourites.setOnClickListener {
                showFavourites()
            }
        }

    }

    private fun showFavourites() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.commit {
            replace<FavoritesFragment>(R.id.mainContainer)
            setReorderingAllowed(true)
            addToBackStack(null)

        }

    }

    private fun showCategories() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.commit {
            replace<CategoriesListFragment>(R.id.mainContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

}