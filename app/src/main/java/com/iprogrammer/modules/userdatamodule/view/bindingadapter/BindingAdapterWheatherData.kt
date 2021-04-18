package com.iprogrammer.modules.loginmodule.view.bindingadapter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.iprogrammer.R
import com.iprogrammer.modules.userdatamodule.viewmodel.WheatherDataViewModel

object BindingAdapterWheatherData {

    @BindingAdapter("inputFieldsValidate")
    @JvmStatic
    fun isValidateDetails(
        inputView: EditText,
        mWheatherDataViewModel: WheatherDataViewModel
    ) {

        inputView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (mWheatherDataViewModel != null && inputView.id == R.id.txtCityName) {
                    mWheatherDataViewModel.cityName.set(s.toString())
                    if (!s.toString().isNullOrEmpty()) {
                        mWheatherDataViewModel.isEnable.set(true)


                    } else {
                        mWheatherDataViewModel.isEnable.set(false)
                    }
                }
            }
        })

    }
}