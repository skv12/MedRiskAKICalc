package com.example.diploma

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.Toast

interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}
interface OnItemLongClickListener{
    fun onItemLongClicked(position: Int, view: View)
}
fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }
    })
}
fun RecyclerView.addOnItemLongClickListener(onClickListener: OnItemLongClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view.setOnLongClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view.setOnLongClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemLongClicked(holder.adapterPosition, view)
                return@setOnLongClickListener true
            }
        }
    })
}
// Usage:
