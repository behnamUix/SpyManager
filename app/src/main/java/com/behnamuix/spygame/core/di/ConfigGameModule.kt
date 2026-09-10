package com.behnamuix.spygame.core.di

import com.behnamuix.spygame.feature.configgame.data.repository.ConfigGameRepositoryImpl
import com.behnamuix.spygame.feature.configgame.domain.repository.ConfigGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

abstract class ConfigGameModule {
    //az bind estefade kardam chon interface va be hilt migam khodet implemention kon

    @Binds
    abstract fun bindConfigGameRepository(
        impl: ConfigGameRepositoryImpl
    ): ConfigGameRepository
}