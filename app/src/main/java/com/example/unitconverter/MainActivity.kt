package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.nio.file.WatchEvent
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                UnitConverter()
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var InputValue by remember { mutableStateOf("") }
    var OutputValue by remember { mutableStateOf("") }
    var InputUnit by remember { mutableStateOf("Meters") }
    var OutputUnit by remember { mutableStateOf("Meters") }
    var iexpanded by remember { mutableStateOf(false) }
    var oexpanded by remember { mutableStateOf(false) }
    val ConversionFactor = remember { mutableStateOf(1.0) }
    val OutputConversionFactor = remember { mutableStateOf(1.0) }


    fun UnitConvert()
    {
        val InputValueDouble = InputValue.toDoubleOrNull() ?: 0.0   // ?: 0.0 is elvis operator or in c++ it is ternary operator
        val result = (InputValueDouble * ConversionFactor.value * 100 / OutputConversionFactor.value ).roundToInt() / 100
        OutputValue = result.toString()

    }


       // Here all the UI elements stacked below each other (column wise)
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
        )

        {
        Text("Unit Converter" , style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(InputValue , onValueChange = {
            InputValue = it
            UnitConvert()
        // here goes what should happen when the value changes , when the OutlinedTextField value changes
        } , label = {Text("Enter Value")})
            Spacer(modifier = Modifier.height(16.dp))

        Row {
       // Hera all the UI elements comes next to each other (row wise)
            Box()
            // Input Box
            {
                // Input Button
                 Button(onClick = {
                     iexpanded = true
                     oexpanded = false
                 } ) {
                     Text(InputUnit)
                     Icon(Icons.Default.ArrowDropDown , contentDescription = "Arrow Down")
                 }

            DropdownMenu(expanded = iexpanded , onDismissRequest = {iexpanded = false}) {
                DropdownMenuItem(
                    text = { Text("Centimeters") } ,
                    onClick =  {
                        iexpanded = false
                        InputUnit = "Centimeters"
                        ConversionFactor.value = 0.01
                        UnitConvert()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Millimeters") } ,
                    onClick =  {
                        iexpanded = false
                        InputUnit = "Millimeters"
                        ConversionFactor.value = 0.001
                        UnitConvert()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Meters") } ,
                    onClick =  {
                        iexpanded = false
                        InputUnit = "Meters"
                        ConversionFactor.value = 1.0
                        UnitConvert()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Feet") } ,
                    onClick =  {
                        iexpanded = false
                        InputUnit = "Feet"
                        ConversionFactor.value = 0.3048
                        UnitConvert()
                    }
                )
            }
        }

            Spacer(modifier = Modifier.width(16.dp))

            Box()
            // Output Box
            {
                // Output Button
                Button(onClick = {
                    oexpanded = true
                    iexpanded = false
                }
                ) {
                    Text(OutputUnit)
                    Icon(Icons.Default.ArrowDropDown , contentDescription = "Arrow Down")
                }

                DropdownMenu(expanded = oexpanded , onDismissRequest = {oexpanded = false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") } ,
                        onClick =  {
                            oexpanded = false
                            OutputUnit = "Centimeters"
                            OutputConversionFactor.value = 0.01
                            UnitConvert()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") } ,
                        onClick =  {
                            oexpanded = false
                            OutputUnit = "Millimeters"
                            OutputConversionFactor.value = 0.001
                            UnitConvert()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Decimeters") } ,
                        onClick =  {
                            oexpanded = false
                            OutputUnit = "Decimeters"
                            OutputConversionFactor.value = 1.0
                            UnitConvert()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("feet") } ,
                        onClick =  {
                            oexpanded = false
                            OutputUnit = "feet"
                            OutputConversionFactor.value = 0.3048
                            UnitConvert()
                        }
                    )

                }
            }
        }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Result: $OutputValue $OutputUnit",
                style = MaterialTheme.typography.headlineMedium
            )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview()
{
    UnitConverter()
}