package com.example.cupcake.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _pickupDate = MutableLiveData<String>()
    val pickupDate: LiveData<String> = _pickupDate

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    val pickupDateOptions: List<String>

    init {
        resetData()
        pickupDateOptions = generatePickupDateOptions()
    }

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

    fun setPickupDate(newPickupDate: String) {
        _pickupDate.value = newPickupDate
        Log.d("OrderViewModel", "set pickup date to '$newPickupDate'")
    }

    private fun resetData() {
        _quantity.value = 0
        _flavor.value = ""
        _pickupDate.value = ""
        _price.value = 0.0
        Log.d("OrderViewModel", "reset order data")
    }

    private fun generatePickupDateOptions(): List<String> {
        val pickupDateOptions = mutableListOf<String>()

        val dateFormat = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance(Locale.getDefault())
        repeat(4) {
            pickupDateOptions.add(dateFormat.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }

        return pickupDateOptions
    }
}