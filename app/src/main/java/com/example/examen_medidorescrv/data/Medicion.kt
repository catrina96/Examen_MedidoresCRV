package com.example.examen_medidorescrv.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa una medición de consumo (Agua, Luz o Gas).
 *
 * @property id Identificador único de la medición (autogenerado).
 * @property tipo El tipo de servicio medido (Agua, Luz, Gas).
 * @property valor El valor registrado en el medidor.
 * @property fecha La fecha en que se realizó la medición.
 */
@Entity(tableName = "mediciones")
data class Medicion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tipo: String,
    val valor: Int,
    val fecha: String
)
