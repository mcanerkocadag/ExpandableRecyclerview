package com.expandablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.expandablerecyclerview.databinding.ActivityMainBinding
import com.expandablerecyclerviewlibrary.Child
import com.expandablerecyclerviewlibrary.ExpandableRecyclerviewAdapter
import com.expandablerecyclerviewlibrary.Parent
import androidx.recyclerview.widget.SimpleItemAnimator
import com.expandablerecyclerviewlibrary.ExpandableBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var parents: MutableList<Parent>
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initValues()

        val adapter =
            ExpandableBuilder()
                .setContext(this)
                .setData(parents)
                .createAdapter()
        adapter.parentListener = object : ExpandableRecyclerviewAdapter.ParentListener {
            override fun notifySelected(parent: Parent, parent_position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    "Parent : " + parent.title + " Position: " + parent_position,
                    Toast.LENGTH_LONG
                ).show()

                logMessage(adapter)
            }
        }
        (binding?.recyclerview?.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        binding?.recyclerview?.adapter = adapter

        binding?.recyclerview?.setHasFixedSize(true)
        binding?.recyclerview?.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        binding?.executePendingBindings()

    }

    private fun initValues() {
        parents = mutableListOf()
        val child1 = Child("child 1")
        val child2 = Child("child 2")
        val child3 = Child("child 3")
        val child4 = Child("child 4")
        val child5 = Child("child 5")
        val childs = mutableListOf<Child>()
        childs.add(child1)
        childs.add(child2)
        childs.add(child3)
        childs.add(child4)
        childs.add(child5)
        val parent1 = Parent("Parent 1", childs)

        val childs2 = mutableListOf<Child>()
        childs2.add(child1)
        childs2.add(child2)
        childs2.add(child3)
        childs2.add(child4)
        val parent2 = Parent("Parent 2", childs2)

        val childs3 = mutableListOf<Child>()
        childs3.add(child1)
        childs3.add(child2)
        childs3.add(child3)
        val parent3 = Parent("Parent 3", childs3)

        val childs4 = mutableListOf<Child>()
        childs4.add(child1)
        childs4.add(child2)
        childs4.add(child3)
        childs4.add(child4)
        val parent4 = Parent("Parent 4", childs4)

        val childs5 = mutableListOf<Child>()
        childs5.add(child1)
        childs5.add(child2)
        childs5.add(child3)
        childs5.add(child4)
        val parent5 = Parent("Parent5", childs5)

        parents.add(parent1)
        parents.add(parent2)
        parents.add(parent3)
        parents.add(parent4)
        parents.add(parent5)
    }

    private fun logMessage(adapter: ExpandableRecyclerviewAdapter) {
        Log.i(TAG, "Selected Items : \n")
        adapter.getSelectedParents().forEach {

            Log.i(TAG, "Selected Parent : " + it.title + " Selected: " + it.isSelected)
        }
        Log.i(TAG, "----------------------------")
    }
}
