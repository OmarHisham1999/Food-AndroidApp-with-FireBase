package com.example.foodapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Interface.ItemClickListner;
import com.example.foodapp.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView food_name;
    public ImageView food_image;

    private ItemClickListner itemClickListner;

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        food_name = (TextView) itemView.findViewById(R.id.food_name);
        food_image = (ImageView) itemView.findViewById(R.id.food_image);


        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        itemClickListner.onClick(view,getAdapterPosition(),false);
    }
}
