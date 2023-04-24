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

//        supportFragmentManager.beginTransaction().add(R.id.place_holder, TimerFragment()).commit()
        val timerFragment = supportFragmentManager.findFragmentByTag("timerFragment") as? TimerFragment

        if (timerFragment == null) {
            // Если фрагмент не существует, то создаем новый экземпляр и добавляем его в контейнер
            val newTimerFragment = TimerFragment()
            supportFragmentManager.beginTransaction().add(R.id.place_holder, newTimerFragment, "timerFragment").commit()
        } else {
            // Если фрагмент уже существует, то используем его
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, timerFragment, "timerFragment").commit()

        }


        val item = intent.getSerializableExtra("item") as Data

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

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        val timerFragment = supportFragmentManager.findFragmentByTag("timerFragment") as? TimerFragment
//        timerFragment?.let {
//            supportFragmentManager.putFragment(outState, "timerFragment", it)
//        }
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        val timerFragment = supportFragmentManager.getFragment(savedInstanceState, "timerFragment") as? TimerFragment
//        timerFragment?.let {
//            supportFragmentManager.beginTransaction().replace(R.id.place_holder, it, "timerFragment").commit()
//        }
//    }
}
