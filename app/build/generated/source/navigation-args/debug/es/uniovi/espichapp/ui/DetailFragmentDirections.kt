package es.uniovi.espichapp.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import es.uniovi.espichapp.R
import es.uniovi.espichapp.`data`.Coordinates
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class DetailFragmentDirections private constructor() {
  private data class ActionDetailFragmentToMapFragment(
    public val coordinates: Coordinates,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_detailFragment_to_mapFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(Coordinates::class.java)) {
          result.putParcelable("coordinates", this.coordinates as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(Coordinates::class.java)) {
          result.putSerializable("coordinates", this.coordinates as Serializable)
        } else {
          throw UnsupportedOperationException(Coordinates::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionDetailFragmentToMapFragment(coordinates: Coordinates): NavDirections =
        ActionDetailFragmentToMapFragment(coordinates)
  }
}
