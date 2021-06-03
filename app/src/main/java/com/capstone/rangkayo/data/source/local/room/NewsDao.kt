package com.capstone.rangkayo.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.capstone.rangkayo.data.source.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM table_news")
    fun getMovies() : DataSource.Factory<Int, NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = NewsEntity::class)
    fun insertMovies(news: List<NewsEntity>)

    @Update(entity = NewsEntity::class)
    fun updateMovie(news : NewsEntity)


}