package com.debin.localnews.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.debin.localnews.R
import com.debin.localnews.model.NewsData
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(var items : ArrayList<NewsData>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()  {


    fun updateItems(newItems : List<NewsData>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        private val title = view.item_title
        private val description = view.item_description
        private val reporterName = view.item_reporter

        fun bind(newsData: NewsData) {
                title.text = newsData.title
                description.text = newsData.description
                reporterName.text = newsData.reporter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }
}