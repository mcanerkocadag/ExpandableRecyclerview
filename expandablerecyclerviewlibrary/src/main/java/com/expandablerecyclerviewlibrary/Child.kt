package com.expandablerecyclerviewlibrary

class Child {
    var title: String? = null
    var sub_title: String? = null
    var description: String? = null
    var text: String? = null
    var text2: String? = null
    var image: String? = null
    val selected: Boolean = false
    var childs: MutableList<Child> = mutableListOf()

    constructor(
        title: String?
    ) : this() {
        this.title = title
    }

    constructor(
        title: String?,
        childs: MutableList<Child>
    ) : this() {
        this.title = title
        this.childs = childs
    }

    constructor()
}