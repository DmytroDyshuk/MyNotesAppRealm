package com.example.notes.db

import android.util.Log
import com.example.notes.model.Note
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import java.util.*
import kotlin.collections.ArrayList

class RealmHelper {

    private val realm = Realm.getInstance(
        RealmConfiguration.Builder()
        .deleteRealmIfMigrationNeeded()
        .build()
    )

    fun saveNoteToRealm(note: Note) {
        realm.executeTransaction { bgRealm ->
            bgRealm.copyToRealmOrUpdate(note.apply {
                Log.e("crtAT", "id: $createdAtLong")
                if (createdAtLong == null) {
                    createdAtLong = Calendar.getInstance().timeInMillis
                }
            })
        }
    }

    fun getNotesFromRealm() : ArrayList<Note> {
        val result = ArrayList<Note>()
        result.addAll(realm.copyFromRealm(realm.where(Note::class.java).sort("createdAtLong", Sort.DESCENDING).findAll()))
        return result
    }

    fun getNotesInSearch(query: String = "") : ArrayList<Note> {
        return ArrayList<Note>().apply {
            addAll(realm.copyFromRealm(realm.where(Note::class.java).sort("createdAtLong", Sort.DESCENDING).findAll().filter {
                "${it.title} ${it.description}".toLowerCase(Locale.ENGLISH).contains(query.toLowerCase(Locale.ENGLISH))
            }))
        }
    }

    fun deleteNote(createdAtLong: Long?) {
        realm.executeTransaction { bgRealm ->
            bgRealm.where(Note::class.java).equalTo("createdAtLong", createdAtLong).findFirst()?.deleteFromRealm()
        }
    }

}