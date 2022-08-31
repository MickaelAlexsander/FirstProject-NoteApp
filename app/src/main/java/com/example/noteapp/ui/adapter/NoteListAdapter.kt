package com.example.noteapp.ui.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.NoteListAdapterBinding

class NoteListAdapter(private val onItemClicked:(Note)->Unit):
    ListAdapter<Note, NoteListAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            NoteListAdapterBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: NoteListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.apply {
                binding.noteTitle.text = note.noteTitle
                binding.noteDate.text = note.noteDate
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldNote: Note, newNote: Note): Boolean {
                return oldNote === newNote
            }

            override fun areContentsTheSame(oldNote: Note, newNote: Note): Boolean {
                return oldNote.noteTitle == newNote.noteTitle
            }
        }
    }
}