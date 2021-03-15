package homework.chegg.com.chegghomework.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import homework.chegg.com.chegghomework.model.*
import homework.chegg.com.chegghomework.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : AndroidViewModel(application) {//todo - make custom view model factory

    var cardListLd: LiveData<List<Card>> = MutableLiveData()
    var aListLd: LiveData<List<ItemA>> = MutableLiveData()
    var bListLd: LiveData<List<ItemB>> = MutableLiveData()
    var cListLd: LiveData<List<ItemC>> = MutableLiveData()
    var sourceAMld: MutableLiveData<SourceA> = MutableLiveData()
    var sourceA1: SourceA? = null
    var sourceA2: SourceA? = null
    var sourceA3: SourceA? = null

    private val TAG = this::class.java.simpleName

    private val cardRepository: CardRepository = CardRepository.getRepoInstance(application)

    init {

        viewModelScope.launch {


            coroutineScope {

                async(Dispatchers.IO) {
                    //cardRepository.getSourceA()
                }.await()

                async(Dispatchers.IO) {
                    //cardRepository.getSourceB()
                }.await()

                async(Dispatchers.IO) {
                    //cardRepository.getSourceC()
                }.await()

                Log.i(TAG, "Sources Loaded Successfully")

            }

        }

    }

    //  fun getSourceA():LiveData<SourceA> = liveData(Dispatchers.IO) {
    //      emit(cardRepository.getSourceA())
    //  }

}