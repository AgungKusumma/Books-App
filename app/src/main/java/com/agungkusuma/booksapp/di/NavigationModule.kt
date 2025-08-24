package com.agungkusuma.booksapp.di

import com.agungkusuma.booksapp.navigation.AppNavigatorImpl
import com.agungkusuma.common.navigation.BaseNavigator
import com.agungkusuma.common.navigation.FeaturesNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

    @Binds
    @ActivityScoped
    abstract fun provideBaseNavigation(navigation: AppNavigatorImpl): BaseNavigator

    @Binds
    @ActivityScoped
    abstract fun provideFeaturesNavigation(navigation: AppNavigatorImpl): FeaturesNavigation
}