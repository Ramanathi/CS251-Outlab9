package com.projects.bargavsai.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.projects.bargavsai.myapplication.MainActivity.adapter;
import static com.projects.bargavsai.myapplication.MainActivity.item;
import static com.projects.bargavsai.myapplication.MainActivity.items;
import static com.projects.bargavsai.myapplication.MainActivity.mySqliteOpenHelper;
import static com.projects.bargavsai.myapplication.MainActivity.pos;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Tasks> items;
    private int itemLayout;

    private List<String> mData;
    private List<String> mData1;
    private List<String> mData2;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    static boolean life = false;
    RecyclerView recyclerView;
    Context context;
    static int p;

    // data is passed into the constructor
//    MyRecyclerViewAdapter(MainActivity context, ArrayList<String> data, ArrayList<String> data1, ArrayList<String> data2) {
//        this.mInflater = LayoutInflater.from(context);
//        this.mData = data;
//        this.mData1 = data1;
//        this.mData2 = data2;
//    }

    MyRecyclerViewAdapter(MainActivity context, List<Tasks> items){
        this.mInflater = LayoutInflater.from((context));
        this.items = items;
       // this.itemLayout = itemLayout;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tasks item = items.get(position);
        holder.myTextView.setText(item.getTitle());
        holder.myTextView1.setText(item.getDd());
        holder.myTextView2.setText(item.getDescr());
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
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    p=getLayoutPosition();
                    System.out.println("LongClick: "+p);
                    recyclerView = MainActivity.recyclerView;

                    life = true;

                    MainActivity.getInstance().fun(p);
                    //((MainActivity)getActivity()).fun(p);
                    //context = MainActivity.context;
                    //adapter = adapter;
                    //RecyclerView recyclerView1 = findViewById(R.id.my_recycler_view);
                    //recyclerView.setLayoutManager(new LinearLayoutManager(this));

                    //adapter.clear();

                    //Tasks item = adapter.getItem(p);

                    //items = mySqliteOpenHelper.getData_title(item.getTitle());

                    //Toast.makeText(this, "You clicked " + adapter.getItem(p).getTitle() + " on row number " + p, Toast.LENGTH_SHORT).show();

                    //adapter = new MyRecyclerViewAdapter(MainActivity.context,items);
                    //adapter.setClickListener(context);
                    //recyclerView.setAdapter(adapter);
                    return true;// returning true instead of false, works for me
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Tasks getItem(int id) {
        return items.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

//    void myfun(View view, int position){
//        ViewHolder holder = onCreateViewHolder(view,position);
//         //ViewHolder holder = ViewHolder(view);
//        String desc = getItem(position);
//        holder.myTextView2.setText(desc);
//    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void removeItem(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void swapItems(List<Tasks> items){
        this.items.clear();
        this.items = items;
        notifyDataSetChanged();
    }
}