package com.example.unitconverter.model

data class LengthUnit(val name: String, val factor: Double) {
    companion object {
        val units = listOf(
            LengthUnit("Meters", 1.0),
            LengthUnit("Kilometers", 0.001),
            LengthUnit("Centimeters", 100.0),
            LengthUnit("Millimeters", 1000.0),
            LengthUnit("Feet", 3.28084),
            LengthUnit("Inches", 39.3701),
            LengthUnit("Miles", 0.000621371)
        )
    }
}
