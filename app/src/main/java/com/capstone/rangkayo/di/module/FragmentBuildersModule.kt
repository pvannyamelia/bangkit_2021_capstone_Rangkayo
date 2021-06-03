package com.capstone.rangkayo.di.module


import com.capstone.rangkayo.ui.main.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment?

//    @ContributesAndroidInjector
//    abstract fun contributeTvFragment(): TvShowFragment?
//
//    @ContributesAndroidInjector
//    abstract fun contributeFavoriteFragment(): FavoriteFragment?
//
//    @ContributesAndroidInjector
//    abstract fun contributeMovieFavFragment(): FavoriteMovieFragment?
//
//    @ContributesAndroidInjector
//    abstract fun contributeTvFavFragment(): FavoriteTvShowFragment?
}