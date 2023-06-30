package com.pdmpa.stockmarketapp.domain.model

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.pdmpa.stockmarketapp.R

sealed class CardInput(
    @StringRes val label: Int, val keyBoardType: KeyboardType,
    val imeAction: ImeAction
) {
    abstract var text: MutableState<String>
    abstract fun onValueChanged(newValue: String)

    object CardNumber : CardInput(
        label = R.string.card_number, keyBoardType = KeyboardType.Number,
        imeAction = ImeAction.Next
    ) {

        override var text: MutableState<String> = mutableStateOf("")

        override fun onValueChanged(newValue: String) {
            text.value = newValue
        }
    }

    object CardHolderName : CardInput(
        label = R.string.cardholder_name, keyBoardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ) {
        override var text: MutableState<String> = mutableStateOf("")

        override fun onValueChanged(newValue: String) {
            text.value = newValue
        }
    }

    object ValidUntil : CardInput(
        label = R.string.valid_until, keyBoardType = KeyboardType.Number,
        imeAction = ImeAction.Next
    ) {
        override var text: MutableState<String> = mutableStateOf("")

        override fun onValueChanged(newValue: String) {
            text.value = newValue
        }
    }

    object CVV : CardInput(
        label = R.string.cvv_number, keyBoardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    ) {
        override var text: MutableState<String> = mutableStateOf("")

        override fun onValueChanged(newValue: String) {
            text.value = newValue
        }
    }
}

data class Card(
    val number: String,
    val holderName: String,
    val balance: String,
    val validUntil: String,
    val cvv: String
)