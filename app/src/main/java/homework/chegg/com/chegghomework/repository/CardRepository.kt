package homework.chegg.com.chegghomework.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import homework.chegg.com.chegghomework.api.ApiService
import homework.chegg.com.chegghomework.api.RetrofitBuilder
import homework.chegg.com.chegghomework.model.Card
import homework.chegg.com.chegghomework.model.ItemB
import homework.chegg.com.chegghomework.model.a.ItemA
import homework.chegg.com.chegghomework.model.a.SourceA
import homework.chegg.com.chegghomework.model.b.SourceB
import homework.chegg.com.chegghomework.model.c.ItemC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class CardRepository private constructor(application: Application) {

    var cardListMld: MutableLiveData<Card> = MutableLiveData()
    private var aList: List<ItemA> = mutableListOf()
    private var bList: List<ItemB> = mutableListOf()
    private var cList: List<ItemC> = mutableListOf()
    private var sourceA: SourceA = SourceA(aList)
    private val apiService: ApiService = RetrofitBuilder.apiService
    private val TAG = this::class.java.simpleName

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

    suspend fun loadCards(): List<Card> {

        var sourceA: SourceA
        var sourceB: SourceB
        var sourceC: List<ItemC>
        var cardListFromA: List<Card> = mutableListOf()
        var cardListFromB: List<Card> = mutableListOf()
        var cardListFromC: List<Card> = mutableListOf()
        var completeCardsList: List<Card> = mutableListOf()


        coroutineScope {

            /**load sources*/

            sourceA = async(Dispatchers.IO) {
                apiService.getSourceA()
            }.await()

            sourceB = async(Dispatchers.IO) {
                apiService.getSourceB()
            }.await()

            sourceC = async(Dispatchers.IO) {
                apiService.getSourceC()
            }.await()

            /** Source -> List<Card> conversion */

            Log.i(TAG, "Sources loaded Successfully \n $sourceA \n $sourceB \n SourceC$sourceC")

            cardListFromA = sourceA.stories.map {
                Card(it.title, it.subtitle, it.imageUrl)
            }

            cardListFromB = sourceB.metadata.innerData.map {
                Card(it.articleWrapper.header, it.articleWrapper.description, it.imageUrl)
            }

            cardListFromC = sourceC.map {
                Card(it.topLine,
                        "${it.subLine1}${it.subLine2}",
                        it.imageUrl)
            }

            /** multiple cardLists merge */



        }

        return completeCardsList

    }

}