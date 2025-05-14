package com.example.mobilevan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilevan.enums.Periodo
import com.example.mobilevan.ui.theme.AzulVann
import com.example.mobilevan.ui.theme.CinzaVann

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun <T>ComboBox(
    options: List<T>,
    selectedOption: MutableState<T>,
    optionToString: (T) -> String
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedOption.value?.let(optionToString) ?: "",
            onValueChange = {  },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            readOnly = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = CinzaVann,
                focusedBorderColor = AzulVann,
                unfocusedBorderColor = AzulVann,
                focusedLabelColor = AzulVann,
                unfocusedLabelColor = AzulVann,
                focusedTextColor = AzulVann,
                unfocusedTextColor = AzulVann,
                cursorColor = AzulVann
            ),
            textStyle = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .background(color = CinzaVann),
        )
        ExposedDropdownMenu(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(optionToString(option)) },
                    onClick = {
                        selectedOption.value = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun ComboPrev() {
    val selectedOption = remember { mutableStateOf<Periodo?>(null) }
    ComboBox(
        options = Periodo.values().toList(),
        selectedOption = selectedOption,
        optionToString = { it?.getDescricao() ?: "" }
    )
}