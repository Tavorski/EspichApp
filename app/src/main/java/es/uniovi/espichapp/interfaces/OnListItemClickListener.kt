package es.uniovi.espichapp.interfaces

    /**
     * Esta interfaz la implementan aquellos fragmentos que deban controllar el uso que hacen sus
     * ViewHolders y Adapters de la red, quienes recibirán como argumento una referencia al fragmento.
     * Esta interfaz es necesaria porque no es posible ordenar el cambio de vista
     * desde LocationListViewHolder o saber en que establecimiento se ha clickado desde
     * el fragmento
     */
interface OnListItemClickListener {


    /**
     * La función onItemClick la llama LocationListViewHolder para delegar
     * en LocationListFragment la tarea de cambiar a la vista del detalle pasandole
     * como argumento el nombre del establecimiento en el que se ha clickado.
     * @param position Elemento del adapter en el que se ha pulsado
     */
    fun onItemClick(position: Int)
}