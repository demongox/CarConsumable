package com.hirkanico.carconsumable;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllConsumableHolder extends RecyclerView.ViewHolder {

    public ImageView imgEngineIcon;
    public TextView txtConsumableName;
    public TextView txtBestKilometerToChange;
    public RelativeLayout rowLayout;
    View view;

    public AllConsumableHolder(@NonNull View itemView) {
        super(itemView);
        imgEngineIcon = (ImageView) itemView.findViewById(R.id.imgEngineIcon);
        txtConsumableName = (TextView) itemView.findViewById(R.id.txtConsumableName);
        txtBestKilometerToChange = (TextView) itemView.findViewById(R.id.txtBestKilometerToChange);
        rowLayout = (RelativeLayout) itemView.findViewById(R.id.allConsumableRowLayout);
        view = itemView;
    }
}
