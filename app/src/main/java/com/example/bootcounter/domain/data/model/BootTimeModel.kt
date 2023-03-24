package com.example.bootcounter.domain.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

open class BootTimeModel (
    @PrimaryKey
    var eventId: Int = 0,
    var timestamp: Long = 0,
) : RealmObject() {
    override fun toString(): String {
        return "$eventId : $timestamp"
    }
}