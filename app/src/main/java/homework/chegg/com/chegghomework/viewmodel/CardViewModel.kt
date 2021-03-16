package homework.chegg.com.chegghomework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import homework.chegg.com.chegghomework.model.Card
import homework.chegg.com.chegghomework.repository.CardRepository
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : AndroidViewModel(application) {


    private val cardRepository: CardRepository
    val cardListLd: LiveData<List<Card>>

    init {
        cardRepository = CardRepository.getRepoInstance(application)
        cardListLd = cardRepository.cardDao.getCardsList()
    }

    fun refreshCardsList() {
        viewModelScope.launch { cardRepository.loadCards() }
    }

}