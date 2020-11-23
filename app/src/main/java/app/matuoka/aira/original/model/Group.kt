package app.matuoka.aira.original.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


open class Group(
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var title: String = "",
    open var createdAt: Date = Date(System.currentTimeMillis())
) : RealmObject()
