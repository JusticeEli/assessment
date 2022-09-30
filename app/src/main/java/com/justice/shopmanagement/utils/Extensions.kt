package com.justice.shopmanagement.utils

import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment



fun ProgressBar.show(){this.isVisible=true}
fun ProgressBar.hide(){this.isVisible=false}

 fun Fragment.showToastMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}