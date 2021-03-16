package homework.chegg.com.chegghomework.repository

import android.app.Application
import android.util.Log
import homework.chegg.com.chegghomework.api.ApiService
import homework.chegg.com.chegghomework.api.RetrofitBuilder
import homework.chegg.com.chegghomework.model.Card
import homework.chegg.com.chegghomework.model.a.SourceA
import homework.chegg.com.chegghomework.model.b.SourceB
import homework.chegg.com.chegghomework.model.c.ItemC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class CardRepository private constructor(application: Application) {

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

    suspend fun loadCards(): MutableList<Card> {

        var sourceA: SourceA
        var sourceB: SourceB
        var sourceC: MutableList<ItemC>
        var cardList: MutableList<Card> = mutableListOf()

        coroutineScope {

            /**Load sources*/

            sourceA = async(Dispatchers.IO) {
                apiService.getSourceA()
            }.await()

            sourceB = async(Dispatchers.IO) {
                apiService.getSourceB()
            }.await()

            sourceC = async(Dispatchers.IO) {
                apiService.getSourceC()
            }.await()

            Log.i(TAG, "Sources Loaded Successfully \n $sourceA \n $sourceB \n SourceC$sourceC")

            /** Source -> List<Card> Conversion */

            val cardListFromA = sourceA.stories.map {
                Card(it.title, it.subtitle, it.imageUrl)
            }.toMutableList()

            val cardListFromB = sourceB.metadata.innerData.map {
                Card(it.articleWrapper.header, it.articleWrapper.description, it.imageUrl)
            }.toMutableList()

            val cardListFromC = sourceC.map {
                Card(it.topLine,
                        "${if (it.subLine1 != null) it.subLine1 else ""}${if (it.subLine2 != null) it.subLine2 else ""}",
                        it.imageUrl)
            }.toMutableList()

            /** Multiple CardLists Merge */

            val cardMListToMerge: MutableList<List<Card>> = mutableListOf(cardListFromA, cardListFromB, cardListFromC)

            val completeCardsList = cardMListToMerge.flatten().toMutableList()

            cardList = completeCardsList

        }

        return cardList

    }

}