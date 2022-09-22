package com.omar.mobiletest.ui.feature.cards.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omar.mobiletest.R
import com.omar.mobiletest.databinding.ItemCardBinding
import com.omar.mobiletest.ui.extensions.calculateProgress
import com.omar.mobiletest.ui.extensions.getFormattedValue
import com.omar.mobiletest.ui.extensions.obfuscateCreditFirstThreeDigits
import com.omar.mobiletest.ui.feature.cards.model.Card
import com.omar.string_manager.StringManager
import com.omar.string_manager.StringReplacement

class CardAdapter : RecyclerView.Adapter<CardViewHolder>() {

    var itemList: List<Card> = listOf()
    set(value) {
        updateList(value)
        field = value.map { it.copy() }.toList()
    }

    private lateinit var stringManager: StringManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        stringManager = StringManager(parent.context)
        return CardViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    private fun updateList(newList: List<Card>) {
        val diffResult = DiffUtil.calculateDiff(RecyclerDiffCallback(this.itemList, newList))
        diffResult.dispatchUpdatesTo(this)
    }
}

class CardViewHolder(private val binding: ItemCardBinding, context: Context) : RecyclerView.ViewHolder(binding.root) {

    private val stringManager = StringManager(context)
    private val stringReplacement = StringReplacement(StringReplacement.Placeholder.VALUE)

    fun bind(item: Card) {
        with(binding) {
            cardType.text = item.typeCard.type
            nameCredit.text =
                stringManager.getString(R.string.nombre_credito, stringReplacement.setReplacement(item.nameCredit.obfuscateCreditFirstThreeDigits()))
            limitCreditValue.text =
                stringManager.getString(R.string.limite_credito, stringReplacement.setReplacement(item.limitCredit.getFormattedValue()))
            customCard.cardHolder = item.nameHolder
            customCard.dateExpired = item.dateExpired
            customCard.numberCard = item.numCard
            customCard.srcBackground = item.srcBackground
            customCard.subtitleCard = stringManager.getString(R.string.titulo_tarjeta).toString()
            labelRepaid.textSubtitle =
                stringManager.getString(R.string.saldo_dispuesto, stringReplacement.setReplacement(item.creditArranged.getFormattedValue()))
            labelAvailable.textSubtitle =
                stringManager.getString(R.string.saldo_disponible, stringReplacement.setReplacement(item.creditAvailable.getFormattedValue()))
            labelTypeHolder.text = item.typeHolder.type
            footer.enableCard = item.enableCard
            progress.progress = item.calculateProgress()
        }
    }
}

class RecyclerDiffCallback(private val newItem: List<Card>, private val oldItem: List<Card>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItem.size

    override fun getNewListSize(): Int = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition].numCard == newItem[newItemPosition].numCard

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition] == newItem[newItemPosition]
}