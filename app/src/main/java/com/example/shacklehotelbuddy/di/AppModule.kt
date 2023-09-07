package com.example.shacklehotelbuddy.di

import android.content.Context
import com.example.shacklehotelbuddy.BASE_URL
import com.example.shacklehotelbuddy.network.ApiService
import com.example.shacklehotelbuddy.repo.FakeHotelsRepository
import com.example.shacklehotelbuddy.room.SearchHistoryDao
import com.example.shacklehotelbuddy.room.SearchHistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Module
    @InstallIn(SingletonComponent::class)
    class AppModules {

        @Provides
        fun provideRetrofit(): Retrofit {
            val client = OkHttpClient
                .Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        @Provides
        fun provideApiService(
            retrofit: Retrofit
        ): ApiService = retrofit.create(ApiService::class.java)


        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext appContext: Context): SearchHistoryDatabase {
            return SearchHistoryDatabase.getInstance(context = appContext)
        }

        @Provides
        fun provideSearchHistoryDao(appDatabase: SearchHistoryDatabase): SearchHistoryDao {
            return appDatabase.searchQueryDao()
        }


        @Provides
        fun provideHotelsRepository(
            apiService: ApiService,
            searchHistoryDatabase: SearchHistoryDatabase
        ): FakeHotelsRepository = FakeHotelsRepository(
            apiService = apiService,
            searchHistoryDatabase.searchQueryDao()
        )
    }
}