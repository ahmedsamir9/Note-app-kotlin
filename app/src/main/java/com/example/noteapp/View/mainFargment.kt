package com.example.noteapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Adapter.noteAdapter
import com.example.noteapp.Model.Note
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel


class mainFargment : Fragment() {

    companion object {
        fun newInstance() = mainFargment()
    }
   
    private val noteViewModel: NoteViewModel by viewModel<NoteViewModel>()
    private lateinit var listadapter:noteAdapter
   private lateinit var  recyclerView:RecyclerView
    lateinit var fab:FloatingActionButton
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fargment_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        fab = view.findViewById(R.id.fab)
        recyclerView  =view.findViewById<RecyclerView>(R.id.rvNotes)
        fab.setOnClickListener{
            val action =mainFargmentDirections.actionMainFargmentToAddNoteFragment(null)
            navController.navigate(action)
        }
    }

    override fun onStart() {
        super.onStart()
        noteViewModel.loadNotes()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(view!!.context)
            listadapter = noteAdapter(clicks)
            listadapter.submitList(noteViewModel.notes.value)
            adapter =listadapter
        }
        subscribeToLiveData()
    }
    private fun subscribeToLiveData(){
        noteViewModel.notes.observe(this, Observer {
            listadapter.onChange(it)
        })
    }
    val clicks =object :noteAdapter.Interaction{
        override fun onClickupdate(position: Int, item: Note) {
            val action =mainFargmentDirections.actionMainFargmentToAddNoteFragment(item)
            navController.navigate(action)

        }

        override fun onClickDelete(position: Int, item: Note) {

            noteViewModel.deleteNOte(item)
        }

    }
}
