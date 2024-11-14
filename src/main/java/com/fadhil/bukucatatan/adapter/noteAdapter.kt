package com.fadhil.bukucatatan.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.bukucatatan.DetailNotesActivity
import com.fadhil.bukucatatan.R
import com.fadhil.bukucatatan.model.Note

class noteAdapter (private val notes: List<Note>) : RecyclerView.Adapter<noteAdapter.NoteViewHolder>() {
    class NoteViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.txtjudul)
        val content = itemView.findViewById<TextView>(R.id.txtdeskripsi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.content.text = note.content

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailNotesActivity::class.java).apply {
                // Mengirim data melalui intent
                putExtra("title", note.title)
                putExtra("content",note.content)
            }
            context.startActivity(intent)
        }
    }
}