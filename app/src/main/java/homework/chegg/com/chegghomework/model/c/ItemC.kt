package homework.chegg.com.chegghomework.model.c

import com.google.gson.annotations.SerializedName

data class ItemC(var topLine: String,
                 var subLine1: String,
                 var subLine2: String,
                 @SerializedName("image")
                 var imageUrl: String)

