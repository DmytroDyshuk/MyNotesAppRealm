package com.example.notes.presenter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.db.RealmHelper
import com.example.notes.model.Note
import com.example.notes.note_details.NoteDetailsActivity
import java.util.*


class NotesAdapter(private val context: Context, private val values: ArrayList<Note>): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = values[position]
        holder.tvTitle?.text = note.title
        holder.tvDescription?.text = note.description
        holder.tvNumber?.text = (position + 1).toString()

        val draw = GradientDrawable()
        draw.shape = GradientDrawable.RECTANGLE
        draw.setColor(note.color)
        holder.ivColorLine?.background = draw

        holder.llNote?.setOnClickListener {
            val intent = Intent(context, NoteDetailsActivity::class.java)
            intent.putExtra("intent_arg_note", note)
            context.startActivity(intent)
        }

        holder.llDelete?.setOnClickListener {
            RealmHelper().deleteNote(note.createdAtLong)
            values.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, values.size)
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById(R.id.tvTitle) as? TextView
        val tvDescription = itemView.findViewById(R.id.tvDescription) as? TextView
        val ivColorLine = itemView.findViewById(R.id.ivColorLine) as? ImageView
        val tvNumber = itemView.findViewById(R.id.tvNumber) as? TextView
        val llNote = itemView.findViewById(R.id.llNote) as? LinearLayout
        val llDelete = itemView.findViewById(R.id.llDelete) as? LinearLayout
    }

}