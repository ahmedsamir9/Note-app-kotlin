package com.example.noteapp.View

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.noteapp.Model.Note

import com.example.noteapp.R
import com.example.noteapp.ViewModel.AddNoteViewModel

class AddNoteFragment : Fragment() ,View.OnClickListener{
    lateinit var navController: NavController
    companion object {
        fun newInstance() = AddNoteFragment()
    }

    private lateinit var viewModel: AddNoteViewModel
  lateinit var title:EditText
    lateinit var dec:EditText
    lateinit var per :EditText
    lateinit var btn :Button
     var note : Note? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_note_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val arg = arguments?.let { AddNoteFragmentArgs.fromBundle(it) }
        note =arg?.note
        title = view.findViewById<EditText>(R.id.etTitle)
        dec = view.findViewById<EditText>(R.id.etedesc)
        per = view.findViewById<EditText>(R.id.etper)
        btn = view.findViewById<Button>(R.id.button)
        if(note != null){
            title.setText(note?.title)
            dec.setText(note?.description)
            per.setText(note?.importance.toString())
        }
        btn.setOnClickListener {
            if(note != null){
                note!!.title = title.text.toString()
                note!!.description =dec.text.toString()

                val string = per.text.toString()
                note!!.importance =string.toInt()

            }
            else{
                val string = per.text.toString()
                note = Note(0,title.text.toString(),dec.text.toString(),string.toInt())
            }
            viewModel.addNote(note!!)
            Toast.makeText(view.context,"added",Toast.LENGTH_SHORT).show()
           navController.popBackStack()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button->{

                if(note != null){
                    note!!.title = title.text.toString()
                    note!!.description =dec.text.toString()

                    val string = per.text.toString()
                    note!!.importance =string.toInt()

                }
                else{
                    val string = per.text.toString()
                    note = Note(0,title.text.toString(),dec.text.toString(),string.toInt())
                }
                viewModel.addNote(note!!)
                Toast.makeText(view!!.context,"added",Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }
    }

}
