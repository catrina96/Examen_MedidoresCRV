package com.example.examen_medidorescrv.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examen_medidorescrv.R
import com.example.examen_medidorescrv.data.Medicion
import com.example.examen_medidorescrv.viewmodel.MedicionViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Pantalla que permite al usuario registrar una nueva medición.
 * Adaptada visualmente al diseño solicitado (Registro Medidor).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFormularioMedicion(
    viewModel: MedicionViewModel,
    alVolver: () -> Unit
) {
    var valorTexto by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf("Agua") }
    var fechaTexto by remember { mutableStateOf(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())) }

    val opcionesTipo = listOf("Agua", "Luz", "Gas")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        // Título: Registro Medidor
        Text(
            text = "Registro Medidor",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Campo: Medidor
        TextField(
            value = valorTexto,
            onValueChange = { if (it.all { char -> char.isDigit() }) valorTexto = it },
            label = { Text("Medidor") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFEBE3F0),
                focusedContainerColor = Color(0xFFEBE3F0)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo: Fecha
        TextField(
            value = fechaTexto,
            onValueChange = { fechaTexto = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFEBE3F0),
                focusedContainerColor = Color(0xFFEBE3F0)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Etiqueta: Medidor de:
        Text(
            text = "Medidor de:",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp
        )

        // Grupo de RadioButtons
        Column(
            modifier = Modifier
                .selectableGroup()
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            opcionesTipo.forEach { texto ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .selectable(
                            selected = (texto == tipoSeleccionado),
                            onClick = { tipoSeleccionado = texto },
                            role = androidx.compose.ui.semantics.Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (texto == tipoSeleccionado),
                        onClick = null // El click se maneja en el Row
                    )
                    Text(
                        text = texto,
                        modifier = Modifier.padding(start = 16.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón: Registrar medición
        Button(
            onClick = {
                if (valorTexto.isNotEmpty()) {
                    viewModel.insertar(
                        Medicion(
                            tipo = tipoSeleccionado,
                            valor = valorTexto.toInt(),
                            fecha = fechaTexto
                        )
                    )
                    alVolver()
                }
            },
            modifier = Modifier
                .width(220.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6750A4)
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Registrar medición", color = Color.White, fontSize = 16.sp)
        }
    }
}
