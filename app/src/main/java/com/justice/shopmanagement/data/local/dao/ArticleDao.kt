package com.justice.shopmanagement.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.justice.shopmanagement.ui.news.models.Article

@Dao
interface ArticleDao {
    @Insert
    fun insert(article: Article)

    @Query("DELETE FROM articles")
    fun deleteAllArticles()

    @get:Query("SELECT * FROM articles")
    val getAllArticles: LiveData<List<Article?>?>?
}