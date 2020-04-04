package com.example.diploma

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val list: List<AppEntity>) : RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DataViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data: AppEntity = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

}

class DataViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {

    private var dId: TextView? = null
    private var dResult: TextView? = null

    init {
        dId = itemView.findViewById(R.id.list_id)
        dResult = itemView.findViewById(R.id.list_result)
    }

    fun bind(data: AppEntity) {
        dId?.text = "Решено: " + data.resultDate
        if(data.result < 50){
            dResult?.setTextColor(Color.GREEN)
            dResult?.text = "Низкий риск (" + data.result.toString() + ")"
        }
        else
        {
            dResult?.setTextColor(Color.RED)
            dResult?.text = "Высокий риск (" + data.result.toString() + ")"
        }
    }

}