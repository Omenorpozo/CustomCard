package com.omar.mobiletest.ui.feature.cards.model

import android.graphics.drawable.Drawable

data class Card(
    val typeCard: TypeCard,
    val nameCredit: String,
    val typeHolder: TypeHolder,
    val numCard: String,
    val nameHolder: String,
    val dateExpired: String,
    val creditArranged: String,
    val creditAvailable: String,
    val limitCredit: String,
    val srcBackground: Drawable?,
    val enableCard: Boolean
)


