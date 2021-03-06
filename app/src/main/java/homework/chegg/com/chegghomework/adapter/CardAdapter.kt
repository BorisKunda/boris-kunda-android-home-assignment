package homework.chegg.com.chegghomework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import homework.chegg.com.chegghomework.R
import homework.chegg.com.chegghomework.model.Card

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    var cardMutableList: MutableList<Card> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardMutableList[position]
        holder.apply {
            titleTv.text = card.title
            subtitleTv.text = card.subtitle
            Glide.with(pictureIv.context).load(card.imageUrl).placeholder(R.drawable.ic_picture_place_holder).into(pictureIv)
        }
    }

    override fun getItemCount(): Int = cardMutableList.size


    //ViewHolder
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.textView_card_item_title)
        val subtitleTv: TextView = itemView.findViewById(R.id.textView_card_item_subtitle)
        val pictureIv: ImageView = itemView.findViewById(R.id.imageView_card_item)
    }

}