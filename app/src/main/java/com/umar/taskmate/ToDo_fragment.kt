package com.umar.taskmate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umar.taskmate.databinding.FragmentToDoFragmentBinding


class ToDo_fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var newArrayList: ArrayList<String>
    private lateinit var binding: FragmentToDoFragmentBinding
    private lateinit var heading: Array<String>
    lateinit var btn: Button
    lateinit var Inputtext: EditText

    private lateinit var notesViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToDoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heading = arrayOf(
            "A quick brown fox jumps over the lazy dog",
            "A quick brown fox jumps over the lazy dog",
            "A quick brown fox jumps over the lazy dog",
        )
        btn = binding.btnClick
        Inputtext = binding.edittext
        recyclerView = binding.recyclerView
        // Use requireContext() or getContext() to get the valid context
        val layoutManager = LinearLayoutManager(requireContext())
        // Set the layout manager for your RecyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<String>()
        getUserData()
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
            override fun OnItemClick(position: Int) {

            }
        })

        activity?.let { fragmentActivity ->
            notesViewModel = ViewModelProvider(this, ViewModelFactory(fragmentActivity))[MainViewModel::class.java]
        }
    }

    private fun getUserData() {
        for (i in heading.indices) {
            val news = (heading[i])
            newArrayList.add(news)
        }
        adapter = MyAdapter(newArrayList)
        recyclerView.adapter = adapter
        btn.setOnClickListener {
            var input2 = Inputtext.text.toString()
            if (input2 != "") {
                newArrayList.add(input2)
                Inputtext.setText("")
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Plz first write", Toast.LENGTH_LONG).show()
            }
        }
    }


}