package com.justice.shopmanagement.di

import com.justice.shopmanagement.data.remote.NewsService
import com.justice.shopmanagement.repository.ArticleRepository
import com.justice.shopmanagement.repository.NewsRepository
import com.justice.shopmanagement.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {




    @Provides
    @Singleton
    fun provideNewsService(): NewsService {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(NewsService::class.java)
    }



    @Provides
    @Singleton
    fun provideNewsRepository(service: NewsService):NewsRepository{
        return  ArticleRepository(service)

    }
}
