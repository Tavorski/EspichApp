package es.uniovi.espichapp.interfaces

interface LocationListEvent {

    // Esta interfaz es necesaria porque no es posible ordenar el cambio de vista
    // desde LocationListViewHolder o saber en que establecimiento se ha clickado desde
    // el fragmento

    // La función onItemClick la llama LocationListViewHolder para delegar
    // en LocationListFragment la tarea de cambiar a la vista del detalle pasandole
    // como argumento el nombre del establecimiento en el que se ha clickado.

    fun onItemClick(position: Int)

}