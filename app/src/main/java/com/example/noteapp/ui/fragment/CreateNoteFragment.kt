package com.example.noteapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentCreateNoteBinding
import com.example.noteapp.databinding.FragmentNoteListBinding


class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveNoteButton.setOnClickListener {
            val action = CreateNoteFragmentDirections.actionCreateNoteFragmentToNoteListFragment()
            this.findNavController().navigate(action)
        }
    }
}