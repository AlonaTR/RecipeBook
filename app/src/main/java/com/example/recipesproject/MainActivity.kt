package com.example.recipesproject
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.recipesproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewPager2: ViewPager2? = null
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager2 = binding.viewPager

        viewPager2?.adapter = MainPagerAdapter(this)

        bottomNavigationView = binding.bMenu
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.welcome -> viewPager2?.currentItem = 0
                R.id.all -> viewPager2?.currentItem = 1
                R.id.main_course -> viewPager2?.currentItem = 2
                R.id.dessert -> viewPager2?.currentItem = 3
            }
            true
        }

        viewPager2?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.selectedItemId = getMenuItemId(position)
            }
        })
    }

    private fun getMenuItemId(position: Int): Int {
        return when (position) {
            0 -> R.id.welcome
            1 -> R.id.all
            2 -> R.id.main_course
            3 -> R.id.dessert
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }


    private inner class MainPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FragmentWelcome()
                1 -> FragmentMainList.newInstance(null)
                2 -> FragmentMainList.newInstance("Main Course")
                3 -> FragmentMainList.newInstance("Dessert")
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }
    }
}
