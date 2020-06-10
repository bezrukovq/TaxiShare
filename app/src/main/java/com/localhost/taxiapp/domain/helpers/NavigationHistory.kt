package com.localhost.taxiapp.domain.helpers

import java.util.*

class NavigationHistory {

    private var selectedPages: Stack<Int> = Stack()

    fun pushItem(item: Int) {
        // remove if already was selected, move it to front
    //    selectedPages.remove(item)
        selectedPages.push(item)
    }

    fun onBackPressed(): Int {
        if(selectedPages.size>1) {
            selectedPages.pop()
            return selectedPages.peek()
        }
        return selectedPages.peek()
    }

    fun size() = selectedPages.size
}
