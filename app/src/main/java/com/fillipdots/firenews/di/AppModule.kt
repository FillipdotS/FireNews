package com.fillipdots.firenews.di

import android.content.Context
import com.fillipdots.firenews.R
import com.fillipdots.firenews.data.api.FakeNewsApiImpl
import com.fillipdots.firenews.data.api.NewsApi
import com.fillipdots.firenews.data.repository.NewsRepository
import com.fillipdots.firenews.data.repository.AccountRepository
import com.fillipdots.firenews.data.repository.TopicNewsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("NEWS_API_KEY")
    fun provideNewsApiKey(@ApplicationContext context: Context): String {
        return context.getString(R.string.news_api_key)
    }

    @Provides
    @Singleton
    fun provideNewsApi(@Named("NEWS_API_KEY") newsApiKey: String): FakeNewsApiImpl {
        return FakeNewsApiImpl(newsApiKey)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, @Named("NEWS_API_KEY") newsApiKey: String, topicNewsRepository: TopicNewsRepository): NewsRepository {
        return NewsRepository(newsApi, newsApiKey, topicNewsRepository)
    }

    @Provides
    @Singleton
    fun providesAccountService(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore): AccountRepository {
        return AccountRepository(firebaseAuth, firestore)
    }

    @Provides
    @Singleton
    fun providesTopicNewsRepository(accountRepository: AccountRepository): TopicNewsRepository {
        return TopicNewsRepository(accountRepository)
    }

    @Provides
    fun firebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun firebaseStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideNewsApiRetrofit(): NewsApi {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
}