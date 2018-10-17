package edu.gwu.trivia

import android.util.Log
import edu.gwu.trivia.model.generated.petfinder.PetFinderResponse
import edu.gwu.trivia.model.generated.petfinder.PetItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class PetSearchManager {

    private val TAG = "PetSearchManager"
    var petSearchCompletionListener: PetSearchCompletionListener? = null

    interface PetSearchCompletionListener {
        fun petsLoaded(petItems: List<PetItem>)
        fun petsNotLoaded()
    }

    interface ApiEndpointInterface {
        @GET("pet.find")
        fun findPets(@Query("key") key: String, @Query("format") format: String, @Query("animal") animal: String, @Query("location") location: Int): Call<PetFinderResponse>
    }

    fun searchPets() {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.petfinder.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        val apiEndpoint = retrofit.create(ApiEndpointInterface::class.java)

        apiEndpoint.findPets("0b1452a0af588b352bf732936fa8219b", "json", "cat", 22102).enqueue(object: Callback<PetFinderResponse> {
            override fun onFailure(call: Call<PetFinderResponse>, t: Throwable) {
                Log.d(TAG, "API call failed!")
                petSearchCompletionListener?.petsNotLoaded()
            }

            override fun onResponse(call: Call<PetFinderResponse>, response: Response<PetFinderResponse>) {
                val petsResponseBody = response.body()

                petsResponseBody?.petfinder?.pets?.pet?.let {
                    petSearchCompletionListener?.petsLoaded(it)
                    return
                }

                petSearchCompletionListener?.petsNotLoaded()
            }
        })
    }
}