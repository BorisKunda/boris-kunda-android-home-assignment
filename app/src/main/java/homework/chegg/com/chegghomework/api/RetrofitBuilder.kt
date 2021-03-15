package homework.chegg.com.chegghomework.api

import homework.chegg.com.chegghomework.utils.Consts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {



    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}