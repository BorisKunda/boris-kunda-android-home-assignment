package homework.chegg.com.chegghomework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import homework.chegg.com.chegghomework.R
import homework.chegg.com.chegghomework.data.Card

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    var cardsMutableList: MutableList<Card> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardsMutableList[position]
        holder.apply {
            titleTv.text = card.title
            subTitleTv.text = card.subTitle
        }
    }

    override fun getItemCount(): Int = cardsMutableList.size


    //ViewHolder
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv = itemView.findViewById<TextView>(R.id.textView_card_item_title)
        val subTitleTv = itemView.findViewById<TextView>(R.id.textView_card_item_subtitle)
        //todo image
    }

}