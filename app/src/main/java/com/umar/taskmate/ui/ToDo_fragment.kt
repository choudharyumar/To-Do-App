package com.umar.taskmate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.umar.taskmate.data.Notes
import com.umar.taskmate.databinding.FragmentToDoFragmentBinding
import com.umar.taskmate.viewmodel.MainViewModel
import com.umar.taskmate.viewmodel.ViewModelFactory


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
        var linearLayoutManager=LinearLayoutManager(requireContext())
         binding.recyclerView.layoutManager=linearLayoutManager
        myAdapter= MyAdapter(this)
        binding.recyclerView.adapter=myAdapter
        notesViewModel.getNotesView().observe(viewLifecycleOwner) {
            listOfNotes.clear()
            listOfNotes = it.toMutableList()
            myAdapter?.setData(listOfNotes)
            if (listOfNotes.isNotEmpty()){
                binding.recyclerView.smoothScrollToPosition(listOfNotes.size-1)
            }
        }

        binding.btnClickLeft.setOnClickListener {
            handleButtonClick(isLeftMessage = true)
        }
        binding.btnClickRight.setOnClickListener {
            handleButtonClick(isLeftMessage = false)
        }


        binding.recyclerView.setHasFixedSize(true)
    }

//    override fun onPause() {
//        super.onPause()
//        exitProcess(0)
//    }

    override fun onTodoClick(position: Int) {
        notesViewModel.DeleteNotesView(listOfNotes[position])
        requireContext()
    }
    private fun handleButtonClick(isLeftMessage:Boolean){
        val todoText=binding.edittext.text.toString()
        if (todoText.isNotEmpty()){
            val notes= Notes(messages = todoText, isLeftMessage = isLeftMessage)
            notesViewModel.insertNotesView(notes)
            binding.edittext.text.clear()
        }
        else{
            Toast.makeText(requireContext(),"To-Do can't be empty",Toast.LENGTH_LONG).show()
        }

    }


}


