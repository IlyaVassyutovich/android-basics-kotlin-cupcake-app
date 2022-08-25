package com.example.cupcake.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData(0)
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData("")
    val flavor: LiveData<String> = _flavor

    private val _pickupDate = MutableLiveData("")
    val pickupDate: LiveData<String> = _pickupDate

    private val _price = MutableLiveData(0.0)
    val price: LiveData<Double> = _price

    fun hasFlavorSet(): Boolean {
        return !_flavor.value.isNullOrBlank()
    }

    override fun toString(): String {
        return "OrderViewModel(" +
                "quantity=${quantity.value}, " +
                "flavor=${flavor.value}, " +
                "pickupDate=${pickupDate.value}, " +
                "price=${price.value})"
    }

    fun setFlavor(newFlavor: String) {
        _flavor.value = newFlavor
        Log.d("OrderViewModel", "set flavor to '$newFlavor'")
    }

    fun setQuantity(newQuantity: Int) {
        _quantity.value = newQuantity
        Log.d("OrderViewModel", "set quantity to '$newQuantity'")
    }
}