package com.raghavi.movieapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
 * Created by Raghavi on 4/1/2018.
 */

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private ItemData[] data;

    public adapter(ItemData[] data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_single, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.listItemTextView.setText(data[position].getDescription());
        holder.listItemImageView.setImageResource(data[position].getImgId());
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView listItemImageView;
        public TextView listItemTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            this.listItemImageView = (ImageView) itemView.findViewById(R.id.list_item_image_view);
            this.listItemTextView = (TextView) itemView.findViewById(R.id.list_item_text_view);


        }
    }
}
