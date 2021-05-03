package br.com.digitalhouse.desafiomarvel.remote.api

import br.com.digitalhouse.desafiomarvel.domain.utils.*
import br.com.digitalhouse.desafiomarvel.remote.model.CharactersResponse
import br.com.digitalhouse.desafiomarvel.remote.model.ComicsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters/{characterId}/comics")
    suspend fun getComicDateByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("orderBy") orderBy: String = "-issueNumber", //organiza por numero da hq
        @Query("apikey") apikey: String = PUBLIC_KEY,
        @Query("hash") hash: String = HASH,
        @Query("ts") ts: Int = TS,
        @Query("limit") limit: Int = LIMIT

    ): ComicsResponse

    companion object {

        fun create(): MarvelApi {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarvelApi::class.java)
        }
    }

}