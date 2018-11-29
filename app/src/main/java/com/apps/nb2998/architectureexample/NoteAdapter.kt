package com.apps.nb2998.architectureexample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    var notes = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_note, parent, false))
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.tvTitle.text = notes[position].title
        holder.tvDescription.text = notes[position].description
        holder.tvPriority.text = notes[position].priority.toString()
    }

    class NoteHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvPriority: TextView = itemView.findViewById(R.id.text_view_priority)
        val tvDescription: TextView = itemView.findViewById(R.id.text_view_description)
        val tvTitle: TextView = itemView.findViewById(R.id.text_view_title)
    }

    fun setNoteList(notes: MutableList<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}