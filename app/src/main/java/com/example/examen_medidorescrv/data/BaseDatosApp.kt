package com.example.examen_medidorescrv.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Base de datos principal de la aplicación utilizando Room.
 * Define las entidades y proporciona acceso a los DAOs.
 */
@Database(entities = [Medicion::class], version = 1, exportSchema = false)
abstract class BaseDatosApp : RoomDatabase() {
    /**
     * Proporciona acceso al DAO de mediciones.
     */
    abstract fun medicionDao(): MedicionDao

    companion object {
        @Volatile
        private var INSTANCIA: BaseDatosApp? = null

        /**
         * Obtiene la instancia única de la base de datos (Singleton).
         * @param contexto El contexto de la aplicación.
         * @return La instancia de BaseDatosApp.
         */
        fun obtenerBaseDatos(contexto: Context): BaseDatosApp {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    contexto.applicationContext,
                    BaseDatosApp::class.java,
                    "base_datos_mediciones"
                ).build()
                INSTANCIA = instancia
                instancia
            }
        }
    }
}
