package com.wwwkr.baseproject_cleanarchitecture.view.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wwwkr.baseproject_cleanarchitecture.databinding.ItemNewsBinding
import com.wwwkr.domain.model.ArticleData


class NewsRecyclerViewAdapter : ListAdapter<ArticleData, NewsRecyclerViewAdapter.ViewHolder>(object : DiffUtil.ItemCallback<ArticleData>() {


    override fun areItemsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {
        return oldItem.url == newItem.url && oldItem.content == newItem.content
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),parent.context
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun opentLink(context : Context, url : String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
    inner class ViewHolder(private val binding: ItemNewsBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArticleData) {
            binding.item = item

            Glide.with(context)
                .load(item.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivNews)

            binding.root.setOnClickListener {
                opentLink(context = context, item.url.toString())
            }

        }
    }


}