package com.a6tak.practice.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a6tak.practice.R

class CustomAdapter(
    private val dataSet: List<FriendsListFragment.Item>,
    private val itemClick: (String) -> Unit,

    ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsContent: TextView

        init {
            newsContent = view.findViewById(R.id.news_content)
            view.setOnClickListener {
            }
        }
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView

        init {
            imageView = view.findViewById(R.id.news_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FriendsListFragment.Item.News.TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.news_item, parent, false)
                NewsViewHolder(view)
            }

            FriendsListFragment.Item.Image.TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.image_item, parent, false)
                ImageViewHolder(view)
            }

            else -> {
                TODO()
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: FriendsListFragment.Item = dataSet[position]
        when (item) {
            is FriendsListFragment.Item.News -> {
                holder as NewsViewHolder
                holder.newsContent.text = item.text
                holder.itemView.setOnClickListener { itemClick(item.text) }
            }

            is FriendsListFragment.Item.Image -> {
                holder as ImageViewHolder
                holder.imageView.setImageResource(item.imageRes)
                holder.itemView.setOnClickListener { itemClick("image ${item.imageRes}") }
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size


//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//
//    }

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].getType()
    }
}