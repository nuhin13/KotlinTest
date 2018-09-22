package com.pranay.kotlinroomdbtodo.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.item_prefer_time.view.*
import test.kotlin.nuhin13.kotlintest.R
import test.kotlin.nuhin13.kotlintest.api.response.Time
import kotlin.coroutines.experimental.CoroutineContext

class PreferTimeAdapter : RecyclerView.Adapter<PreferTimeAdapter.TaskViewHolder>() {

    lateinit var tasks: List<Time>
    lateinit var mItemClickListener: MyAdapterListener

    private lateinit var context: CoroutineContext

    override fun onCreateViewHolder(parent: android.view.ViewGroup, type: Int): TaskViewHolder {
        return TaskViewHolder(parent)
    }

    fun setData(tasks: List<Time>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: PreferTimeAdapter.TaskViewHolder, position: Int) {
        viewHolder.bind(tasks[position], position)
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(parent: android.view.ViewGroup) :
            RecyclerView.ViewHolder(android.view.LayoutInflater.from(parent.context).
                    inflate(R.layout.item_prefer_time, parent, false)) {

        fun bind(task: Time, position: Int): Unit = with(itemView) {

            if(task.isValid == "1"){
                tv_prefer_time.visibility = View.VISIBLE
                tv_prefer_time.setText(task.value);
            }else{
                tv_prefer_time.visibility = View.GONE
            }

            itemView.setOnClickListener(View.OnClickListener {
                Log.e("Click", position.toString())
                Toast.makeText(context, position.toString(), Toast.LENGTH_LONG).show()
            })
        }
    }

     fun setOnItemClick(itemClickListener: MyAdapterListener): Unit {
        this.mItemClickListener = itemClickListener
    }

    interface MyAdapterListener {
        fun onItemViewClick(webUrl:String,position: Int)
    }
}