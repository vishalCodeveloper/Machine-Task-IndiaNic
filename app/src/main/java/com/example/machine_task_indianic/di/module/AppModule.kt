package com.example.machine_task_indianic.di.module

import android.content.Context
import androidx.room.Room
import com.example.machine_task_indianic.BuildConfig
import com.example.machine_task_indianic.remote.repository.WeatherRepository
import com.example.machine_task_indianic.remote.datasource.WeatherLiveDataSource
import com.example.machine_task_indianic.remote.service.WeatherApiService
import com.example.machine_task_indianic.database.AppDatabase
import com.example.machine_task_indianic.pref.SharedPreferencesManagerImpl
import com.example.machine_task_indianic.utils.URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) = SharedPreferencesManagerImpl(context)

    @Provides
    fun provideBaseUrl() = URL.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(WeatherApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: WeatherLiveDataSource): WeatherRepository = apiHelper

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "task"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.weatherDAO()
}