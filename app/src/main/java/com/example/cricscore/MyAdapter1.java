package com.example.cricscore;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {
    private int mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public EditText editText;
        public MyViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.playersX);
            editText = (EditText) v.findViewById(R.id.players_X);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter1(int myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.players, parent, false);

        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(contactView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyAdapter1.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       // holder.textView.setText(mDataset[position]);
        holder.textView.setText("Player"+(position+1));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset;
    }
}
