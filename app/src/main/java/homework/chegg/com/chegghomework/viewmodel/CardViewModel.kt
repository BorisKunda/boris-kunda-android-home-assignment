package homework.chegg.com.chegghomework.viewmodel

import android.app.Application
import androidx.lifecycle.*
import homework.chegg.com.chegghomework.model.*
import homework.chegg.com.chegghomework.repository.CardRepository
import homework.chegg.com.chegghomework.utils.SingleLiveEvent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : AndroidViewModel(application) {//todo - make custom view model factory

    var cardListLd: LiveData<List<Card>> = MutableLiveData()
    var aListLd: LiveData<List<ItemA>> = MutableLiveData()
    var bListLd: LiveData<List<ItemB>> = MutableLiveData()
    var cListLd: LiveData<List<ItemC>> = MutableLiveData()
    private val cardRepository: CardRepository = CardRepository.getRepoInstance(application)

    init {



    }

    fun getSourceA():LiveData<SourceA> = liveData(Dispatchers.IO) {
        emit(cardRepository.getSourceA())
    }

}