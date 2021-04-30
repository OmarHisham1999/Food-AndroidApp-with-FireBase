package com.example.foodapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Interface.ItemClickListner;
import com.example.foodapp.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListner itemClickListner;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuName = (TextView) itemView.findViewById(R.id.Menu_name);
        imageView = (ImageView) itemView.findViewById(R.id.menu_image);


        itemView.setOnClickListener(this);


    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View view) {

        itemClickListner.onClick(view,getAdapterPosition(),false);


    }
}
