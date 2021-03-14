package homework.chegg.com.chegghomework.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import homework.chegg.com.chegghomework.data.Card

class CardRepository private constructor(application: Application) {

    var cardsListMld: MutableLiveData<Card> = MutableLiveData()

    enum class Source {
        A, B, C
    }

    init {

    }

    companion object {

        fun getRepoInstance(application: Application): CardRepository {

            val instance: CardRepository by lazy { CardRepository(application) }

            return instance

        }

    }

}