package homework.chegg.com.chegghomework.api

import homework.chegg.com.chegghomework.model.a.SourceA
import homework.chegg.com.chegghomework.model.b.SourceB
import homework.chegg.com.chegghomework.model.c.ItemC
import homework.chegg.com.chegghomework.utils.Constants
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.DATA_SOURCE_A_ENDPOINT)
    suspend fun getSourceA(): SourceA

    @GET(Constants.DATA_SOURCE_B_ENDPOINT)
    suspend fun getSourceB(): SourceB

    @GET(Constants.DATA_SOURCE_C_ENDPOINT)
    suspend fun getSourceC(): MutableList<ItemC>

}