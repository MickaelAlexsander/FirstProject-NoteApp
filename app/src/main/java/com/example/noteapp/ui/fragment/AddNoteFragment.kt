package com.example.noteapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.viewmodel.InventoryViewModel
import com.example.noteapp.viewmodel.InventoryViewModelFactory
import com.example.noteapp.viewmodel.NoteApplication


class AddNoteFragment : Fragment() {

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as NoteApplication).database
                .noteDao()
        )
    }

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        val priority = when (binding.priority.checkedRadioButtonId){
            R.id.radioHigh -> "high"
            R.id.radioMedium -> "medium"
            else -> "low"
        }

        return viewModel.isEntryValid(
            binding.inputTitle.editText.toString(),
            binding.inputDesc.editText.toString(),
            priority
        )
    }
    private fun addNewNote() {
        val priority: String = when (binding.priority.checkedRadioButtonId){
            R.id.radioHigh -> "high"
            R.id.radioMedium -> "medium"
            else -> "low"
        }
        if (isEntryValid()) {
            viewModel.addNewNote(
                binding.inputTitle.editText?.getText().toString(),
                binding.inputDesc.editText?.getText().toString(),
                priority
            )
            val action = AddNoteFragmentDirections.actionCreateNoteFragmentToNoteListFragment()
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveNoteButton.setOnClickListener {
            addNewNote()
        }
    }
}