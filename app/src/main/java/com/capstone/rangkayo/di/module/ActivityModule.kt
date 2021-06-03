package com.capstone.rangkayo.di.module


import com.capstone.rangkayo.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): MainActivity

//    @ContributesAndroidInjector
//    abstract fun contributeDetailActivity(): DetailActivity

}