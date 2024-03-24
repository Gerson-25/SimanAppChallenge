package com.example.simanappchallenge.data.di

import com.example.simanappchallenge.BuildConfig
import com.example.simanappchallenge.data.network.MarvelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url

                val timestamp = (System.currentTimeMillis() / 1000).toString()
                val md = MessageDigest.getInstance("MD5")
                val input = "$timestamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
                val md5 = BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
                    .addQueryParameter("ts", timestamp)
                    .addQueryParameter("hash", md5)
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()

    }

    @Provides
    @Singleton
    fun provideMarvelApi(client: OkHttpClient): MarvelApi {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }

}