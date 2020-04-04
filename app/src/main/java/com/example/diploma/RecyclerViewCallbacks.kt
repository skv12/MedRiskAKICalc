package com.example.diploma

import android.view.View
interface RecyclerViewCallbacks<T> {
    fun onItemClick(view: View, position: Int, item: T)
}