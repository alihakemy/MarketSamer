package com.hakemy.marketsamer.utils.services

import com.hakemy.marketsamer.utils.SharePreferenceManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    //Adjust Request timeout
    private val client: Retrofit?
        private get() {
            if (retrofit == null) {
                //Adjust Request timeout
                okHttpClient =OkHttpClient.Builder()

                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(Interceptor { chain ->
                        val newRequest: Request = chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/json")
                            .addHeader("lang", SharePreferenceManager.getLang().toString())

                            .addHeader("Accept-Language", SharePreferenceManager.getLang().toString())
                            .addHeader("Authorization", "Bearer ${SharePreferenceManager.getToken()}")
                            .build()
                         chain.proceed(newRequest)
                    })

                    .build()
                retrofit = Retrofit.Builder()

                    .baseUrl("https://sharwa.masharia.co/api/")

                    .client(okHttpClient)


                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    fun  servicesApi(): ApiService {
        return client!!.create(ApiService::class.java)

    }

}