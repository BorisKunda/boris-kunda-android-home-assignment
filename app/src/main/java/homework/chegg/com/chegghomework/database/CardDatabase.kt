package homework.chegg.com.chegghomework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import homework.chegg.com.chegghomework.model.Card


@Database(entities = [Card::class], version = 1)
abstract class CardDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

}