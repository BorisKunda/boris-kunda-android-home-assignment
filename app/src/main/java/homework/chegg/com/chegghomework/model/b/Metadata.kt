package homework.chegg.com.chegghomework.model.b

import com.google.gson.annotations.SerializedName
import homework.chegg.com.chegghomework.model.ItemB

data class Metadata(@SerializedName("this")
                    var unimportant: String,
                    @SerializedName("innerdata")
                    var innerData: List<ItemB>
)