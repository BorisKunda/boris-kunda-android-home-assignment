package homework.chegg.com.chegghomework.model.b

import com.google.gson.annotations.SerializedName

data class Metadata(@SerializedName("this")
                    var unimportant: String,
                    @SerializedName("innerdata")
                    var innerData: MutableList<ItemB>
)