package com.justice.shopmanagement.repository

import com.justice.shopmanagement.ui.news.models.NewsResults
import retrofit2.Response


interface NewsRepository {


    suspend fun getAllArticles(queries:HashMap<String,String>): Response<NewsResults>


}