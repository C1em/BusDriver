package com.yavin.busdriver.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.yavin.busdriver.BR

class Basket: BaseObservable() {

    // store total as integer by making 12.34 € into 1234 €
    private var total: Int = 0

    fun getTotalString() : String {

        return "${(total / 100)}.${(total % 100)}"
    }

    @Bindable
    fun getTotal(): Int {

        return total
    }

    fun addItem(itemPrice: Int) {

        total += itemPrice
        notifyPropertyChanged(BR.total)
    }

    fun empty() {

        total = 0
        notifyPropertyChanged(BR.total)
    }
}