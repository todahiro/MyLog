package com.todahiro.mylog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.RealmResults
import java.text.SimpleDateFormat

class LogRecyclerViewAdapter(private val mValues: RealmResults<LogDB>) : RecyclerView.Adapter<LogRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.log_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewLogs.text = mValues[position]!!.strLog

        val df = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
        holder.textViewDate.text = df.format(mValues[position]!!.dateLog)
        // holder.textViewDate.text = mValues[position]!!.dateLog.toString()
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val textViewLogs : TextView
        val textViewDate : TextView

        init {
            textViewLogs = mView.findViewById<View>(R.id.log_textview) as TextView
            textViewDate = mView.findViewById<View>(R.id.date_textview) as TextView
        }
    }
}