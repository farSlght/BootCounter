package com.example.bootcounter.domain.data

import androidx.lifecycle.MutableLiveData
import com.example.bootcounter.domain.data.model.BootTimeModel
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import io.realm.kotlin.where

object BootTimeStorage {

    private val realmName: String = "BootCounter"
    private val config = RealmConfiguration.Builder().name(realmName).build()
    private val realm: Realm = Realm.getInstance(config)

    val bootTimes = MutableLiveData<List<BootTimeModel>>()

    fun saveNewBootTime() {
        val now = System.currentTimeMillis()
        realm.where<BootTimeModel>().findAll().max("eventId")?.let { maxOrderNumber ->
            realm.executeTransaction {
                it.insert(BootTimeModel(maxOrderNumber.toInt() + 1, now))
            }
        }
        bootTimes.postValue(getSavedBootTimes())
    }

    fun getSavedBootTimes(): List<BootTimeModel> {
        return realm.where<BootTimeModel>().findAll()
    }

    fun getLatestBootTimeDiff(): BootTimeModel? {
        val bootTimes = realm.where<BootTimeModel>().findAll()
        if (bootTimes.isNotEmpty()) {
            val timeDiff: Long = bootTimes.sort("eventId", Sort.DESCENDING).run {
                (get(0)?.timestamp ?: 0) - (get(1)?.timestamp ?: 0)
            }
            val latestBootEvent = bootTimes.max("eventId")
            latestBootEvent?.let {
                return BootTimeModel(it.toInt(), timeDiff)
            }
        }
        return null
    }
}