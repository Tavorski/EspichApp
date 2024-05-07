package es.uniovi.arqui.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.uniovi.espichapp.model.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(loc: Location)

    @Query("DELETE FROM location_table WHERE Nombre = :name")
    suspend fun deleteLocation(name: String)

    @Query("SELECT * FROM location_table WHERE Nombre == :name")
    fun getLocationByName(name: String): Flow<Location>

    @Query("SELECT * FROM location_table")
    fun getLocations(): Flow<List<Location>>

    @Query("SELECT * FROM location_table WHERE " +
            "Nombre LIKE '%' || :search || '%' OR " +
            "Concejo LIKE '%' || :search || '%'") // tambien permite buscar por localidad
    fun searchLocationsByName(search: String): Flow<List<Location>>

    @Query("DELETE FROM location_table")
    suspend fun deleteLocations()

}