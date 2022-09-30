package com.justice.shopmanagement.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.justice.shopmanagement.data.local.dao.ArticleDao
import com.justice.shopmanagement.ui.news.models.Article

@Database(
    entities = [Article::class],
    version = 2,
    exportSchema = false
)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao


    companion object {
        private var instance: ArticlesDatabase? = null
        @Synchronized
        fun getInstance(context: Context): ArticlesDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder<ArticlesDatabase>(
                    context.applicationContext,
                    ArticlesDatabase::class.java, "articles_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }


    }
}