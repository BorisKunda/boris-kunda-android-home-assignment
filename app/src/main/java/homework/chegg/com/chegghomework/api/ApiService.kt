package homework.chegg.com.chegghomework.api

import homework.chegg.com.chegghomework.model.a.SourceA
import homework.chegg.com.chegghomework.model.b.SourceB
import homework.chegg.com.chegghomework.model.c.ItemC
import homework.chegg.com.chegghomework.utils.Consts
import retrofit2.http.GET

interface ApiService {

    @GET(Consts.DATA_SOURCE_A_ENDPOINT)
    suspend fun getSourceA(): SourceA

    @GET(Consts.DATA_SOURCE_B_ENDPOINT)
    suspend fun getSourceB(): SourceB

    @GET(Consts.DATA_SOURCE_C_ENDPOINT)
    suspend fun getSourceC(): MutableList<ItemC>

}