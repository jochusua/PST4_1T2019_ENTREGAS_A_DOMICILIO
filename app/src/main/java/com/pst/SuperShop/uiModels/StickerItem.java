package com.pst.SuperShop.uiModels;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosItem;

import java.util.List;

/**
 * Un adapter para habilitar la multi selección, provee al contador un estado
 */
public class StickerItem extends RecyclerView.Adapter<StickerItem.MyViewHolder> {

    private List<DatosItem> mDatosItemList;

    public StickerItem(List<DatosItem> datosItemList) {
        mDatosItemList = datosItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sticker_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DatosItem datosItem = mDatosItemList.get(position);
        holder.itemPrice.setText(datosItem.getPrecio());
        holder.view.setBackgroundColor(datosItem.isSelected() ? Color.CYAN : Color.WHITE);
        holder.itemPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datosItem.setSelected(!datosItem.isSelected());
                holder.view.setBackgroundColor(datosItem.isSelected() ? Color.YELLOW : Color.WHITE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatosItemList == null ? 0 : mDatosItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView itemPrice;
        private TextView itemName;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            itemPrice = (TextView) itemView.findViewById(R.id.item_price);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}
