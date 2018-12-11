package com.projectxr.mehmetd.bulentbey;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.projectxr.mehmetd.bulentbey.Models.KitapItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KitapAdapter extends RecyclerView.Adapter<KitapAdapter.ViewHolder> {
    List<KitapItem> kitapItems;
    public OnAdapterClickListener onAdapterClickListener;
    private Context context;
    String id;

    public KitapAdapter(List<KitapItem> kitapItems, Context context, OnAdapterClickListener onAdapterClickListener) {
        this.kitapItems = kitapItems;
        this.context = context;
        this.onAdapterClickListener = onAdapterClickListener;
    }


    @NonNull
    @Override
    public KitapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_kitap,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KitapAdapter.ViewHolder viewHolder, final int i) {
        final KitapItem kitapItem =kitapItems.get(i);
        viewHolder.textViewImage.setText(kitapItem.getKitapAdı());


       Picasso.get().load(kitapItem.getFotoUrl()).into(viewHolder.imageView);
        id="" + kitapItem.getId();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdapterClickListener.onItemClickListener(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kitapItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewImage;
        public ImageView imageView;
        public RelativeLayout relativeLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewImage = itemView.findViewById(R.id.kitap_adı);
            imageView= itemView.findViewById(R.id.image);
/*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context,PartDinle.class);
                    i.putExtra("position",i);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
            */
        }
    }
}
