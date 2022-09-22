package com.omar.mobiletest.ui.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.omar.mobiletest.R
import com.omar.mobiletest.ui.extensions.getFormattedValue
import com.omar.mobiletest.ui.extensions.obfuscateCreditFirstThreeDigits
import com.omar.mobiletest.ui.feature.cards.model.Card
import com.omar.mobiletest.ui.feature.cards.model.TypeCard
import com.omar.mobiletest.ui.feature.cards.model.TypeHolder
import com.omar.string_manager.StringManager
import com.omar.string_manager.StringReplacement

object GetCards {

    fun mockupCards(context: Context): List<Card> {

        return listOf(
            Card(
                TypeCard.CREDIT,
                "1234567",
                TypeHolder.TITULAR,
                "1234456778901234",
                "Omar Aldair Menor Pozo",
                "O2/23",
                "1500.00",
                "2500.00",
                "4000.00",
                ContextCompat.getDrawable(context, com.omar.ui_components.R.color.yellow),
                true
            ),
            Card(
                TypeCard.DEBIT,
                "78906786",
                TypeHolder.BENEFICIARY,
                "45678456778909856",
                "Omar Aldair Menor Pozo",
                "O5/25",
                "4000.00",
                "1000.00",
                "6000.00",
                ContextCompat.getDrawable(context, com.omar.ui_components.R.color.dark_blue),
                false
            ),
            Card(
                TypeCard.CREDIT,
                "38097567",
                TypeHolder.TITULAR,
                "9845456782451234",
                "Omar Aldair Menor Pozo",
                "O2/26",
                "2500.00",
                "3500.00",
                "6000.00",
                ContextCompat.getDrawable(context, R.color.black),
                true
            ),
            Card(
                TypeCard.CREDIT,
                "67890939",
                TypeHolder.TITULAR,
                "1234456778901234",
                "Omar Aldair Menor Pozo",
                "O9/22",
                "500.00",
                "600.00",
                "500.00",
                ContextCompat.getDrawable(context, R.drawable.gradient_card),
                false
            )
        )
    }
}