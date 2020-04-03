package com.projects.bargavsai.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static com.projects.bargavsai.myapplication.MainActivity.adapter;
import static com.projects.bargavsai.myapplication.MainActivity.items;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Tasks> items;
    private int itemLayout;

    private List<String> mData;
    private List<String> mData1;
    private List<String> mData2;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    RecyclerAdapter(MainActivity context, List<Tasks> items){
        this.mInflater = LayoutInflater.from((context));
        this.items = items;
        // this.itemLayout = itemLayout;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.subtasks, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Tasks item = items.get(position);
        holder.myTextView.setText(item.getTitle());
        holder.myTextView1.setText(item.getDescr());
        holder.myTextView2.setText(item.getDd());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        int size = items.size();
        items.clear();
        adapter.notifyItemRangeRemoved(0,size);
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView myTextView1;
        TextView myTextView2;

        ViewHolder(View itemView) {
            super(itemView);
            this.myTextView = itemView.findViewById(R.id.view_tasks);
            this.myTextView1 = itemView.findViewById(R.id.view_date);
            this.myTextView2 = itemView.findViewById(R.id.view_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData2.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}