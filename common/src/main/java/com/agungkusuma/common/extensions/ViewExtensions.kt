package com.agungkusuma.common.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(msg: String, length: Int = Snackbar.LENGTH_SHORT) {
    view?.let { Snackbar.make(it, msg, length).show() }
}

fun Fragment.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), msg, length).show()
}

fun Fragment.showToastLong(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
}