package com.codewithnazeefo.mynewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codewithnazeefo.mynewsapp.Model.Article

@Database(entities = [Article::class], version = 1)
        @TypeConverters(Converter::class)

abstract class ArticleDatabase():RoomDatabase () {
 abstract fun getArticleDao ():articleDao

  companion object {
      @Volatile

   private var instance :ArticleDatabase? = null
     private val LOCK  = Any()

    operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
         instance?:createDatabase(context).also{ instance = it}

    }

   private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, ArticleDatabase::class.java,
       "Article_db.db").build()

  }
}