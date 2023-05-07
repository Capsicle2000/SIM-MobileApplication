package com.example.sim;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    ArrayList<itemsDomain> items;
    DecimalFormat formatter;
    Context context;

    public ItemsAdapter(ArrayList<itemsDomain> items, Context context) {
        this.items = items;
        formatter = new DecimalFormat("###,###,###,###,##");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewholder, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.descriptionTxt.setText(items.get(position).getDescription());
        holder.dateTxt.setText(items.get(position).getDate());

        int drawable = holder.itemView.getResources().getIdentifier(items.get(position).getImage(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawable).into(holder.pic);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxt, descriptionTxt, dateTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            descriptionTxt = itemView.findViewById(R.id.descriptionTxt);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
