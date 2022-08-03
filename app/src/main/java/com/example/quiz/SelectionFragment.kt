package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation


class SelectionFragment : Fragment() {

    private var arrayAdapter: ArrayAdapter<String>?= null
    private var arrayAdapter2: ArrayAdapter<String>?= null
    private val difficulty= arrayOf("Easy", "Medium", "Hard")
    companion object{
        private val category = arrayOf("General Knowledge", "Books", "Film", "Music", "Musicals & Theatre", "Television", "Video Games", " Board Games", "Science & Nature",
            "Computers", "Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Arts", "Celebrities", "Animals","Vehicles","Comics","Gadgets",
            "Japanese Anime & Manga", "Animation & Cartoon")
        var selectedD: String= "Easy"
        var selectedC: String= "General Knowledge"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_selection, container, false)

        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val spinner2= view.findViewById<Spinner>(R.id.spinner2)
        val start: Button= view.findViewById(R.id.btnStart)
        start.setOnClickListener { Navigation.findNavController(view).navigate(R.id.selectionToQuiz) }
        arrayAdapter= ArrayAdapter<String>(requireContext(),android.R.layout.select_dialog_item,category)
        arrayAdapter2 = ArrayAdapter<String>(requireContext(),android.R.layout.select_dialog_item,difficulty)
        spinner2.adapter= arrayAdapter2
        spinner.adapter= arrayAdapter
        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val item= p0!!.getItemAtPosition(p2)
                selectedC= item.toString()
                selectedC()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        spinner2.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val item= p0!!.getItemAtPosition(p2)
                selectedD=item.toString().lowercase()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        return view
    }
    private fun selectedC(){
        var x=9
        for(i in category){
            if(selectedC==i){
                break
            }
            x++
        }
        selectedC=x.toString()
    }
}


