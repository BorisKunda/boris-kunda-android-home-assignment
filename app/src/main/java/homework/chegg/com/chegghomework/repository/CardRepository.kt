package homework.chegg.com.chegghomework.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import homework.chegg.com.chegghomework.api.ApiService
import homework.chegg.com.chegghomework.api.RetrofitBuilder
import homework.chegg.com.chegghomework.database.CardDao
import homework.chegg.com.chegghomework.database.CardDatabaseBuilder
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
    val cardDao: CardDao

    enum class SourceType {
        A, B, C
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    init {
        val db = CardDatabaseBuilder.getCardDatabase(application)
        cardDao = db.cardDao()
    }

    companion object {

        fun getRepoInstance(application: Application): CardRepository {

            val instance: CardRepository by lazy { CardRepository(application) }

            return instance

        }

    }

    suspend fun loadCards() {

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

            Log.i(TAG, "Sources loaded successfully \n $sourceA \n $sourceB \n SourceC$sourceC")

            /** Source -> List<Card> conversion */

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

            /** Multiple CardLists merge */

            val cardMListToMerge: MutableList<List<Card>> = mutableListOf(cardListFromA, cardListFromB, cardListFromC)

            val completeCardsList = cardMListToMerge.flatten().toMutableList()

            cardList = completeCardsList

            /** Inserting list to database for caching purposes */

            cardDao.insertAll(cardList)

        }

    }

}