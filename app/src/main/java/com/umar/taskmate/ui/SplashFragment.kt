package com.umar.taskmate.ui

import android.os.Bundle
<<<<<<< HEAD
import android.os.Handler
import android.os.Looper
=======
>>>>>>> c47c5cd302be2098b43993ce0e06afb6b5399537
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.ProgressBar
=======
>>>>>>> c47c5cd302be2098b43993ce0e06afb6b5399537
import androidx.navigation.fragment.findNavController
import com.umar.taskmate.R
import com.umar.taskmate.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
<<<<<<< HEAD
    private lateinit var progressBar: ProgressBar
=======
>>>>>>> c47c5cd302be2098b43993ce0e06afb6b5399537
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
<<<<<<< HEAD
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
=======
        val navController = findNavController()
        view.postDelayed({
            navController.navigate(R.id.action_splashFragment_to_toDo_fragment)
        }, 3000)
    }


>>>>>>> c47c5cd302be2098b43993ce0e06afb6b5399537
}