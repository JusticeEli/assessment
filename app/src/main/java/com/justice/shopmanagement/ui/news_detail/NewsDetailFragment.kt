package com.justice.shopmanagement.ui.news_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.justice.shopmanagement.R
import com.justice.shopmanagement.databinding.FragmentNewsDetailBinding


class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {
    lateinit var binding: FragmentNewsDetailBinding
    private val arguments: NewsDetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDetailBinding.bind(view)
        setUpArticle()

    }

    private fun setUpArticle() {
        val article = arguments.article
        binding.apply {
            tvTitle.text = article.title

            Glide.with(binding.root)
                .load(article.urlToImage)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageView)

            tvAuthor.text=article.author
            tvDescription.text=article.description


        }
    }
}