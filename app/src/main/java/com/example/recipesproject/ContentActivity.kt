package com.example.recipesproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.recipesproject.R
import com.example.recipesproject.databinding.ActivityContentBinding
import com.example.recipesproject.databinding.ActivityContentTabletBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (resources.getBoolean(R.bool.isTablet)) {
            binding = ActivityContentTabletBinding.inflate(layoutInflater)
            setContentView(binding.root)
        } else {
            binding = ActivityContentBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }

        supportFragmentManager.beginTransaction().add(R.id.place_holder, TimerFragment()).commit()

        val item = intent.getSerializableExtra("item") as Recipe

        // Cast the binding to the correct type
        when (binding) {
            is ActivityContentBinding -> {
                val contentBinding = binding as ActivityContentBinding
                contentBinding.imMain.setImageResource(item.imageId)
                contentBinding.tvTitle.text = item.title
                contentBinding.tvingrideints.text = item.ingridients
                contentBinding.tvsteps.text = item.steps
                contentBinding.bClose.setOnClickListener {
                    finish()
                }
            }
            is ActivityContentTabletBinding -> {
                val tabletBinding = binding as ActivityContentTabletBinding
                tabletBinding.imMain.setImageResource(item.imageId)
                tabletBinding.tvTitle.text = item.title
                tabletBinding.tvingrideints.text = item.ingridients
                tabletBinding.tvsteps.text = item.steps
                tabletBinding.bClose.setOnClickListener {
                    finish()
                }
            }
        }
    }
}
