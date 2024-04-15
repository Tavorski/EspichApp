package es.uniovi.espichapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.uniovi.espichapp.model.LocationList
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// URL base del servidor
private val BASE_URL = "http://orion.edv.uniovi.es/"

// Obejto MOSHI para convertir los datos entrantes en formato JSON a objetos Kotlin
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Objeto RETROFIT que hará los accesos al servicio REST
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface RestApiService {
    @GET("~arias/json/BodegasLlagaresQueserias.json")
    suspend fun getStatusInfo(): LocationList
}
object RestApi {
    val retrofitService: RestApiService by lazy {
        retrofit.create(RestApiService::class.java)
    }
}





