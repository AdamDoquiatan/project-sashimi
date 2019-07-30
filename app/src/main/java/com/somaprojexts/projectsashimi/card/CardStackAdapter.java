package com.somaprojexts.projectsashimi.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.somaprojexts.projectsashimi.R;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private Context context;
    private Card[] cards;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item_name);
            image = view.findViewById(R.id.item_image);
        }
    }

    public CardStackAdapter(Context context, Card[] cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public CardStackAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.name.setText(cards[position].getName());
        viewHolder.image.setImageDrawable(context.getDrawable(cards[position].getImage()));
    }

    @Override
    public int getItemCount() {
        return cards.length;
    }
}
