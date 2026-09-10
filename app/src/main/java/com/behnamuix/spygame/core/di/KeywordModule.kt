package com.behnamuix.spygame.core.di

import com.behnamuix.spygame.feature.configword.data.repository.KeywordRepositoryImpl
import com.behnamuix.spygame.feature.configword.domain.repository.KeywordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    //az bind estefade kardam chon interface va be hilt migam khodet implemention kon
    @Binds
    abstract fun bindKeywordRepository(
        impl: KeywordRepositoryImpl
    ): KeywordRepository
}