package com.capstone.rangkayo.di.component

import android.app.Application
import com.capstone.rangkayo.App
import com.capstone.rangkayo.di.module.ActivityModule
import com.capstone.rangkayo.di.module.AppModule
import com.capstone.rangkayo.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        NetworkModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}