package com.expandablerecyclerviewlibrary

import android.content.Context

class ExpandableBuilder {

    private lateinit var context: Context
    lateinit var parents: MutableList<Parent>
    var arrowUpImgResource: Int? = null
    var arrowDownImgResource: Int? = null
    private lateinit var adapter: ExpandableRecyclerviewAdapter

    fun setContext(context: Context): ExpandableBuilder {
        this.context = context
        return this
    }

    fun setData(parents: MutableList<Parent>): ExpandableBuilder {
        this.parents = parents
        return this
    }

    fun setArrowUpDrawable(arrowUpImgResource: Int): ExpandableBuilder {
        this.arrowUpImgResource = arrowUpImgResource
        return this
    }

    fun setArrowDownDrawable(arrowDownImgResource: Int): ExpandableBuilder {
        this.arrowDownImgResource = arrowDownImgResource
        return this
    }

    fun setChildColor(parentColor: Int): ExpandableBuilder {
        this.parents = parents
        return this
    }

    fun createAdapter(): ExpandableRecyclerviewAdapter {
        adapter = ExpandableRecyclerviewAdapter(context)
        adapter.parents = parents
        adapter.arrowUpImgResource = arrowUpImgResource
        adapter.arrowDownImgResource = arrowDownImgResource
        return adapter
    }

}