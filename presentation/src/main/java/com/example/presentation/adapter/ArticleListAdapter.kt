package com.example.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Article
import com.example.presentation.databinding.ItemNewsBinding

class ArticleListAdapter(private val onClick: (id: Int, url: String) -> Unit) :
    ListAdapter<Article, NewsViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.onBind(item)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(
                oldItem: Article,
                newItem: Article
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Article,
                newItem: Article
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class NewsViewHolder(
    private var binding: ItemNewsBinding,
    private val itemClick: (Int, String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val TAG = "ArticleListAdapter"

    fun onBind(item: Article) {
        binding.item = item
        binding.layoutNews.setOnClickListener {
            Log.d(TAG, "onBind::url::${item.url}")
            itemClick.invoke(item.id, item.url)
        }
    }
}