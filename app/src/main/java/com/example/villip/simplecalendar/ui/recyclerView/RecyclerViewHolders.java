package com.example.villip.simplecalendar.ui.recyclerView;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.villip.simplecalendar.R;
import com.example.villip.simplecalendar.ui.ListEventsActivity;
import com.example.villip.simplecalendar.ui.MainContentFragment;


public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public FrameLayout frameLayout;
    public TextView everyDay;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        frameLayout = (FrameLayout) itemView.findViewById(R.id.content_every_day);
        everyDay = (TextView)itemView.findViewById(R.id.every_day);

    }

    @Override
    public void onClick(View view) {
       // Context context = view.getContext();
        Intent intent = new Intent(view.getContext(), ListEventsActivity.class);
        intent.putExtra("key_day", everyDay.getText().toString() + " " + MainContentFragment.staticMonthYear);
        view.getContext().startActivity(intent);

    }
}