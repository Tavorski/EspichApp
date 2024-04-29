package es.uniovi.espichapp.ui

import android.os.Bundle
import androidx.navigation.NavDirections
import es.uniovi.espichapp.R
import kotlin.Int
import kotlin.String

public class DetailFragmentDirections private constructor() {
  private data class ActionDetailFragmentToMapFragment(
    public val coordinates: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_detailFragment_to_mapFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("coordinates", this.coordinates)
        return result
      }
  }

  public companion object {
    public fun actionDetailFragmentToMapFragment(coordinates: String): NavDirections =
        ActionDetailFragmentToMapFragment(coordinates)
  }
}
