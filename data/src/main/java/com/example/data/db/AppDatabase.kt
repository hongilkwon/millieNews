package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.db.dao.ArticleDao
import com.example.data.db.entity.ArticleEntity

@Database(
    entities = [
        ArticleEntity::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "AppDatabase.db"
    }

    abstract fun articleDao() : ArticleDao
}