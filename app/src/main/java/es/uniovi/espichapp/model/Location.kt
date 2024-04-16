package es.uniovi.espichapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class Location(@PrimaryKey val Nombre: String,
                    val Concejo: String?,
                    val Zona: String?,
                    val Direccion: String?,
                    val Telefono: String?,
                    val Email: String?,
                    val Web: String?,
                    val Facebook: String?,
                    val Instagram: String?,
                    val BreveDescripcion: String?,
                    val Descripcion: String?,
                    val Horario: String?,
                    val Tarifas: String?,
                    val Visitas: String?,
                    val Productos: String?,
                    val Denominacion: String?,
                    val Observaciones: String?,
                    val Coordenadas: String?,
                    val Slide: String?,
                    val SlideTitulo: String?,
                    val SlideUrl: String?) {

}