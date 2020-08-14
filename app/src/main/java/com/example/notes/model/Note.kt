package com.example.notes.model

import android.graphics.Color
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class Note: RealmObject(), Serializable {
    @PrimaryKey
    var createdAtLong: Long? = null
    var title: String? = null
    var description: String? = null
    var color: Int

    init {

        val r = Random()
        val red = r.nextInt(256)
        val green = r.nextInt(256)
        val blue = r.nextInt(256)

        color = Color.rgb(red, green, blue)
    }
}
