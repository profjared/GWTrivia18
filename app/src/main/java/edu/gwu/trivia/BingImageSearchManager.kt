package edu.gwu.trivia

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import edu.gwu.trivia.model.generated.BingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

class BingImageSearchManager(private val context: Context, private val imageView: ImageView ) {

    private val TAG = "BingImageSearchManager"
    var imageSearchCompletionListener: ImageSearchCompletionListener? = null

    interface ImageSearchCompletionListener {
        fun imageLoaded()
        fun imageNotLoaded()
    }

    interface ApiEndpointInterface {
        @Headers("Ocp-Apim-Subscription-Key: ${Constants.BING_API_KEY}")
        @GET("bing/v7.0/images/search")
        fun getBingResponse(@Query("q") query: String, @Query("safeSearch") safeSearch: String, @Query("mkt") market: String): Call<BingResponse>
    }

    fun searchImages(query: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BING_SEARCH_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        val apiEndpoint = retrofit.create(ApiEndpointInterface::class.java)

        apiEndpoint.getBingResponse(query, "Strict", "en-us").enqueue(object: Callback<BingResponse> {
            override fun onFailure(call: Call<BingResponse>, t: Throwable) {
                Log.d(TAG, "API call failed!")
                imageSearchCompletionListener?.imageNotLoaded()
            }

            override fun onResponse(call: Call<BingResponse>, response: Response<BingResponse>) {
                val orientation = context.resources.configuration.orientation
                val bingResponseBody = response.body()

                if(bingResponseBody != null) {
                    val uri = parseUriFromBingResponse(bingResponseBody, orientation)
                    Log.d(TAG, "found uri: $uri")

                    Picasso.get().load(uri).into(imageView, object: com.squareup.picasso.Callback {
                        override fun onSuccess() {
                            imageSearchCompletionListener?.imageLoaded()
                        }

                        override fun onError(e: java.lang.Exception?) {
                            imageSearchCompletionListener?.imageNotLoaded()
                        }
                    })
                } else {
                    imageSearchCompletionListener?.imageNotLoaded()
                }
            }
        })
    }

    private fun parseUriFromBingResponse(bingResponse: BingResponse, desiredOrientation: Int): Uri? {
        //navigate through response to obtain an image url that meets criteria: not too large, and correct orientation
        val valueItems = bingResponse.value
        for (item in valueItems) {
            val tooBig = Integer.parseInt(item.contentSize.replace(" B", "")) > Constants.MAX_IMAGE_FILE_SIZE_IN_BYTES
            if (!tooBig) {
                val width = item.width
                val height = item.height

                try {
                    if (desiredOrientation == Configuration.ORIENTATION_PORTRAIT) {
                        if (height > width) {
                            return Uri.parse(item.contentUrl)
                        }
                    } else if (desiredOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                        if (width > height) {
                            return Uri.parse(item.contentUrl)
                        }
                    }
                } catch (e: Exception) {
                    //skip this image
                }
            }
        }

        return null
    }





}