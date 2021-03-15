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

    suspend fun loadSources() {

        var a: Any
        var b: Any
        var c: Any

        coroutineScope {

            a = async(Dispatchers.IO) {
                apiService.getSourceA()
            }.await()

            b = async(Dispatchers.IO) {
                apiService.getSourceB()
            }.await()

            c = async(Dispatchers.IO) {
                apiService.getSourceC()
            }.await()



            Log.i(TAG, "Sources Loaded Successfully " +
                    "\n ***A*** $a" +
                    "\n ***B*** $b" +
                    "\n ***C*** $c"
            )

        }

    }

}