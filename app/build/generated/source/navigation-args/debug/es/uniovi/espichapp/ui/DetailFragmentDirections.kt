package es.uniovi.espichapp.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import es.uniovi.espichapp.R

public class DetailFragmentDirections private constructor() {
  public companion object {
    public fun actionDetailFragmentToLocationsListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_detailFragment_to_locationsListFragment)
  }
}
