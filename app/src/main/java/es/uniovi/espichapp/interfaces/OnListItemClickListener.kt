package es.uniovi.espichapp.interfaces

    /**
     * Esta interfaz la implementa LocationListFragment para responder al evento onClick de las vistas
     * de los ViewHolders en la lista de establecimientos.
     * Esta interfaz es necesaria porque no es posible ordenar el cambio de vista
     * desde LocationListViewHolder o saber en que establecimiento se ha clickado desde
     * el fragmento.
     */
interface OnListItemClickListener {


    /**
     * La función onItemClick la llama LocationListViewHolder para delegar
     * en LocationListFragment la tarea de cambiar a la vista del detalle pasandole
     * como argumento el número de lista del establecimiento en el que se ha clickado.
     * @param position Elemento del adapter en el que se ha pulsado
     */
    fun onItemClick(position: Int)
}