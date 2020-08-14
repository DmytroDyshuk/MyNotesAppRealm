package com.example.notes.new_note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.R
import com.example.notes.db.RealmHelper
import com.example.notes.model.Note
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        ivBack.setOnClickListener {
            finish()
        }

        tvSaveNote.setOnClickListener {
            RealmHelper().saveNoteToRealm(Note().apply {
                title = etTitle.text.toString()
                description = etDescription.text.toString()
            })

            finish()
        }

        ivBack.setOnClickListener {
            finish()
        }
    }

}