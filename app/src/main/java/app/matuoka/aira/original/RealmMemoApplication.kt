package app.matuoka.aira.original

import android.app.Application
import android.content.Context
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.*

class RealmMemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }

}