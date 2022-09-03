package com.example.noteapp.ui.fragment

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.ActivityMainBinding.bind
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.ui.adapter.NoteListAdapter
import com.example.noteapp.viewmodel.InventoryViewModel
import com.example.noteapp.viewmodel.InventoryViewModelFactory
import com.example.noteapp.viewmodel.NoteApplication
import kotlin.math.log


class NoteDetailFragment : Fragment() {

    private val TAG = "NOTEDETAIL"


    private val noteId by navArgs<NoteDetailFragmentArgs>()
    lateinit var note: Note
    private lateinit var binding: FragmentNoteDetailBinding

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as NoteApplication).database.noteDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = noteId.id
        viewModel.retrieveNote(id).observe(this.viewLifecycleOwner) { selectedNote ->
            note = selectedNote
            bind(note)
        }
    }

    private fun bind(note: Note) {
        binding.apply {
            inputTitle.setText(note.noteTitle)
            when (note.notePriority){
                "high"-> priority.check(R.id.radioHigh)
                "medium"-> priority.check(R.id.radioMedium)
                else -> priority.check(R.id.radioLow)
            }
            inputDesc.setText(note.noteDesc)
            binding.saveNoteButton.setOnClickListener{
                updateNote()
            }
        }
    }
    private fun isEntryValid(): Boolean {
        val priority = when (binding.priority.checkedRadioButtonId){
            R.id.radioHigh -> "high"
            R.id.radioMedium -> "medium"
            else -> "low"
        }

        return viewModel.isEntryValid(
            binding.inputTitle.text.toString(),
            binding.inputDesc.text.toString(),
            priority
        )
    }
    private fun updateNote() {
        val priority: String = when (binding.priority.checkedRadioButtonId){
            R.id.radioHigh -> "high"
            R.id.radioMedium -> "medium"
            else -> "low"
        }
        if (isEntryValid()) {
            viewModel.updateNote(
                this.noteId.id,
                this.binding.inputTitle.text.toString(),
                this.binding.inputDesc.text.toString(),
                priority
            )
            val action = NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteListFragment()
            findNavController().navigate(action)
        }
    }
}