package com.justice.shopmanagement.ui.news


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.justice.shopmanagement.R
import com.justice.shopmanagement.databinding.FragmentNewsBinding
import com.justice.shopmanagement.ui.news.models.Article
import com.justice.shopmanagement.utils.Resource
import com.justice.shopmanagement.utils.hide
import com.justice.shopmanagement.utils.show
import com.justice.shopmanagement.utils.showToastMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {
    lateinit var binding: FragmentNewsBinding
    lateinit var newsAdapter: NewsRecyclerAdapter
    private val viewModel: NewsViewModel by viewModels()
    private val TAG = "NewsFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        setHasOptionsMenu(true)
        setUpRecyclerView()
        subScribeToObservers()
        viewModel.setEvent(NewsViewModel.Event.GetAllArticles)


    }

    private fun subScribeToObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.getAllArticleStatus.collect {
                    Log.d(TAG, "subscribeToObservers: getAllNewsStatus:${it.status.name}")
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            binding.progressBar.show()
                        }
                        Resource.Status.SUCCESS -> {
                            binding.progressBar.hide()
                            Log.d(TAG, "subscribeToObservers: Success:${it.data}")
                            newsAdapter.submitList(it.data)
                        }
                        Resource.Status.ERROR -> {
                            binding.progressBar.hide()
                            it.exception?.message?.let {
                                showToastMessage(it)
                            }
                            Log.e(TAG, "subscribeToObservers: Error:${it.exception?.message}")


                        }
                    }
                }
            }


        }

    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            newsAdapter = NewsRecyclerAdapter(requireContext()) {
                onClick(it)
            }
            adapter = newsAdapter

        }


    }

    private fun onClick(article: Article) {
        Log.d(TAG, "onClick: article:$article")
        findNavController().navigate(
            NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(
                article
            )
        )
    }


}
