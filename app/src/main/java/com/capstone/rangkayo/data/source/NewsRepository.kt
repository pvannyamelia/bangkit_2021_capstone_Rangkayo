package com.capstone.rangkayo.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.capstone.rangkayo.data.source.local.entity.NewsEntity
import com.capstone.rangkayo.data.source.remote.NetworkBoundResource
import com.capstone.rangkayo.data.source.remote.response.Responses
import com.capstone.rangkayo.data.source.remote.RemoteDataSource
import com.capstone.rangkayo.vo.Resource
import com.capstone.rangkayo.data.source.local.LocalDataSource
import com.capstone.rangkayo.data.source.remote.ApiResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    NewsDataSource {
    override fun getNews(): LiveData<Resource<PagedList<NewsEntity>>> {
        return object : NetworkBoundResource<PagedList<NewsEntity>, Responses>() {
            public override fun loadFromDB(): LiveData<PagedList<NewsEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getNews(), config).build()
            }

            override fun shouldFetch(data: PagedList<NewsEntity>?) = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<Responses>> =
                remoteDataSource.getNews()

            override fun saveCallResult(data: Responses) {
                val newsList = ArrayList<NewsEntity>()
                data.articles.forEach { news ->
                    val entity = NewsEntity(
                        news._id,
                        news.title,
                        news.media,
                        news.summary,
                        news.link
                    )
                    newsList.add(entity)
                    localDataSource.insertMovies(newsList)
                }
            }
        }.asLiveData()
    }


}