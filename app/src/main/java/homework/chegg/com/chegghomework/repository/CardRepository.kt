package homework.chegg.com.chegghomework.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import homework.chegg.com.chegghomework.api.ApiService
import homework.chegg.com.chegghomework.api.RetrofitBuilder
import homework.chegg.com.chegghomework.model.*

class CardRepository private constructor(application: Application) {

    var cardListMld: MutableLiveData<Card> = MutableLiveData()
    private var aList: List<ItemA> = mutableListOf()
    private var bList: List<ItemB> = mutableListOf()
    private var cList: List<ItemC> = mutableListOf()
    private var sourceA: SourceA = SourceA(aList)
    private val apiService: ApiService = RetrofitBuilder.apiService

    enum class SourceType {
        A, B, C
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    init {

    }

    companion object {

        fun getRepoInstance(application: Application): CardRepository {

            val instance: CardRepository by lazy { CardRepository(application) }

            return instance

        }

    }


    suspend fun getSourceA(): SourceA = apiService.getSourceA()
    //suspend fun getSourceB(): SourceB = apiService.getSourceB()
    //suspend fun getSourceC(): SourceC = apiService.getSourceC()


}