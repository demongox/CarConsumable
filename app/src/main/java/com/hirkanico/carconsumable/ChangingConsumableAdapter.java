package com.hirkanico.carconsumable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hirkanico.carconsumable.classes.ChangingConsumableObject;
import com.hirkanico.carconsumable.classes.ClickListener;

import java.util.ArrayList;

public class ChangingConsumableAdapter extends RecyclerView.Adapter<ChangingConsumableHolder> {

    int rowIndex = -1;

    ArrayList<ChangingConsumableObject> changeConsumableList;
    ClickListener listener;
    Context context;

    public ChangingConsumableAdapter(Context context, ArrayList<ChangingConsumableObject> allConsumableLists, ClickListener listener) {

        this.changeConsumableList = allConsumableLists;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChangingConsumableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        View taskView = inflater.inflate(R.layout.show_consumable_change_time_layout, parent, false);

        return new ChangingConsumableHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChangingConsumableHolder holder, @SuppressLint("RecyclerView") int position) {

        final int index = holder.getAdapterPosition();

        holder.txtConsumableName.setText(changeConsumableList.get(position).consumableName);
        holder.txtPreviousKilometerChange.setText(changeConsumableList.get(position).previousChangeKilometer);
        holder.txtKilometerToChange.setText(changeConsumableList.get(position).kilometerToChange);
        holder.txtChangeDate.setText(changeConsumableList.get(position).changeDate);
        holder.txtChangingPrice.setText(changeConsumableList.get(position).changePrice);
        holder.txtDescription.setText(changeConsumableList.get(position).description);

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
        return changeConsumableList.size();
        //return 0;
    }
}
