package com.hirkanico.carconsumable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hirkanico.carconsumable.classes.CarConsumableObject;
import com.hirkanico.carconsumable.classes.ClickListener;

import java.util.ArrayList;

public class AllConsumableAdapter extends RecyclerView.Adapter<AllConsumableHolder> {

    int rowIndex = -1;

    ArrayList<CarConsumableObject> allConsumableList;
    ClickListener listener;
    Context context;

    public AllConsumableAdapter(Context context, ArrayList<CarConsumableObject> allConsumableLists, ClickListener listener) {

        this.allConsumableList = allConsumableLists;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AllConsumableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        Log.v("Adapter all" , "all");

        // Inflate the layout
        View taskView = inflater.inflate(R.layout.show_all_consumable_layout, parent, false);

        return new AllConsumableHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllConsumableHolder holder, @SuppressLint("RecyclerView") int position) {

        final int index = holder.getAdapterPosition();

        //Log.v("Position", String.valueOf(position));

        holder.txtConsumableName.setText(allConsumableList.get(position).pieceName);
        holder.txtBestKilometerToChange.setText(allConsumableList.get(position).bestKilometerToChange);
        holder.imgEngineIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowIndex = position;
                //listener.click(index, "delete");
            }
        });

        if (position%2 == 0)
            holder.rowLayout.setBackgroundColor(Color.parseColor("#d5dbdb"));
        else
            holder.rowLayout.setBackgroundColor(Color.parseColor("#cacfd2"));
    }

    @Override
    public int getItemCount() {
        return allConsumableList.size();

        //********************************  return zero must be commented
        //return 0;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
