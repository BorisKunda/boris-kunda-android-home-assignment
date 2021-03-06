package homework.chegg.com.chegghomework.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import homework.chegg.com.chegghomework.model.Card

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cardList: List<Card>)

    @Query("SELECT * from CARDS_TABLE")
    fun getCardsList(): LiveData<List<Card>>

}