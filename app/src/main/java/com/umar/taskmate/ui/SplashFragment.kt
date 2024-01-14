package com.umar.taskmate.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.umar.taskmate.R
import com.umar.taskmate.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navController = findNavController()
//        view.postDelayed({
//            navController.navigate(R.id.action_splashFragment_to_toDo_fragment)
//        }, 3000)
        progressBar=binding.progressBar
        progressBar.max=100
        simulateProgressBar()
    }
    private fun simulateProgressBar(){
        val handler=Handler(Looper.getMainLooper())
        var taskProcess=object :Runnable{
            var progress=0
            override fun run() {
               progressBar.progress=progress
                if (progress==progressBar.max){
                    navigateToNextScreen()
                }
                else{
                    progress++
                    handler.postDelayed(this,30)
                }
            }
        }
        handler.post(taskProcess)
    }
    private fun navigateToNextScreen(){
        findNavController().navigate(R.id.action_splashFragment_to_toDo_fragment)
    }
}