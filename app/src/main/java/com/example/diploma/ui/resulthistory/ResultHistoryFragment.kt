package com.example.diploma.ui.resulthistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.diploma.*
import com.example.diploma.ui.main.MainFragment
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.window_resulthistory.*
import kotlinx.android.synthetic.main.window_resulthistory.view.*


class ResultHistoryFragment : Fragment(){
    internal interface OnItemClick {
        fun onItemClickSelected(Id: Int)
    }
    private lateinit var manager: FragmentManager
    private lateinit var transaction: FragmentTransaction
    private lateinit var communicator: Communicator
    private lateinit var resultHistoryViewModel: ResultHistoryViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultHistoryViewModel =
            ViewModelProviders.of(this).get(ResultHistoryViewModel::class.java)
        var root:View = inflater.inflate(R.layout.window_resulthistory,container, false)
        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "app_db"
        ).allowMainThreadQueries().build()

        communicator = ViewModelProviders.of(this).get(Communicator::class.java)
        root.clearButton.setOnClickListener{
            db.appDao().deleteAll()
            resultHistoryView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ListAdapter(AppDatabase.invoke(requireContext()).appDao().getAll())
            }
        }
        /*root.resultHistoryView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val tempId = list_id.text.toString().toInt()
                val listener: OnItemClick = activity as OnItemClick
                listener.onItemClickSelected(tempId)
            }
        })
        root.resultHistoryView.addOnItemLongClickListener(object : OnItemLongClickListener{
            override fun onItemLongClicked(position: Int, view: View) {
                val tempId = list_id.text.toString().toInt()
                db.appDao().deleteItem(tempId)
                resultHistoryView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = ListAdapter(AppDatabase.invoke(requireContext()).appDao().getAll())
                }
            }
        })*/

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultHistoryView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(AppDatabase.invoke(requireContext()).appDao().getAll())
        }
    }
    private fun initFragment(){
        transaction = manager.beginTransaction()
        transaction.add(R.id.container, MainFragment())
        transaction.commit()
    }
}