package com.example.recipesproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipesproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RecipeAdapter.OnItemClickListener {
    lateinit var binding: ActivityMainBinding
    private val adapter = RecipeAdapter(this)

    val recipes = mapOf(
        "Spaghetti Carbonara" to Data(
            id = null,
            title = "Carbonara",
            imageId = R.drawable.carbonara,
            ingridients= "Spaghetti, Bacon, Eggs, Parmesan Cheese, Garlic, Salt, Pepper",
            steps = "Cook the spaghetti in a pot of salted boiling water until al dente."+
                    "While the spaghetti cooks, fry the bacon in a pan until crispy. Remove from pan and set aside."+
                    "Beat the eggs in a bowl, add grated Parmesan cheese, minced garlic, salt, and pepper."+
                    "Drain the spaghetti and add it to the pan with the bacon, keeping the heat on low."+
                    "Pour the egg mixture over the spaghetti and toss until the eggs are cooked and the spaghetti is coated. Serve immediately.",
            category = "Main Course"

        ),
        "Chicken Curry" to Data(
            id = null,
            title = "Chicken Curry",
            imageId = R.drawable.chicken_curry,
            ingridients = "Chicken, Onion, Garlic, Ginger, Curry powder, Coconut milk, Tomato paste, Salt, Pepper, Rice",

            steps =  "Cut the chicken into bite-sized pieces and season with salt and pepper."+
                    "Heat oil in a pan and add the chicken. Cook until browned on all sides."+
                    "Remove the chicken from the pan and set aside. In the same pan, sauté the onion, garlic, and ginger until fragrant."+
                    "Add curry powder and tomato paste to the pan and stir to combine."+
                    "Add coconut milk to the pan and bring to a simmer. Add the chicken back to the pan and let it cook for a few minutes."+
                    "Serve the chicken curry with rice.",
            category = "Main Course"

        ),
//      generete 3 deserts
        "Chocolate Cake" to Data(
            id = null,
            title = "Chocolate Cake",
            imageId = R.drawable.chocolate_cake,
            ingridients = "Flour, Sugar, Cocoa powder, Baking soda, Salt, Milk, Vegetable oil, Vanilla extract, Boiling water",
            steps = "Preheat the oven to 350°F. Grease and flour a 9-inch round cake pan."+
                    "In a large bowl, whisk together the flour, sugar, cocoa powder, baking soda, and salt."+
                    "In a separate bowl, whisk together the milk, vegetable oil, and vanilla extract."+
                    "Add the wet ingredients to the dry ingredients and mix until combined."+
                    "Pour the boiling water into the batter and mix until combined."+
                    "Pour the batter into the prepared cake pan and bake for 30-35 minutes, until a toothpick inserted into the center comes out clean."+
                    "Let the cake cool completely before frosting.",
            category = "Dessert"
        ),
        "Chocolate Chip Cookies" to Data(
            id = null,
            title = "Cookies",
            imageId = R.drawable.chocolate_chip_cookies,
            ingridients = "Flour, Sugar, Baking soda, Salt, Butter, Vanilla extract, Eggs, Chocolate chips",
            steps = "Preheat the oven to 350°F. Line a baking sheet with parchment paper."+
                    "In a large bowl, whisk together the flour, sugar, baking soda, and salt."+
                    "In a separate bowl, cream together the butter and vanilla extract."+
                    "Add the eggs to the butter mixture and mix until combined."+
                    "Add the wet ingredients to the dry ingredients and mix until combined."+
                    "Fold in the chocolate chips."+
                    "Drop the cookie dough by rounded spoonfuls onto the prepared baking sheet."+
                    "Bake for 8-10 minutes, until the edges are golden brown."+
                    "Let the cookies cool on the baking sheet for 5 minutes before transferring to a wire rack to cool completely.",
            category = "Dessert"
        ),
        "Apple Pie" to Data(
            id = null,
            title = "Apple Pie",
            imageId = R.drawable.apple_pie,
            ingridients = "Apples, Sugar, Flour, Butter, Cinnamon, Salt, Pie crust",
            steps = "Preheat the oven to 425°F. Line a baking sheet with parchment paper."+
                    "Peel, core, and slice the apples. Toss the apples with the sugar, flour, cinnamon, and salt."+
                    "Roll out one pie crust and place it in a 9-inch pie pan. Pour the apple filling into the crust."+
                    "Roll out the remaining pie crust and place it on top of the pie. Crimp the edges to seal."+
                    "Cut a few slits in the top crust to allow steam to escape."+
                    "Bake for 15 minutes. Reduce the oven temperature to 350°F and bake for an additional 35-45 minutes, until the crust is golden brown and the filling is bubbling."+
                    "Let the pie cool completely before serving.",
            category = "Dessert"
        ),

        "Beef Stew" to Data(
            id = null,
            title = "Beef Stew",
            imageId = R.drawable.beef_stew,
            ingridients = "Beef, Carrots, Potatoes, Onion, Garlic, Tomato paste, Beef broth, Red wine, Flour, Thyme, Rosemary, Salt, Pepper",
            steps = "Heat oil in a large pot over medium-high heat. Add the beef and cook until browned on all sides."+
                    "Remove the beef from the pot and set aside. In the same pot, sauté the carrots, potatoes, onion, and garlic until tender."+
                    "Add tomato paste and flour to the pot and stir to combine."+
                    "Pour in the beef broth and red wine. Add the beef back to the pot along with thyme, rosemary, salt, and pepper."+
                    "Bring the stew to a simmer and let it cook for about an hour, until the beef is tender and the vegetables are cooked through.",
            category = "Main Course"
        ),
        "Pesto Pasta" to Data(
            id = null,
            title = "Pesto Pasta",
            imageId =R.drawable.pesto_pasta,
            ingridients = "Pasta, Basil, Garlic, Parmesan Cheese, Pine nuts, Olive oil, Salt, Pepper",
            steps = "Cook the pasta in a pot of salted boiling water until al dente."+
                    "While the pasta cooks, combine the basil, garlic, Parmesan cheese, pine nuts, salt, and pepper in a food processor."+
                    "Pulse the ingredients until they are finely chopped. While the food processor is running, slowly pour in the olive oil."+
                    "Drain the pasta and toss with the pesto sauce."+
                    "Serve the pasta with extra Parmesan cheese and pine nuts on top.",
            category = "Main Course"

        ),
        "Tomato Soup" to Data(
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
                    "Serve hot with bread or croutons.",
            category = "Main Course"

        )

    )


    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        binding.bMenu?.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.welcome ->{
                    // make image visible and recycler view invisible
                    binding.imwelcome?.visibility = View.VISIBLE
                    binding.text1?.visibility= View.VISIBLE
                    binding.textwelcome?.visibility= View.VISIBLE
                    binding.rcView.visibility = View.INVISIBLE
                }
                R.id.dessert -> {
                    binding.imwelcome?.visibility = View.INVISIBLE
                    binding.text1?.visibility = View.INVISIBLE
                    binding.textwelcome?.visibility = View.INVISIBLE
                    binding.rcView.visibility = View.VISIBLE
                    adapter.update(recipes.filter { it.value.category == "Dessert" }.map { it.value })
                }
                R.id.main_course -> {
                    binding.imwelcome?.visibility = View.INVISIBLE
                    binding.text1?.visibility = View.INVISIBLE
                    binding.textwelcome?.visibility = View.INVISIBLE
                    binding.rcView.visibility = View.VISIBLE
                    adapter.update(recipes.filter { it.value.category == "Main Course" }.map { it.value })
                }
                R.id.all -> {
                    binding.imwelcome?.visibility = View.INVISIBLE
                    binding.text1?.visibility = View.INVISIBLE
                    binding.textwelcome?.visibility = View.INVISIBLE
                    binding.rcView.visibility = View.VISIBLE
                    adapter.update(recipes.map { it.value })
                }
            }
            true
        }


    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            rcView.adapter = adapter


            val db = Recipes.getDb(this@MainActivity)

            //add to database from map recipes

//            Thread {
//                db.getDao().deleteAll()
//            }.start()

//            recipes.forEach {
//                val recipe = Data(imageId= it.value.imageId, title = it.value.title, ingridients= it.value.ingridients, steps= it.value.steps, category = it.value.category)
//                Thread {
//                    db.getDao().insert(recipe)
//                }.start()
//            }


            db.getDao().getAll().asLiveData().observe(this@MainActivity) {list ->

                list.forEach(){
                    val recipe = Data(imageId= it.imageId, title = it.title, ingridients= it.ingridients, steps= it.steps, category = it.category)
                    adapter.addRecipe(recipe)
                }
            }


        }
    }

    override fun onItemClick(recipe: Data) {
        val intent = Intent(this, ContentActivity::class.java)
        intent.putExtra("item", recipe)
        startActivity(intent)
        Log.d("MyLog", "onItemClick: $recipe")
    }
}



