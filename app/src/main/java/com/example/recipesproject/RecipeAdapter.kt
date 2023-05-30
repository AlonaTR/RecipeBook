package com.example.recipesproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesproject.databinding.RecipeItemBinding
import java.util.ArrayList

class RecipeAdapter(val listener: FragmentMainList): RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {
    val recipeList = ArrayList<Data>()

    fun update(recipes: List<Data>) {
        recipeList.clear()
        recipeList.addAll(recipes)
        notifyDataSetChanged()
    }

    class RecipeHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = RecipeItemBinding.bind(item)
        fun bind(recipe: Data, listener: OnItemClickListener) = with(binding) {
            im.setImageResource(recipe.imageId)
            tvTitle.text = recipe.title
            itemView.setOnClickListener {
                listener.onItemClick(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeHolder(view)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bind(recipeList[position], listener)
    }

    fun addRecipe(recipe: Data){
        recipeList.add(recipe)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(recipe: Data)
    }
}