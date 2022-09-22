package com.omar.mobiletest.ui.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun DialogFragment.setFullScreenMode() {
    dialog?.window?.apply {
        requestFeature(DialogFragment.STYLE_NO_TITLE)
        decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        statusBarColor = Color.TRANSPARENT
    }
}

fun DialogFragment.setTransparentBackground() {
    dialog?.window?.apply { setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) }
}

fun DialogFragment.show(fragmentManager: FragmentManager) {
    this.show(fragmentManager, this::class.java.canonicalName)
}