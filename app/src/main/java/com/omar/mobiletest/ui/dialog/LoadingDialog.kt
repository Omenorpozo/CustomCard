package com.omar.mobiletest.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.omar.mobiletest.R
import com.omar.mobiletest.ui.extensions.setFullScreenMode
import com.omar.mobiletest.ui.extensions.setTransparentBackground

class LoadingDialog : DialogFragment(R.layout.loading_dialog) {

    companion object {
        @JvmStatic
        fun newInstance() = run {
            val dialog = LoadingDialog()
            dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFullScreenMode()
        setTransparentBackground()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}