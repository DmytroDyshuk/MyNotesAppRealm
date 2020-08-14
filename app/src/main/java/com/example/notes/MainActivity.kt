package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notes.db.RealmHelper
import com.example.notes.model.Note
import com.example.notes.new_note.NewNoteActivity
import com.example.notes.presenter.NotesAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private val itemList = ArrayList<Note>()
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        adapter = NotesAdapter(this, itemList)
        initAdapter()


        fabAdd.setOnClickListener {
            startActivity(Intent(this, NewNoteActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()

        reloadNotes()
        initSearchView(svNotes)
    }

    private fun initAdapter() {
        rvNotes.layoutManager = GridLayoutManager(this, 2)
        rvNotes.adapter = adapter
    }

    private fun reloadNotes() {
        val list = RealmHelper().getNotesFromRealm()
        itemList.clear()
        itemList.addAll(list)
        adapter.notifyDataSetChanged()
        Log.e("ERR", "${list.size}")
    }


    private fun getPatients(query: String = "") {
        val db = RealmHelper()
        db.getNotesInSearch(query).let { notes ->
            itemList.clear()
            itemList.addAll(notes)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initSearchView(view: View) {
        view.svNotes.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean { return false }

            override fun onQueryTextChange(newText: String?): Boolean {
                getPatients(newText ?: "")
                return false
            }
        })
    }

}