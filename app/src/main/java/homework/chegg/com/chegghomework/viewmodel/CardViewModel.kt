package homework.chegg.com.chegghomework.viewmodel

import android.app.Application
import androidx.lifecycle.*
import homework.chegg.com.chegghomework.model.Card
import homework.chegg.com.chegghomework.model.ItemB
import homework.chegg.com.chegghomework.model.a.ItemA
import homework.chegg.com.chegghomework.model.c.ItemC
import homework.chegg.com.chegghomework.repository.CardRepository
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : AndroidViewModel(application) {//todo - make custom view model factory

    var cardListLd: LiveData<MutableList<Card>> = MutableLiveData()

    private val TAG = this::class.java.simpleName

    private val cardRepository: CardRepository = CardRepository.getRepoInstance(application)

    init {

        viewModelScope.launch {

            cardListLd = liveData {
               emit(cardRepository.loadCards())
            }

        }

    }


}