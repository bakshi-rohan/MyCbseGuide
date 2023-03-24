package com.example.mycbseguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycbseguide.Adapters.CategoryAdapter
import com.example.mycbseguide.Model.Category
import com.example.mycbseguide.databinding.ActivityMainBinding
import java.util.ArrayList

class Dashboard : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
    lateinit var image: Array<Int>
    lateinit var description: Array<String>
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var categoryRecyclerView: RecyclerView
    lateinit var newArrayList: ArrayList<Category>
    lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        setContentView(binding.root)

        categoryRecyclerView = binding.recycler
        categoryRecyclerView.layoutManager = GridLayoutManager(this, 3)
        newArrayList = arrayListOf<Category>()

        getCategories()
    }
//function for getting categories through api
    private fun getCategories(){
viewModel.requestCategory(this,binding.progress).observe(this,{
    categoryAdapter = CategoryAdapter(it.categories,this)
    binding.recycler.adapter = categoryAdapter
})
    }
}