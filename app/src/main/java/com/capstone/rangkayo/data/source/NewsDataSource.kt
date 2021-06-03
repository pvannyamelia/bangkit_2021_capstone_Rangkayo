package com.capstone.rangkayo.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.capstone.rangkayo.data.source.local.entity.NewsEntity
import com.capstone.rangkayo.data.source.remote.response.NewsResponse
import com.capstone.rangkayo.vo.Resource

interface NewsDataSource {

    fun getNews(): LiveData<Resource<PagedList<NewsEntity>>>

//    fun getDetailMovie(movieId: Int): LiveData<NewsResponse>


}