package com.justice.shopmanagement.repository

import com.justice.shopmanagement.data.remote.NewsService
import com.justice.shopmanagement.ui.news.models.NewsResults
import retrofit2.Response



class ArticleRepository constructor( private val service: NewsService):NewsRepository {



    override suspend fun getAllArticles(queries:HashMap<String,String>): Response<NewsResults> =service.getAllArticles(queries)



}