package es.uniovi.espichapp.ui

import android.os.Bundle
import androidx.navigation.NavDirections
import es.uniovi.espichapp.R
import kotlin.Int
import kotlin.String

public class LocationsListFragmentDirections private constructor() {
  private data class ActionLocationsListFragmentToDetailFragment(
    public val locationName: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_locationsListFragment_to_detailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("locationName", this.locationName)
        return result
      }
  }

  public companion object {
    public fun actionLocationsListFragmentToDetailFragment(locationName: String): NavDirections =
        ActionLocationsListFragmentToDetailFragment(locationName)
  }
}
