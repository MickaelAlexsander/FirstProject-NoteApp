package com.example.noteapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.FragmentNoteListBinding


class NoteListFragment : Fragment() {
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
        binding.recycleView.layoutManager = LinearLayoutManager(this.context)
        binding.createNoteButton.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToCreateNoteFragment()
            this.findNavController().navigate(action)
        }
    }
}