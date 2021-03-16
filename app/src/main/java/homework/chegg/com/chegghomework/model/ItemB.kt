package homework.chegg.com.chegghomework.model

import com.google.gson.annotations.SerializedName
import homework.chegg.com.chegghomework.model.b.ArticleWrapper

data class ItemB(var articleId: String,
                 @SerializedName("articlewrapper")
                 var articleWrapper: ArticleWrapper,
                 @SerializedName("picture")
                 var imageUrl: String
)