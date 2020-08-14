package com.example.notes.note_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.R
import com.example.notes.db.RealmHelper
import com.example.notes.model.Note
import kotlinx.android.synthetic.main.activity_note_details.*

class NoteDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        val note = intent.getSerializableExtra("intent_arg_note") as? Note

        etTitle.setText(note?.title)
        etDescription.setText(note?.description)

        tvSaveNote.setOnClickListener {
            if (note != null) {
                RealmHelper().saveNoteToRealm(note.apply {
                    title = etTitle.text.toString()
                    description = etDescription.text.toString()
                })
            }

            finish()
        }

        ivBack.setOnClickListener {
            finish()
        }
    }
}