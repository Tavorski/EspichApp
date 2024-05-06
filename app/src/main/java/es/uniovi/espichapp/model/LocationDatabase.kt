package es.uniovi.espichapp.model

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.uniovi.arqui.model.LocationDAO
import es.uniovi.espichapp.data.ApiResult
import es.uniovi.espichapp.network.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Location::class], version = 1)
abstract class LocationDatabase: RoomDatabase() {

    abstract fun locationDao(): LocationDAO

    companion object {
        private var INSTANCE: LocationDatabase? = null

        fun getInstance(context: Context): LocationDatabase? {
            if (INSTANCE == null) {
                synchronized(LocationDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocationDatabase::class.java,
                        "location.db"
                    )
                        .addCallback(CALLBACK)
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private val CALLBACK = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    try {// Respuesta correcta
                        // Petición al servicio
                        val locations = RestApi.retrofitService.getLocationsInfo()

                        // Se guardan los nuevos datos en la base de datos recién creada
                        locations.items.map { INSTANCE!!.locationDao().insertLocation(it) }

                    } catch (_: Exception) { /*si no se pudieron tomar los datos, ya se hará más tarde*/ }

                    /*INSTANCE!!.locationDao().insertLocation(
                        Location("Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo",
                            "Ejemplo")
                    )*/
                }
            }
        }
    }
}