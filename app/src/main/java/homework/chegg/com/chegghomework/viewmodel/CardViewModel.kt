package homework.chegg.com.chegghomework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import homework.chegg.com.chegghomework.data.Card

class CardViewModel(application: Application) : AndroidViewModel(application) {

    var cardsListLd: LiveData<Card> = MutableLiveData()

    init {

    }

}