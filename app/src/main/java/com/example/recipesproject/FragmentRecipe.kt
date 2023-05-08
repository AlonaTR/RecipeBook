package com.example.recipesproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.recipesproject.databinding.ActivityContentBinding
import com.example.recipesproject.databinding.ActivityContentTabletBinding
import com.example.recipesproject.databinding.FragmentRecipeBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX


class FragmentRecipe : Fragment() {
    private lateinit var binding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecipeBinding.inflate(inflater, container, false)

        val item = activity?.intent?.getSerializableExtra("item") as Data

        binding.imMain.setImageResource(item.imageId)
        binding.tvTitle.text = item.title
        binding.tvingridients.text = item.ingridients
        binding.tvsteps.text = item.steps


        return binding.root
    }


}

