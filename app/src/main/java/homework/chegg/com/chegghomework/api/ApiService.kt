package homework.chegg.com.chegghomework.api

import homework.chegg.com.chegghomework.model.ItemA
import homework.chegg.com.chegghomework.model.SourceA
import homework.chegg.com.chegghomework.utils.Consts
import retrofit2.http.GET

interface ApiService {

    @GET(Consts.DATA_SOURCE_A_ENDPOINT)
    suspend fun getSourceA():SourceA

}