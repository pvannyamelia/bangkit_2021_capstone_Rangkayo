package com.capstone.rangkayo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.capstone.rangkayo.data.source.NewsRepository
import com.capstone.rangkayo.data.source.local.entity.NewsEntity
import com.capstone.rangkayo.data.source.remote.response.NewsResponse
import com.capstone.rangkayo.vo.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    fun getMovies() : LiveData<Resource<PagedList<NewsEntity>>> = newsRepository.getNews()
}