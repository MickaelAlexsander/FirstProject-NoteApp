package com.example.noteapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.FragmentNoteListBinding
import com.example.noteapp.ui.adapter.NoteListAdapter
import com.example.noteapp.viewmodel.InventoryViewModel
import com.example.noteapp.viewmodel.InventoryViewModelFactory
import com.example.noteapp.viewmodel.NoteApplication


class NoteListFragment : Fragment() {

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as NoteApplication).database.noteDao()
        )
    }

    private lateinit var binding: FragmentNoteListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NoteListAdapter {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(it.id)
            findNavController().navigate(action)
        }

        binding.recycleView.adapter = adapter
        viewModel.allItems.observe(this.viewLifecycleOwner){ notes ->
            notes.let {
                adapter.submitList(it)
            }
        }



        binding.recycleView.layoutManager = LinearLayoutManager(this.context)
        binding.createNoteButton.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToCreateNoteFragment()
            this.findNavController().navigate(action)
        }
    }

}