package com.example.cupcake.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.0
private const val SAME_DAY_DELIVERY_PRICE = 3.0

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _pickupDate = MutableLiveData<String>()
    val pickupDate: LiveData<String> = _pickupDate

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    val pickupDateOptions: List<String>

    init {
        resetOrder()
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
    }

    fun setQuantity(newQuantity: Int) {
        _quantity.value = newQuantity
        updatePrice()
    }

    private fun updatePrice() {
        var newPrice = quantity.value!! * PRICE_PER_CUPCAKE
        if (pickupDate.value == pickupDateOptions[0])
            newPrice += SAME_DAY_DELIVERY_PRICE
        _price.value = newPrice
    }

    fun setPickupDate(newPickupDate: String) {
        _pickupDate.value = newPickupDate
        updatePrice()
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _pickupDate.value = ""
        _price.value = 0.0
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