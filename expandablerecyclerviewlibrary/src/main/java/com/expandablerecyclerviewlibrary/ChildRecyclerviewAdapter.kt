package com.expandablerecyclerviewlibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.expandablerecyclerviewlibrary.databinding.ChildItemBinding

class ChildRecyclerviewAdapter(ctx: Context) :
    RecyclerView.Adapter<ChildRecyclerviewAdapter.MyViewHolder>() {

    var context: Context = ctx
    lateinit var childListener: ChildListener
    lateinit var childs: MutableList<Child>
    override fun getItemCount(): Int {
        return childs.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(childs, position, childListener, context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return from(parent)
    }

    class MyViewHolder(private val binding: ChildItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            items: MutableList<Child>,
            position: Int,
            childListener: ChildListener,
            context: Context)
        {
            val item = items[position]
            if(item.title != null){
                binding.title.visibility = View.VISIBLE
                binding.title.text = item.title
            }

            if(item.sub_title != null){
                binding.subTitle.visibility = View.VISIBLE
                binding.subTitle.text = item.sub_title
            }

            if(item.description != null){
                binding.description.visibility = View.VISIBLE
                binding.description.text = item.description
            }

            binding.root.setOnClickListener {
                childListener.onChildClick(position,item)
            }

            binding.root.setOnLongClickListener {
                childListener.onChildLongClick(position,item)
                true
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): MyViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val dataBinding = ChildItemBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(dataBinding)
        }
    }

    interface ChildListener {
        fun onChildClick(
            child_position: Int,
            child: Child
        ){}
        fun onChildLongClick(
            child_position: Int,
            child: Child
        ){}
    }
}