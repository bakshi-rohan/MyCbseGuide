package com.example.mycbseguide.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mycbseguide.Model.Category
import com.example.mycbseguide.Model.GetCategoryModel
import com.example.mycbseguide.R

class CategoryAdapter (private val catList:List<Category>,var context: Context): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_recyler_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = catList[position]
//        holder.itemView.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_nav_sales)
//        }

        Glide.with(context).load(ItemsViewModel.mobile_logo).apply(
            RequestOptions()
                .placeholder(R.drawable.building)
        ).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView)

        // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageResource(ItemsViewModel.mobile_logo)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.name

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return catList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}