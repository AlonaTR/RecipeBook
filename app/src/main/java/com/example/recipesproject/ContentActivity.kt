package com.example.recipesproject

import android.content.Intent
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
            supportFragmentManager.beginTransaction().add(R.id.timer_holder, newTimerFragment, "timerFragment").commit()
        } else {
            // Если фрагмент уже существует, то используем его
            supportFragmentManager.beginTransaction().replace(R.id.timer_holder, timerFragment, "timerFragment").commit()

        }




        val item = intent.getSerializableExtra("item") as Data

        // Cast the binding to the correct type
        when (binding) {
            is ActivityContentBinding -> {
                val contentBinding = binding as ActivityContentBinding

                contentBinding.bClose.setOnClickListener {
                    val ingredientData = item.ingridients
                    sendMessage(ingredientData)
                }

                val fragment = FragmentRecipe() // создаем экземпляр фрагмента
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout_recipe, fragment) // заменяем FrameLayout на наш фрагмент
                transaction.commit()

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

    private fun sendMessage(ingredientData: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, ingredientData)
        startActivity(Intent.createChooser(intent, "Send Ingredient Data"))
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
