package ru.navodnikov.denis.data.networking

import retrofit2.http.GET
import retrofit2.http.Query
import ru.navodnikov.denis.data.entity.GET_URL
import ru.navodnikov.denis.data.entity.MassageResult
import ru.navodnikov.denis.data.entity.QUERY

interface TelegramApiService {
    @GET(GET_URL)
    suspend fun sendMassage(
        @Query(QUERY)
        massage: String
    ): MassageResult
}