package com.example.myapplication

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.VocabularyDao
import com.example.myapplication.data.local.VocabularyDb
import com.example.myapplication.data.remote.wordApi
import com.example.myapplication.data.repository.repositoryImplemtation
import com.example.myapplication.domain.repository.wordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object VocabularyModul {

    @Provides
    fun provideDao(db: VocabularyDb): VocabularyDao {
        return db.dao
    }

    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext appContext:Context): VocabularyDb {
        return Room.databaseBuilder(
            appContext.applicationContext,
            VocabularyDb::class.java,
            "vocabulary"
        ).fallbackToDestructiveMigration().build()
    }
    @Singleton
    @Provides
    fun wordRet(): wordApi {
         return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.dictionaryapi.dev/")
            .build()
            .create(wordApi::class.java)
    }
    @Singleton
    @Provides
    fun didRepository(api: wordApi,db: VocabularyDb):wordRepository{
        return repositoryImplemtation(dao = db.dao, api = api)
    }
}