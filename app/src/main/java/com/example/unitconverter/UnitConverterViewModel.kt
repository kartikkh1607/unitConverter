
package com.example.unitconverter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.unitconverter.model.LengthUnit
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class UnitConverterViewModel : ViewModel() {

    private val _inputValue = mutableStateOf("")
    val inputValue: State<String> = _inputValue

    private val _fromUnit = mutableStateOf(LengthUnit.units[0])
    val fromUnit: State<LengthUnit> = _fromUnit

    private val _toUnit = mutableStateOf(LengthUnit.units[1])
    val toUnit: State<LengthUnit> = _toUnit

    private val _result = mutableStateOf("")
    val result: State<String> = _result

    fun onInputValueChange(newValue: String) {
        _inputValue.value = newValue
    }

    fun onFromUnitSelected(unit: LengthUnit) {
        _fromUnit.value = unit
    }

    fun onToUnitSelected(unit: LengthUnit) {
        _toUnit.value = unit
    }

    fun convert() {
        val input = inputValue.value.toDoubleOrNull()
        if (input != null) {
            val baseValue = input / fromUnit.value.factor
            val converted = baseValue * toUnit.value.factor
            _result.value = "%.4f ${toUnit.value.name}".format(converted)
        } else {
            _result.value = "Invalid input"
        }
    }
}