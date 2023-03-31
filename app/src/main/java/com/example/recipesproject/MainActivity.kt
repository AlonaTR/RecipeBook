package com.example.recipesproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipesproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RecipeAdapter.OnItemClickListener {
    lateinit var binding: ActivityMainBinding
    private val adapter = RecipeAdapter(this)



    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }
    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter


            val db = Recipes.getDb(this@MainActivity)


            db.getDao().getAll().asLiveData().observe(this@MainActivity) {list ->

                list.forEach(){
                    val recipe = Recipe(imageId= it.imageId, title = it.title, ingridients= it.ingridients, steps= it.steps)
                    adapter.addRecipe(recipe)
                }
            }


        }
    }

    override fun onItemClick(recipe: Recipe) {
        val intent = Intent(this, ContentActivity::class.java)
        intent.putExtra("item", recipe)
        startActivity(intent)
        Log.d("MyLog", "onItemClick: $recipe")
    }
}



