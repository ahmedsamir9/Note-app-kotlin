package com.example.noteapp.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.daimajia.swipe.SimpleSwipeListener
import com.daimajia.swipe.SwipeLayout
import com.example.noteapp.Model.Note
import com.example.noteapp.R
import net.cachapa.expandablelayout.ExpandableLayout


class noteAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var data:MutableList<Note?>? =null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return noteViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.noteitem,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is noteViewholder -> {
                holder.bind(data!!.get(position)!!)
            }
        }
    }

    override fun getItemCount(): Int {
        if(data == null)return 0
        return data!!.size
    }

    fun submitList(list: MutableList<Note?>?) {
        this.data=list

    }
    fun onChange(list: MutableList<Note?>?){
        this.data=list
        notifyDataSetChanged()
    }
    class noteViewholder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {
            val title = itemView.findViewById<TextView>(R.id.tasktitle)
            val decs = itemView.findViewById<TextView>(R.id.dec)
            val periortyImg = itemView.findViewById<ImageView>(R.id.pero)
            val expandableLayout = itemView.findViewById<ExpandableLayout>(R.id.expandable_layout)
            val swipeLayout = itemView.findViewById<SwipeLayout>(R.id.swipe)
            val editBtn = itemView.findViewById<ImageView>(R.id.tvedit)
            val deleteBtn = itemView.findViewById<ImageView>(R.id.tvDelete)
        fun bind(item: Note) = with(itemView) {
            title.text =item.title
            decs.text =item.description
            when(item.importance){
                0 -> periortyImg.setImageResource(R.drawable.lowcircle)
                1 ->periortyImg.setImageResource(R.drawable.middlecicle)
                2->periortyImg.setImageResource(R.drawable.circle)
            }
            onClicks(item)
        }
        fun onClicks(item:Note){
            val onSwipe = object : SimpleSwipeListener() {
                override fun onOpen(layout: SwipeLayout?) {
                    super.onOpen(layout)
                }
            }
            swipeLayout.apply {
                showMode = SwipeLayout.ShowMode.LayDown
                addSwipeListener(onSwipe)

            }
            title.setOnClickListener{
                expandableLayout.toggle()
            }
            editBtn.setOnClickListener{
                interaction?.onClickupdate(adapterPosition,item)
            }
            deleteBtn.setOnClickListener {
                interaction?.onClickDelete(adapterPosition,item)
            }

        }


    }

    interface Interaction {

        fun onClickupdate(position: Int, item: Note)
        fun onClickDelete(position: Int, item: Note)
    }
}
