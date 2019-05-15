package com.example.cricscore

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

class MyAdapter1// Provide a suitable constructor (depends on the kind of dataset)
(private val mDataset: Int) : RecyclerView.Adapter<MyAdapter1.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var textView: TextView = v.findViewById(R.id.playersX) as TextView
        var editText: EditText = v.findViewById(R.id.players_X) as EditText

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter1.MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.players, parent, false)

        // Return a new holder instance
        return MyViewHolder(contactView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyAdapter1.MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // holder.textView.setText(mDataset[position]);
        holder.textView.text = "Player" + (position + 1)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset
    }
}
