package es.uniovi.espichapp.interfaces

    /**
     * Esta interfaz la implementan aquellos fragmentos que deban controllar el uso que hacen sus
     * ViewHolders y Adapters de la red, quienes recibirán como argumento una referencia al fragmento
     */
interface NetworkUseController {

        /**
         * Esta función es invocada por aquellas clases que requieran saber si está permitida la descarga
         * de datos empleando datos móviles, como es el caso de los ViewHolder que emplean Picasso
         * para descargar la imagen del establecimiento y pintarla en la lista
         * @return False si no se deben descargar datos a través de la red. True si la descarga de
         * datos a través de la red está permitida
         */
    fun areDownloadsAllowed(): Boolean
}