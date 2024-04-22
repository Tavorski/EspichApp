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
    suspend fun getLocationByName(name: String): Location

    @Query("SELECT * FROM location_table")
    fun getLocationsFlow(): Flow<List<Location>>

    @Query("SELECT * FROM location_table WHERE Nombre LIKE :name")
    fun searchLocationsByName(name: String): Flow<List<Location>>

}