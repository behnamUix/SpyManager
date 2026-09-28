package com.behnamuix.spygame.core.di

import com.behnamuix.spygame.feature.configgame.data.repository.ConfigGameRepositoryImpl
import com.behnamuix.spygame.feature.configgame.domain.repository.ConfigGameRepository
import com.behnamuix.spygame.feature.configrole.data.repository.ConfigRoleRepositoryImpl
import com.behnamuix.spygame.feature.configrole.data.repository.ConfigTimeRepositoryImpl
import com.behnamuix.spygame.feature.configrole.domain.repository.ConfigRoleRepository
import com.behnamuix.spygame.feature.configrole.domain.repository.ConfigTimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

abstract class ConfigTimeModule {
    //az bind estefade kardam chon interface va be hilt migam khodet implemention kon



    @Binds
    abstract fun bindConfigTimeRepository(
        impl: ConfigTimeRepositoryImpl
    ): ConfigTimeRepository
}