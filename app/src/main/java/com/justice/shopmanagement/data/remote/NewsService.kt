package com.justice.shopmanagement.data.remote

import com.justice.shopmanagement.ui.news.models.NewsResults
import retrofit2.Response
import retrofit2.http.*

interface NewsService {


    @GET("top-headlines")
    suspend fun getAllArticles( @QueryMap queries:HashMap<String,String>): Response<NewsResults>

}