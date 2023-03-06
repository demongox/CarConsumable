package com.hirkanico.carconsumable;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChangingConsumableHolder extends RecyclerView.ViewHolder {

    public ImageView imgEngineIcon;

    public TextView txtConsumableName;
    public TextView txtPreviousKilometerChange;
    public TextView txtKilometerToChange;
    public TextView txtChangeDate;
    public TextView txtChangingPrice;
    public TextView txtDescription;

    public RelativeLayout rowLayout;
    View view;

    public ChangingConsumableHolder(@NonNull View itemView) {
        super(itemView);
        imgEngineIcon = (ImageView) itemView.findViewById(R.id.imgEngineIcon);

        txtConsumableName = (TextView) itemView.findViewById(R.id.txtConsumableName);
        txtPreviousKilometerChange = (TextView) itemView.findViewById(R.id.txtPreviousKilometerChange);
        txtKilometerToChange = (TextView) itemView.findViewById(R.id.txtKilometerToChange);
        txtChangeDate = (TextView) itemView.findViewById(R.id.txtChangeDate);
        txtChangingPrice = (TextView) itemView.findViewById(R.id.txtChangingPrice);
        txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);

        rowLayout = (RelativeLayout) itemView.findViewById(R.id.changeConsumableRowLayout);
        view = itemView;
    }
}
