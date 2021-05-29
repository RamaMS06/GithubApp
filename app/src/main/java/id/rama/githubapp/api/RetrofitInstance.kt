package id.rama.githubapp.api

import id.rama.githubapp.utils.Utilities
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder()


    private val retrofitListUsers by lazy {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        Retrofit.Builder()
            .baseUrl(Utilities.URL_GITHUB)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiListUsers : ApiServices by lazy {
        retrofitListUsers.create(ApiServices::class.java)
    }

}