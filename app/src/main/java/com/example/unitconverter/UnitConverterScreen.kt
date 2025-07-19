package com.example.unitconverter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitconverter.R
import com.example.unitconverter.ui.components.StyledTextField
import com.example.unitconverter.ui.components.UnitSelector
import com.example.unitconverter.viewmodel.UnitConverterViewModel

@Composable
fun UnitConverterScreen(viewModel: UnitConverterViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.unit_converter_title),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        StyledTextField(
            value = viewModel.inputValue.value,
            onValueChange = {
                viewModel.onInputValueChange(it)
                viewModel.convert()
            },
            label = stringResource(R.string.enter_value_label)
        )

        UnitSelector(
            selectedUnit = viewModel.fromUnit.value,
            onUnitSelected = {
                viewModel.onFromUnitSelected(it)
                viewModel.convert()
            },
            label = stringResource(R.string.from_unit_label)
        )

        UnitSelector(
            selectedUnit = viewModel.toUnit.value,
            onUnitSelected = {
                viewModel.onToUnitSelected(it)
                viewModel.convert()
            },
            label = stringResource(R.string.to_unit_label)
        )

        Text(
            text = stringResource(R.string.result_label, viewModel.result.value),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

