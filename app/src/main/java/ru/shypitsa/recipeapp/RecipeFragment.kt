package ru.shypitsa.recipeapp

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.shypitsa.recipeapp.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private var recipeId: Int? = null
    private var recipeName: String? = null

    private var _binding: FragmentRecipeBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentRecipeBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getValueRecipeFromBundle()
    }

    private fun getValueRecipeFromBundle() {
        val tv: TextView = binding.tvTitleRecipe
        arguments?.let {
            recipeName = it.parcelable<Recipe>(KEY_IN_BUNDLE_FOR_ARG_RECIPE)?.title
            recipeId = it.parcelable<Recipe>(KEY_IN_BUNDLE_FOR_ARG_RECIPE)?.id
        }
        tv.text = recipeName
    }

    private inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelable(key) as? T
    }

}