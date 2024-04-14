package es.uniovi.espichapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class Location(@PrimaryKey val name: String,
                    val council: String,
                    val zone: String,
                    val address: String,
                    val telephone: String,
                    val email: String,
                    val web: String,
                    val facebook: String,
                    val instagram: String,
                    val twitter: String,
                    val shortDescription: String,
                    val description: String,
                    val schedule: String,
                    val fees: String,
                    val visits: String,
                    val products: String,
                    val certificateOfOrigin: String,
                    val observations: String,
                    val coordinates: String,
                    val slide: String,
                    val slidTitle: String,
                    val slideUrl: String) {
}