package edu.gwu.trivia

import android.util.Log
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import edu.gwu.trivia.model.generated.petfinder.PetItem
import edu.gwu.trivia.model.generated.petfinder.PetfinderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.Type


class PetSearchManager {

    private val TAG = "PetSearchManager"
    var petSearchCompletionListener: PetSearchCompletionListener? = null

    interface PetSearchCompletionListener {
        fun petsLoaded(petItems: List<PetItem>)
        fun petsNotLoaded()
    }

    interface ApiEndpointInterface {
        @GET("pet.find")
        fun findPets(@Query("key") key: String, @Query("format") format: String, @Query("animal") animal: String, @Query("location") location: Int): Call<PetfinderResponse>
    }

    fun searchPets() {
        val moshi = Moshi.Builder()
                .add(ObjectAsListJsonAdapterFactory())
                .add(KotlinJsonAdapterFactory())
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.petfinder.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        val apiEndpoint = retrofit.create(ApiEndpointInterface::class.java)

        apiEndpoint.findPets("0b1452a0af588b352bf732936fa8219b", "json", "cat", 90210).enqueue(object: Callback<PetfinderResponse> {
            override fun onFailure(call: Call<PetfinderResponse>, t: Throwable) {
                Log.d(TAG, "API call failed!")
                petSearchCompletionListener?.petsNotLoaded()
            }

            override fun onResponse(call: Call<PetfinderResponse>, response: Response<PetfinderResponse>) {
                val petsResponseBody = response.body()

                petsResponseBody?.petfinder?.pets?.pet?.let {
                    petSearchCompletionListener?.petsLoaded(it)
                    return
                }

                petSearchCompletionListener?.petsNotLoaded()
            }
        })
    }

    /**
     *
     * The adapter Wraps an encountered Objects in a List whenever a List<T> was expected but an
     * Object was encountered in the JSON
     */

    class ObjectAsListJsonAdapterFactory : JsonAdapter.Factory {
        override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
            if (!List::class.java.isAssignableFrom(Types.getRawType(type))) {
                return null
            }
            val listDelegate = moshi.nextAdapter<List<Any>>(this, type, annotations)
            val innerType = Types.collectionElementType(type, List::class.java)
            val objectDelegate = moshi.adapter<Any>(innerType, annotations)
            return ListJsonAdapter(listDelegate, objectDelegate) as JsonAdapter<Any>
        }

        inner class ListJsonAdapter<T>(private val listDelegate: JsonAdapter<List<T>>,
                                       private val objectDelegate: JsonAdapter<T>) : JsonAdapter<List<T>>() {

            override fun fromJson(reader: JsonReader): List<T>? {
                if (reader.peek() == JsonReader.Token.BEGIN_OBJECT) {
                    objectDelegate.fromJson(reader)?.let { return arrayListOf(it) } ?: return null
                } else {
                    return listDelegate.fromJson(reader)
                }
            }

            override fun toJson(writer: JsonWriter, value: List<T>?) {
                listDelegate.toJson(writer, value)
            }
        }
    }
}