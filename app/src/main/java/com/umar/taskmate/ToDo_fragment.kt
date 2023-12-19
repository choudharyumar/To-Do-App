package com.umar.taskmate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.umar.taskmate.databinding.FragmentToDoFragmentBinding


class ToDo_fragment : Fragment(), MyAdapterInterface {
    private var myAdapter: MyAdapter? = null
    private var listOfNotes: MutableList<Notes> = mutableListOf()
    private lateinit var notesViewModel: MainViewModel
    private lateinit var binding: FragmentToDoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToDoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { fragmentActivity ->
            notesViewModel = ViewModelProvider(this, ViewModelFactory(fragmentActivity))[MainViewModel::class.java]
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        myAdapter= MyAdapter(this)
        binding.recyclerView.adapter=myAdapter
        notesViewModel.getNotesView().observe(viewLifecycleOwner) {
            listOfNotes.clear()
            listOfNotes = it.toMutableList()
            myAdapter?.setData(listOfNotes)
        }

        binding.btnClick.setOnClickListener {
            val todoText=binding.edittext.text.toString()
            if (todoText.isNotEmpty()){
                val notes=Notes(messages = todoText)
                notesViewModel.insertNotesView(notes)
                binding.edittext.text.clear()
            }
            else{
                Toast.makeText(requireContext(),"To-Do can't be empty",Toast.LENGTH_LONG).show()
            }
        }
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onTodoClick(position: Int) {
        notesViewModel.DeleteNotesView(listOfNotes[position])
        requireContext()
    }
}


