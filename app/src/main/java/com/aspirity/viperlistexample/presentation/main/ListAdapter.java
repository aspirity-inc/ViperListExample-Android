package com.aspirity.viperlistexample.presentation.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspirity.viperlistexample.R;
import com.aspirity.viperlistexample.data.Item;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import carbon.widget.CardView;
import carbon.widget.ImageView;
import carbon.widget.RecyclerView;
import carbon.widget.TextView;

/**
 * Created by namtarr on 05.09.16.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder, Item> {

    private List<Item> items;
    private Context context;
    private LayoutInflater inflater;

    public ListAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setItems(List<Item> items, boolean refresh) {
        if (refresh) {
            this.items = new ArrayList<>();
        }
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.populateViewWithData(position);
    }

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {

        @Bind(R.id.card_item_image)
        ImageView imageView;
        @Bind(R.id.card_item_title)
        TextView textView;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            ButterKnife.bind(this, view);
        }

        public void populateViewWithData(int position) {
            Item item = getItem(position);
            Glide.with(context).load(item.getThumbnailUrl()).centerCrop().into(imageView);
            textView.setText(item.getName());
        }
    }
}
