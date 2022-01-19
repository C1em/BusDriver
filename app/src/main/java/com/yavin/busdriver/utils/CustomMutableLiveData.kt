package com.yavin.busdriver.utils

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData


class CustomMutableLiveData<T : BaseObservable?>() : MutableLiveData<T>() {

    constructor(value: T) : this() {

        setValue(value)
    }

    override fun setValue(value: T) {
        super.setValue(value)
        value!!.addOnPropertyChangedCallback(callback)
    }

    var callback: Observable.OnPropertyChangedCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

            //Trigger LiveData observer on change of any property in object
            value?.let { setValue(it) }
        }
    }
}