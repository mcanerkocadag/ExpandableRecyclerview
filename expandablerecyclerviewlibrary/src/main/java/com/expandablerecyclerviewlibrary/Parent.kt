package com.expandablerecyclerviewlibrary

class Parent {
    var title: String? = null
    var sub_title: String? = null
    var description: String? = null
    var text: String? = null
    var text2: String? = null
    var image: String? = null
    var isSelected: Boolean = false
    var expanded: Boolean = false
    var defaultColor: Int? = null
    var selectedColor: Int? = null
    var childs: MutableList<Child> = mutableListOf()

    constructor(
        title: String?,
        childs: MutableList<Child>
    ) : this() {
        this.title = title
        this.childs = childs
    }

    constructor()
}