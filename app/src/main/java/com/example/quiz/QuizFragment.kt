package com.example.quiz
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.quiz.SelectionFragment.Companion.selectedC
import com.example.quiz.SelectionFragment.Companion.selectedD
import org.json.JSONArray


class QuizFragment : Fragment() {
    companion object{
        var score=0
    }
    var answer= ""
    var option4= ""
    var option3= ""
    var option2= ""
    var option1= ""
    var question= ""
    lateinit var tvQuestion: TextView
    lateinit var tvOption1: TextView
    lateinit var tvOption2: TextView
    lateinit var tvOption3: TextView
    lateinit var tvOption4: TextView
    lateinit var cvOption1: CardView
    lateinit var cvOption2: CardView
    lateinit var cvOption3: CardView
    lateinit var cvOption4: CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_quiz, container, false)
        var counter=0
        tvQuestion= view.findViewById(R.id.tvQuestion)
        tvOption1= view.findViewById(R.id.tvOption1)
        tvOption2= view.findViewById(R.id.tvOption2)
        tvOption3= view.findViewById(R.id.tvOption3)
        tvOption4= view.findViewById(R.id.tvOption4)
        cvOption1= view.findViewById(R.id.cvOption1)
        cvOption2= view.findViewById(R.id.cvOption2)
        cvOption3= view.findViewById(R.id.cvOption3)
        cvOption4= view.findViewById(R.id.cvOption4)


        load()

        cvOption1.setOnClickListener{
            load()
            counter++
            if(counter==10)
                Navigation.findNavController(view).navigate(R.id.quizToResult)
        }
        cvOption2.setOnClickListener{
            score++
            load()
            counter++
            if(counter==10)
                Navigation.findNavController(view).navigate(R.id.quizToResult)
        }
        cvOption3.setOnClickListener {
            load()
            counter++
            if(counter==10)
                Navigation.findNavController(view).navigate(R.id.quizToResult)
        }
        cvOption4.setOnClickListener {
            load()
            counter++
            if(counter==10)
                Navigation.findNavController(view).navigate(R.id.quizToResult)
        }

        return view
    }


    fun load(){
        val queue = Volley.newRequestQueue(requireContext())
        val category= selectedC
        val difficulty = selectedD
        val url = "https://opentdb.com/api.php?amount=1&category=$category&difficulty=$difficulty&type=multiple"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val jsonArray: JSONArray= response.getJSONArray("results")

                    val result = jsonArray.getJSONObject(0)
                    question= (result.getString("question"))
                    answer= (result.getString("correct_answer"))
                    val incorrect_answer= result.getJSONArray("incorrect_answers")
                    option1= (incorrect_answer.getString(0))
                    option2= (result.getString("correct_answer"))
                    option3= (incorrect_answer.getString(1))
                    option4= (incorrect_answer.getString(2))
                set()

            },
            { error ->
                    Log.d("error","Error")
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun set(){
        tvQuestion.text= question
        tvOption1.text=option1
        tvOption2.text=option2
        tvOption3.text=option3
        tvOption4.text=option4
    }
}