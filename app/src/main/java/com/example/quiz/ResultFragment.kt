package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.quiz.QuizFragment.Companion.score


class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentScore= score
        val view= inflater.inflate(R.layout.fragment_result, container, false)
        val tvScore= view.findViewById<TextView>(R.id.score)
        val btnReplay=view.findViewById<Button>(R.id.btnRestart)
        val btnEnd = view.findViewById<Button>(R.id.btnEnd)
        tvScore.text= "Your score : $currentScore"
        btnReplay.setOnClickListener {
             score=0
            Navigation.findNavController(view).navigate(R.id.resultToQuiz)
        }
        btnEnd.setOnClickListener {
            requireActivity().finishAffinity()
            System.exit(0)
        }
        return view
    }


}