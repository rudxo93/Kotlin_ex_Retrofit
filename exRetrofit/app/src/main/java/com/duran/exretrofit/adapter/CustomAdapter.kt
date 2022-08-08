package com.duran.exretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duran.exretrofit.R
import com.duran.exretrofit.model.Post

class CustomAdapter(private val dataSet : ArrayList<Post>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val textView : TextView

        init {
            textView = view.findViewById(R.id.textView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {

        holder.textView.text = dataSet[position].title

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}