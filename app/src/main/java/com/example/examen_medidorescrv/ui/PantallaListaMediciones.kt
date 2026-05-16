package com.example.examen_medidorescrv.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Propane
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examen_medidorescrv.data.Medicion
import com.example.examen_medidorescrv.viewmodel.MedicionViewModel

/**
 * Pantalla que muestra la lista de mediciones registradas.
 * Adaptada visualmente al diseño solicitado (Lista con separadores).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaListaMediciones(
    viewModel: MedicionViewModel,
    alNavegarAlFormulario: () -> Unit
) {
    val mediciones by viewModel.todasLasMediciones.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = alNavegarAlFormulario,
                containerColor = Color(0xFFE8DEF8),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(mediciones) { medicion ->
                ElementoMedicion(medicion)
                HorizontalDivider(thickness = 0.5.dp, color = Color.Gray.copy(alpha = 0.5f))
            }
        }
    }
}

/**
 * Componente que muestra una única medición en la lista, según el diseño de la foto.
 */
@Composable
fun ElementoMedicion(medicion: Medicion) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono según tipo
        val icono = when (medicion.tipo.lowercase()) {
            "agua" -> Icons.Default.Opacity
            "luz" -> Icons.Default.Lightbulb
            "gas" -> Icons.Default.Propane
            else -> Icons.Default.Opacity
        }

        Icon(
            imageVector = icono,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Nombre del tipo (AGUA, LUZ, GAS)
        Text(
            text = medicion.tipo.uppercase(),
            modifier = Modifier.width(80.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        // Valor con formato (ej. 1.900)
        Text(
            text = formatarValor(medicion.valor),
            modifier = Modifier.weight(1f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            fontSize = 14.sp
        )

        // Fecha
        Text(
            text = medicion.fecha,
            fontSize = 14.sp
        )
    }
}

/**
 * Formatea el valor entero con puntos como separadores de miles.
 */
private fun formatarValor(valor: Int): String {
    return java.text.NumberFormat.getInstance(java.util.Locale("es", "CL")).format(valor)
}
