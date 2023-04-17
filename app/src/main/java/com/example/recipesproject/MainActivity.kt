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

    val recipes = mapOf(
        "Spaghetti Carbonara" to Recipe(
            id = null,
            title = "Carbonara",
            imageId = R.drawable.carbonara,
            ingridients= "Spaghetti, Bacon, Eggs, Parmesan Cheese, Garlic, Salt, Pepper",
            steps = "Cook the spaghetti in a pot of salted boiling water until al dente."+
                    "While the spaghetti cooks, fry the bacon in a pan until crispy. Remove from pan and set aside."+
                    "Beat the eggs in a bowl, add grated Parmesan cheese, minced garlic, salt, and pepper."+
                    "Drain the spaghetti and add it to the pan with the bacon, keeping the heat on low."+
                    "Pour the egg mixture over the spaghetti and toss until the eggs are cooked and the spaghetti is coated. Serve immediately."

        ),
        "Chicken Curry" to Recipe(
            id = null,
            title = "Chicken Curry",
            imageId = R.drawable.chicken_curry,
            ingridients = "Chicken, Onion, Garlic, Ginger, Curry powder, Coconut milk, Tomato paste, Salt, Pepper, Rice",

            steps =  "Cut the chicken into bite-sized pieces and season with salt and pepper."+
                    "Heat oil in a pan and add the chicken. Cook until browned on all sides."+
                    "Remove the chicken from the pan and set aside. In the same pan, sauté the onion, garlic, and ginger until fragrant."+
                    "Add curry powder and tomato paste to the pan and stir to combine."+
                    "Add coconut milk to the pan and bring to a simmer. Add the chicken back to the pan and let it cook for a few minutes."+
                    "Serve the chicken curry with rice."

        ),
        "Beef Stew" to Recipe(
            id = null,
            title = "Beef Stew",
            imageId = R.drawable.beef_stew,
            ingridients = "Beef, Carrots, Potatoes, Onion, Garlic, Tomato paste, Beef broth, Red wine, Flour, Thyme, Rosemary, Salt, Pepper",
            steps = "Heat oil in a large pot over medium-high heat. Add the beef and cook until browned on all sides."+
                    "Remove the beef from the pot and set aside. In the same pot, sauté the carrots, potatoes, onion, and garlic until tender."+
                    "Add tomato paste and flour to the pot and stir to combine."+
                    "Pour in the beef broth and red wine. Add the beef back to the pot along with thyme, rosemary, salt, and pepper."+
                    "Bring the stew to a simmer and let it cook for about an hour, until the beef is tender and the vegetables are cooked through."

        ),
        "Pesto Pasta" to Recipe(
            id = null,
            title = "Pesto Pasta",
            imageId =R.drawable.pesto_pasta,
            ingridients = "Pasta, Basil, Garlic, Parmesan Cheese, Pine nuts, Olive oil, Salt, Pepper",
            steps = "Cook the pasta in a pot of salted boiling water until al dente."+
                    "While the pasta cooks, combine the basil, garlic, Parmesan cheese, pine nuts, salt, and pepper in a food processor."+
                    "Pulse the ingredients until they are finely chopped. While the food processor is running, slowly pour in the olive oil."+
                    "Drain the pasta and toss with the pesto sauce."+
                    "Serve the pasta with extra Parmesan cheese and pine nuts on top."

        ),
        "Tomato Soup" to Recipe(
            id = null,
            title = "Tomato Soup",
            imageId = R.drawable.tomato_soup,
            ingridients = "Tomatoes, Onion, Garlic, Vegetable broth, Heavy cream, Basil, Olive oil, Salt, Pepper",
            steps = "Preheat oven to 400°F (200°C)."+
                    "Cut tomatoes into quarters and place on a baking sheet with onion and garlic."+
                    "Drizzle with olive oil, salt, and pepper. Roast for 30-35 minutes."+
                    "Add the roasted vegetables to a blender with vegetable broth and blend until smooth."+
                    "Pour the blended soup into a pot and bring to a simmer."+
                    "Add heavy cream and basil to the pot and stir to combine. Simmer for another 5-10 minutes."+
                    "Serve hot with bread or croutons."

        )

    )


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

            //add to database from map recipes

//            clear database
//            Thread {
//                db.getDao().deleteAll()
//            }.start()

            recipes.forEach {
                val recipe = Recipe(imageId= it.value.imageId, title = it.value.title, ingridients= it.value.ingridients, steps= it.value.steps)
                Thread {
                    db.getDao().insert(recipe)
                }.start()
            }


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



