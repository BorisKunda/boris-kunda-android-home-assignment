package homework.chegg.com.chegghomework.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import homework.chegg.com.chegghomework.utils.Constants

@Entity(tableName = Constants.CARDS_TABLE)
data class Card(
        @PrimaryKey
        @NonNull
        var title: String,
        var subtitle: String,
        var imageUrl: String)