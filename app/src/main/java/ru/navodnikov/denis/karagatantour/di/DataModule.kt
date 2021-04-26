package ru.navodnikov.denis.karagatantour.di

import androidx.room.Room
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.navodnikov.denis.data.db.AppDatabase
import ru.navodnikov.denis.data.db.AppDatabase.Companion.NAME
import ru.navodnikov.denis.data.networking.TelegramApiService
import ru.navodnikov.denis.data.repositories.CitesRepositoryImpl
import ru.navodnikov.denis.data.repositories.ExcursionRepositoryImpl
import ru.navodnikov.denis.data.repositories.IslandRepositoryImpl
import ru.navodnikov.denis.data.repositories.OrderRepositoryImpl
import ru.navodnikov.denis.data.storage.HardcodeStorage
import ru.navodnikov.denis.data.storage.Storage
import ru.navodnikov.denis.domain.repositories.CitesRepository
import ru.navodnikov.denis.domain.repositories.ExcursionRepository
import ru.navodnikov.denis.domain.repositories.IslandRepository
import ru.navodnikov.denis.domain.repositories.OrderRepository
import ru.navodnikov.denis.karagatantour.ui.utils.Constants.Companion.BASE_URL
import java.util.concurrent.TimeUnit


private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

val networkingModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(TelegramApiService::class.java) }
    single { provideOkHttpClient() }
    single { provideRetrofit() }
}

fun Scope.provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(get())
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun Scope.provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
    }.build()
}

val dataModule = module {

    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, NAME).build()
    }
    single { get<AppDatabase>().getReposDao() }

    single<Storage> { HardcodeStorage() }
    single<IslandRepository> { IslandRepositoryImpl(storage = get(), context = androidApplication()) }
    single<ExcursionRepository> { ExcursionRepositoryImpl(storage = get()) }
    single<CitesRepository> { CitesRepositoryImpl(storage = get(), context = androidApplication()) }
    single<OrderRepository> {
        OrderRepositoryImpl(
            storageDB =  get(),
            context = androidApplication(),
            storage = get(),
            telegramApiService = get()
        )
    }
}
