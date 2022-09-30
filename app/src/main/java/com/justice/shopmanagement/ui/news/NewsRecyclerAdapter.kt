package com.justice.shopmanagement.ui.news

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.justice.shopmanagement.R
import com.justice.shopmanagement.databinding.ItemNewsBinding
import com.justice.shopmanagement.ui.news.models.Article

class NewsRecyclerAdapter(val context: Context,val onClick:(article: Article)->Unit) : ListAdapter<Article,NewsViewHolder>(DIFF_CALLBACK) {
    private val TAG = "GoodsFragmentRecyclerAd"
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(ItemNewsBinding.bind(view))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, @SuppressLint("RecyclerView") position: Int) {

val article=getItem(position)!!
        Glide.with(holder.binding.root)
            .load(article.urlToImage)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.imageView)

         holder.binding.tvAuthor.text = article.author
      //  holder.binding.tvTitle.text = article.title



        holder.binding.root.setOnClickListener {
            Log.d(TAG, "onBindViewHolder: onclick")
            onClick(getItem(position))
        }

    }




    }

     class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)


        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Article> =
            object : DiffUtil.ItemCallback<Article>() {
                override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                    return oldItem == newItem
                }
            }



