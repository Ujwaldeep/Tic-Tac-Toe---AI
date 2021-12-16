package com.example.ga_andr_001

import com.google.gson.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {
    companion object {
        private const val Base_URL = "https://jsonplaceholder.typicode.com/users"
        private val retrofit:Retrofit
        private val gson: Gson

           if(retrofit==null)
        {
            gson = GsonBuilder().setLenient().create()

           retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit;
        }


}


