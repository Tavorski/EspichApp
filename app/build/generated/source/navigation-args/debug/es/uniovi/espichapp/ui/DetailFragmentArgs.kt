package es.uniovi.espichapp.ui

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class DetailFragmentArgs(
  public val locationName: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("locationName", this.locationName)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("locationName", this.locationName)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DetailFragmentArgs {
      bundle.setClassLoader(DetailFragmentArgs::class.java.classLoader)
      val __locationName : String?
      if (bundle.containsKey("locationName")) {
        __locationName = bundle.getString("locationName")
        if (__locationName == null) {
          throw IllegalArgumentException("Argument \"locationName\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"locationName\" is missing and does not have an android:defaultValue")
      }
      return DetailFragmentArgs(__locationName)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): DetailFragmentArgs {
      val __locationName : String?
      if (savedStateHandle.contains("locationName")) {
        __locationName = savedStateHandle["locationName"]
        if (__locationName == null) {
          throw IllegalArgumentException("Argument \"locationName\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"locationName\" is missing and does not have an android:defaultValue")
      }
      return DetailFragmentArgs(__locationName)
    }
  }
}
