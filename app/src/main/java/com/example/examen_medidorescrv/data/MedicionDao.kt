package com.example.examen_medidorescrv.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz de Acceso a Datos (DAO) para la entidad Medicion.
 * Define las operaciones que se pueden realizar sobre la tabla 'mediciones'.
 */
@Dao
interface MedicionDao {
    /**
     * Obtiene todas las mediciones almacenadas, ordenadas por fecha de forma descendente.
     * @return Un flujo (Flow) con la lista de mediciones.
     */
    @Query("SELECT * FROM mediciones ORDER BY fecha DESC")
    fun obtenerTodasLasMediciones(): Flow<List<Medicion>>

    /**
     * Inserta una nueva medición en la base de datos.
     * @param medicion La instancia de Medicion a guardar.
     */
    @Insert
    suspend fun insertarMedicion(medicion: Medicion)
}
