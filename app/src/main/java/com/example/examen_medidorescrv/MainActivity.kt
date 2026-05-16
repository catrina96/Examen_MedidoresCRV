package com.example.examen_medidorescrv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examen_medidorescrv.ui.PantallaFormularioMedicion
import com.example.examen_medidorescrv.ui.PantallaListaMediciones
import com.example.examen_medidorescrv.ui.theme.Examen_MedidoresCRVTheme
import com.example.examen_medidorescrv.viewmodel.MedicionViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen_MedidoresCRVTheme {
                NavegacionApp()
            }
        }
    }
}

@Composable
fun NavegacionApp() {
    val controladorNavegacion = rememberNavController()
    val viewModel: MedicionViewModel = viewModel()

    NavHost(navController = controladorNavegacion, startDestination = "lista") {
        composable("lista") {
            PantallaListaMediciones(
                viewModel = viewModel,
                alNavegarAlFormulario = { controladorNavegacion.navigate("formulario") }
            )
        }
        composable("formulario") {
            PantallaFormularioMedicion(
                viewModel = viewModel,
                alVolver = { controladorNavegacion.popBackStack() }
            )
        }
    }
}
