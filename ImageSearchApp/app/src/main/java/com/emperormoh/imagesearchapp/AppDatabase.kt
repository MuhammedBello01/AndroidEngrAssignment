package com.emperormoh.imagesearchapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emperormoh.imagesearchapp.data.local.ImageDao
import com.emperormoh.imagesearchapp.data.local.RemoteKeyDao
import com.emperormoh.imagesearchapp.data.model.local.ImageEntity
import com.emperormoh.imagesearchapp.data.model.local.RemoteKey

@Database(entities = [ImageEntity::class, RemoteKey::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        fun getInstance(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        )
            .build()
    }

    abstract fun getImageDao(): ImageDao

    abstract fun getRemoteKeyDao(): RemoteKeyDao
}