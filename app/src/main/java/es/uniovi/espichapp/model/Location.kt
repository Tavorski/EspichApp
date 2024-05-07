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


    /*
  isBodega() hace un barrido rápido de los datos del establecimiento para determinar si puede ser
  un lugar dedicado a la elaboración de vinos
   */
    fun isBodega(): Boolean {
        var ret: Boolean
        with(this.Nombre) {
            ret = contains("bodega",true)
                    || contains("vino",true)
        }
        with(this.BreveDescripcion) {
            ret = ret || this?.contains("bodega",true) ?: false
                    || this?.contains("vino",true) ?: false
        }
        with(this.Descripcion) {
            ret = ret || this?.contains("vino",true) ?: false
        }
        with(this.Productos) {
            ret = ret || this?.contains("vino",true) ?: false
        }
        return ret;
    }

    /*
    isLlagar() hace un barrido rápido de los datos del establecimiento para determinar si puede ser
    un lugar dedicado a la elaboración de sidra
     */
    fun isLlagar(): Boolean {
        var ret: Boolean
        with(this.Nombre) {
            ret = contains("sidra",true)
                    || contains("llagar",true)
                    || contains("pomarada",true)
        }
        with(this.BreveDescripcion) {
            ret = ret || this?.contains("sidra",true) ?: false
                    || this?.contains("llagar",true) ?: false
                    || this?.contains("pomarada",true) ?: false
        }
        with(this.Descripcion) {
            ret = ret || this?.contains("sidra",true) ?: false
                    || this?.contains("llagar",true) ?: false
                    || this?.contains("pomarada",true) ?: false
        }
        with(this.Productos) {
            ret = ret || this?.contains("sidra",true) ?: false
        }
        return ret;
    }

    /*
    isQueseria() hace un barrido rápido de los datos del establecimiento para determinar si puede ser
    un lugar dedicado a la elaboración de queso
     */
    fun isQueseria(): Boolean {
        var ret: Boolean
        with(this.Nombre) {
            ret = contains("queso",true)
                    || contains("quesería",true)
        }
        with(this.BreveDescripcion) {
            ret = ret || this?.contains("queso",true) ?: false
                    || this?.contains("quesería",true) ?: false
        }
        with(this.Descripcion) {
            ret = ret || this?.contains("queso",true) ?: false
                    || this?.contains("quesería",true) ?: false
        }
        with(this.Productos) {
            ret = ret || this?.contains("queso",true) ?: false
        }
        return ret;
    }

}