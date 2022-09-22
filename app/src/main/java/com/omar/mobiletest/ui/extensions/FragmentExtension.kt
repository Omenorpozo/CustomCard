package com.omar.mobiletest.ui.extensions

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.omar.mobiletest.ui.delegate.FragmentViewBindingDelegate

inline fun <reified T : ViewBinding> Fragment.viewBinding() = FragmentViewBindingDelegate(this, T::class.java)