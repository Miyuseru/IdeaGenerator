package app.matuoka.aira.original

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


open class Word(
    @PrimaryKey
    open var id: String = UUID.randomUUID().toString(),
    open var title: String = "",
    open var groupId: String = "",
    open var createdAt: Date = Date(System.currentTimeMillis())

) : RealmObject()
