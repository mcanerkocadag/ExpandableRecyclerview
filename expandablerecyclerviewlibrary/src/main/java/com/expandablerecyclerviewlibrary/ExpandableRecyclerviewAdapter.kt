package com.expandablerecyclerviewlibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.expandablerecyclerviewlibrary.databinding.ParentItemBinding

class ExpandableRecyclerviewAdapter(ctx: Context) :
    RecyclerView.Adapter<ExpandableRecyclerviewAdapter.MyViewHolder>() {

    private var context: Context = ctx
    lateinit var parentListener: ParentListener
    lateinit var parents: MutableList<Parent>
    var arrowUpImgResource: Int? = null
    var arrowDownImgResource: Int? = null

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(
            parents,
            position,
            parentListener,
            context,
            arrowUpImgResource,
            arrowDownImgResource
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return from(parent)
    }

    class MyViewHolder(private val binding: ParentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var parent: Parent
        private lateinit var context: Context

        fun bind(
            items: MutableList<Parent>,
            position: Int,
            parentListener: ParentListener,
            ctx: Context,
            arrowImgResource: Int?,
            arrowDownImgResource: Int?
        ) {
            context = ctx
            parent = items[position]
            binding.title.text = parent.title
            binding.root.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    if (parent.isSelected)
                        if (parent.selectedColor == null)
                            R.color.colorPrimary
                        else parent.selectedColor!!
                    else
                        if (parent.defaultColor == null)
                            R.color.white
                        else
                            parent.defaultColor!!
                )
            )

            val childAdapter = ChildRecyclerviewAdapter(context)
            childAdapter.childListener = object : ChildRecyclerviewAdapter.ChildListener {
                override fun onChildClick(child_position: Int, child: Child) {
                    parentListener.onChildClick(position, child_position, parent, child)
                }

                override fun onChildLongClick(child_position: Int, child: Child) {
                    parentListener.onChildLongClick(position, child_position, parent, child)
                }
            }
            childAdapter.childs = parent.childs
            binding.childRecyclerview.adapter = childAdapter

            binding.container.setOnClickListener {
                selected(parentListener)
                parentListener.onParentClick(position, parent)
            }

            binding.root.setOnLongClickListener {
                selected(parentListener)
                true
            }

            expanded(arrowImgResource, arrowDownImgResource)
        }

        private fun selected(parentListener: ParentListener) {
            parent.isSelected = !parent.isSelected
            binding.root.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    if (parent.isSelected)
                        if (parent.selectedColor == null)
                            R.color.colorPrimary
                        else parent.selectedColor!!
                    else
                        if (parent.defaultColor == null)
                            R.color.white
                        else parent.defaultColor!!
                )
            )

            if (parent.isSelected)
                parentListener.notifySelected(parent)
        }

        private fun expanded(arrowUpImgResource: Int?, arrowDownImgResource: Int?) {
            if (parent.expanded) {
                binding.childRecyclerview.visibility = View.VISIBLE
                binding.arrowImg.background =
                    ContextCompat.getDrawable(context, arrowUpImgResource ?: R.drawable.ic_arrow_up)
            } else {
                binding.childRecyclerview.visibility = View.GONE
                binding.arrowImg.background =
                    ContextCompat.getDrawable(
                        context,
                        arrowDownImgResource ?: R.drawable.ic_arrow_down
                    )
            }

            binding.collapsingBtn.setOnClickListener {
                if (binding.childRecyclerview.isVisible) {
                    binding.childRecyclerview.visibility = View.GONE
                    binding.arrowImg.background =
                        ContextCompat.getDrawable(
                            context,
                            arrowDownImgResource ?: R.drawable.ic_arrow_down
                        )
                    parent.expanded = false
                } else {
                    binding.childRecyclerview.visibility = View.VISIBLE
                    binding.arrowImg.background =
                        ContextCompat.getDrawable(
                            context,
                            arrowUpImgResource ?: R.drawable.ic_arrow_up
                        )
                    parent.expanded = true
                }
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): MyViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val dataBinding = ParentItemBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(dataBinding)
        }
    }

    interface ParentListener {

        fun notifySelectedList(selectedList: MutableList<Parent>) {
        }

        fun notifySelected(selectedList: Parent) {
        }

        fun onParentClick(
            position: Int,
            parent: Parent
        ) {
        }

        fun onParentLongClick(
            position: Int,
            parent: Parent
        ) {
        }

        fun onChildClick(
            parent_position: Int,
            child_position: Int,
            parent: Parent,
            child: Child
        ) {
        }

        fun onChildLongClick(
            parent_position: Int,
            child_position: Int,
            parent: Parent,
            child: Child
        ) {
        }
    }
}