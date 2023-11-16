package com.example.myapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VocabularyEntity::class], version = 2, exportSchema = false)
abstract class VocabularyDb:RoomDatabase () {

    abstract val dao: VocabularyDao

    companion object{

        @Volatile
        private var instance: VocabularyDao?=null
        fun getDaoInstance(context: Context): VocabularyDao
        {
            synchronized(this)
            {

            var createInstance= instance
            if (createInstance==null){
                 createInstance= buildDatabase(context).dao
                instance =createInstance
            }
           return createInstance
        }

        }

        private fun  buildDatabase(context: Context)
        : VocabularyDb =Room.databaseBuilder(
            context.applicationContext,
            VocabularyDb::class.java,
            "vocabulary"
        ).fallbackToDestructiveMigration().build()
    }
}