package com.example.fizzbuzzletmecounttothatnumber.injections

import com.example.usecase.FizzbuzzNumberConverterUseCase
import com.example.fizzbuzzletmecounttothatnumber.ui.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModuleInjection {

    @Provides
    @Singleton
    fun provideFizzbuzzNumberConverterUseCase(): FizzbuzzNumberConverterUseCase =
        FizzbuzzNumberConverterUseCase()

    @Provides
    @Singleton
    fun provideMainViewModel(): MainViewModel =
        MainViewModel(provideFizzbuzzNumberConverterUseCase())
}