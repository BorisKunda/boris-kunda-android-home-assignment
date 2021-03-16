package homework.chegg.com.chegghomework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import homework.chegg.com.chegghomework.model.*
import homework.chegg.com.chegghomework.model.a.ItemA
import homework.chegg.com.chegghomework.model.c.ItemC
import homework.chegg.com.chegghomework.repository.CardRepository
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : AndroidViewModel(application) {//todo - make custom view model factory

    var cardListLd: LiveData<List<Card>> = MutableLiveData()
    var aListLd: LiveData<List<ItemA>> = MutableLiveData()
    var bListLd: LiveData<List<ItemB>> = MutableLiveData()
    var cListLd: LiveData<List<ItemC>> = MutableLiveData()

    private val TAG = this::class.java.simpleName

    private val cardRepository: CardRepository = CardRepository.getRepoInstance(application)

    init {

        viewModelScope.launch {

            cardRepository.loadCards()

        }

    }

    //  fun getSourceA():LiveData<SourceA> = liveData(Dispatchers.IO) {
    //      emit(cardRepository.getSourceA())
    //  }

}