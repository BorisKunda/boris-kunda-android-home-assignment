package homework.chegg.com.chegghomework.database

import android.app.Application
import androidx.room.Room
import homework.chegg.com.chegghomework.utils.Constants

object CardDatabaseBuilder {

    fun getCardDatabase(application: Application): CardDatabase {

        val cardDatabase: CardDatabase by lazy {
            Room.databaseBuilder(application, CardDatabase::class.java, Constants.DATABASE_NAME).build()
        }
        return cardDatabase
    }

}