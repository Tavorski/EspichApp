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
    suspend fun insertLocation(course: Location)

    @Query("DELETE FROM location_table WHERE name = :course")
    suspend fun deleteLocation(course:String)

    @Query("SELECT * FROM location_table WHERE name LIKE :name")
    fun getLocationByName(name:String): Flow<Location>

    @Query("SELECT name FROM location_table")
    fun getNames(): Flow<List<String>>
}