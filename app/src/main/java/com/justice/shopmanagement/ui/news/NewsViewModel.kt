package com.justice.shopmanagement.ui.news


import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justice.shopmanagement.repository.NewsRepository
import com.justice.shopmanagement.ui.news.models.Article
import com.justice.shopmanagement.utils.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor( val repository: NewsRepository) :
    ViewModel() {
    private val TAG = "GoodsViewModel"

    private val _allArticles: MutableLiveData<List<Article>> = MutableLiveData()
    val allArticle = _allArticles as LiveData<List<Article>>

    private val _getAllArticlesStatus = Channel<Resource<List<Article>>>()
    val getAllArticleStatus = _getAllArticlesStatus.receiveAsFlow()

    fun setEvent(event: Event) {
        viewModelScope.launch {
            when (event) {

                is Event.GetAllArticles -> {
                    try {
                        _getAllArticlesStatus.send(Resource.loading(""))
                        getAllArticles()
                    } catch (e: Exception) {
                        Log.e(TAG, "setEvent: ", e)
                        _getAllArticlesStatus.send(Resource.error(e))
                    }
                }
            }
        }
    }



    private suspend fun getAllArticles() {
        Log.d(TAG, "getAll: in progress")
        var queries= hashMapOf<String,String>("apiKey" to "2dad3fe274fb4ef0a9fb5b13ba114f4c","country" to "us")
        val resp = repository.getAllArticles(queries)
        if (resp.isSuccessful) {
            Log.d(TAG, "getAll: resp:${resp.body()}")
            val body = resp.body()!!
            _allArticles.value = body.articles
            _getAllArticlesStatus.send(Resource.success(body.articles))
        } else {
            val error = resp.errorBody();
            Log.e(TAG, "getAll: ${error}")
            _getAllArticlesStatus.send(Resource.error(Exception(error.toString())))
        }
    }



    sealed class Event {
        object GetAllArticles : Event()
    }

}