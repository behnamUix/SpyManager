package com.behnamuix.spygame.feature.configgame.domain.usecase


import com.behnamuix.spygame.feature.configgame.domain.repository.ConfigGameRepository
import javax.inject.Inject


class ConfigGameUseCase @Inject constructor(
    private val configGameRepository: ConfigGameRepository
) {


    fun incAgentCountPlayer():Int {
        return configGameRepository.incAgentCountPlayer()
    }

    fun decAgentCountPlayer():Int {
        return configGameRepository.decAgentCountPlayer()
    }

    fun incSpyCountPlayer():Int {
        return configGameRepository.incSpyCountPlayer()
    }

    fun decSpyCountPlayer():Int {
        return configGameRepository.decSpyCountPlayer()
    }
    fun getAgentCode()=configGameRepository.getAgentCode()
    fun getSpyCode()=configGameRepository.getSpyCode()

    fun getBiometricProg()=configGameRepository.getBioProg()


}