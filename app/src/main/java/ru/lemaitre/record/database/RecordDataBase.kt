package ru.lemaitre.record.database

import android.content.Context
import android.icu.text.AlphabeticIndex
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecordingItem::class], version = 1, exportSchema = false)
abstract class RecordDataBase : RoomDatabase() {
    abstract val recordDatabase: RecordDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDataBase? = null

        fun getInstance(context: Context): RecordDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecordDataBase::class.java,
                        "recorder_app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}