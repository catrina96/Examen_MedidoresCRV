package com.example.examen_medidorescrv.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen_medidorescrv.data.BaseDatosApp
import com.example.examen_medidorescrv.data.Medicion
import com.example.examen_medidorescrv.data.MedicionDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar la lógica de negocio de las mediciones.
 * Se encarga de interactuar con el repositorio (DAO) y exponer los datos a la UI.
 *
 * @param aplicacion Instancia de la aplicación para acceder a la base de datos.
 */
class MedicionViewModel(aplicacion: Application) : AndroidViewModel(aplicacion) {
    private val medicionDao: MedicionDao = BaseDatosApp.obtenerBaseDatos(aplicacion).medicionDao()
    
    /**
     * Flujo que contiene la lista de todas las mediciones registradas.
     */
    val todasLasMediciones: Flow<List<Medicion>> = medicionDao.obtenerTodasLasMediciones()

    /**
     * Inserta una nueva medición en la base de datos de forma asíncrona.
     * @param medicion La medición a insertar.
     */
    fun insertar(medicion: Medicion) {
        viewModelScope.launch {
            medicionDao.insertarMedicion(medicion)
        }
    }
}
