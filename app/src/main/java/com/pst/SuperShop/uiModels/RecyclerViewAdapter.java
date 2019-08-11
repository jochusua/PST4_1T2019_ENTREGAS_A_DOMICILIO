package com.pst.SuperShop.uiModels;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pst.SuperShop.R;
import com.pst.SuperShop.models.ItemFromStore;

import java.util.List;

/**
 * Un adapter para habilitar la multi selección, provee al contador un estado
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<ItemFromStore> mItemFromStoreList;

    public RecyclerViewAdapter(List<ItemFromStore> itemFromStoreList) {
        mItemFromStoreList = itemFromStoreList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ItemFromStore itemFromStore = mItemFromStoreList.get(position);
        holder.itemPriceTextview.setText(itemFromStore.getItemPrice());
        holder.view.setBackgroundColor(itemFromStore.isSelected() ? Color.CYAN : Color.WHITE);
        holder.itemPriceTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemFromStore.setSelected(!itemFromStore.isSelected());
                holder.view.setBackgroundColor(itemFromStore.isSelected() ? Color.YELLOW : Color.WHITE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemFromStoreList == null ? 0 : mItemFromStoreList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView itemPriceTextview;
        private TextView itemDescTextview;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            itemPriceTextview = (TextView) itemView.findViewById(R.id.item_price);
            itemDescTextview = (TextView) itemView.findViewById(R.id.item_desc);
        }
    }
}
