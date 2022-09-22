package com.omar.mobiletest.ui.extensions

import com.omar.mobiletest.ui.feature.cards.model.Card

fun Card.calculateProgress() =
    (((limitCredit.toDouble() - creditArranged.toDouble()) / limitCredit.toDouble()) * 100).toInt()
