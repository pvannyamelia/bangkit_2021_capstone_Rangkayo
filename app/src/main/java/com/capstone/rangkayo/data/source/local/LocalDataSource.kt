package com.capstone.rangkayo.data.source.local

import androidx.paging.DataSource
import com.capstone.rangkayo.data.source.local.entity.NewsEntity
import com.capstone.rangkayo.data.source.local.room.NewsDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val newsDao: NewsDao) {

    fun getNews() : DataSource.Factory<Int, NewsEntity> = newsDao.getMovies()

    fun insertMovies(news: List<NewsEntity>) = newsDao.insertMovies(news)

}