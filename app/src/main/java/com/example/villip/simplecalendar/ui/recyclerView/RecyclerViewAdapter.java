package com.example.villip.simplecalendar.ui.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.villip.simplecalendar.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<ItemObject> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<ItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {


        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_every_day, null);
        RecyclerViewHolders recyclerViewHolders = new RecyclerViewHolders(layoutView);
        return recyclerViewHolders;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {

        int date = itemList.get(position).getDate();
        boolean dayToday = itemList.get(position).isDayToday();
        boolean dayWithNote = itemList.get(position).isDayWithNote();

        if(date == 0){
            holder.frameLayout.setVisibility(View.INVISIBLE);
        } else {
            holder.frameLayout.setVisibility(View.VISIBLE);
        }

        String date_string = Integer.toString(date);
        holder.everyDay.setText(date_string);

        //Проверка на день сегодняшний
        if(dayToday){
            holder.everyDay.setBackgroundResource(R.drawable.disk);
        } else if(dayWithNote){
            holder.everyDay.setBackgroundResource(R.drawable.ring);
        }

        //holder.everyDay.setBackgroundResource(R.drawable.ring);
        //holder.note.setImageResource(itemList.get(position).getImg_note());

    }

        @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
