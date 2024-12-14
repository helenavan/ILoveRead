package com.toulousehvl.iloveread.data.di

import com.google.firebase.auth.FirebaseAuth
import com.toulousehvl.iloveread.data.repository.remote.service.BookApiService
import com.toulousehvl.iloveread.data.repository.remote.service.BookImpl
import com.toulousehvl.iloveread.data.repository.remote.service.UserImpl
import com.toulousehvl.iloveread.domain.repository.BookRepository
import com.toulousehvl.iloveread.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module that provides all dependencies from the repository package.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideBookRepository(impl: BookImpl): BookRepository = impl

    @Provides
    fun provideBaseUrl(): String = "https://www.googleapis.com/books/v1/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): BookApiService =
        retrofit.create(BookApiService::class.java)

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        auth: FirebaseAuth
    ): UserRepository = UserImpl(auth)

    //TODO for Firestore

}