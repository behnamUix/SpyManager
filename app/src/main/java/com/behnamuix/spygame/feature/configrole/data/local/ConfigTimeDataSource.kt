package com.behnamuix.spygame.feature.configrole.data.local

import com.behnamuix.spygame.feature.configrole.domain.model.Time
import javax.inject.Inject

class ConfigTimeDataSource @Inject constructor() {

    fun increaseTime(): Int {
        if (Time.count < 10) {
            Time.count += 2
        }

        return Time.count
    }

    fun decreaseTime(): Int {
        if (Time.count > 0) {
            Time.count -= 2
        }

        return Time.count
    }
}