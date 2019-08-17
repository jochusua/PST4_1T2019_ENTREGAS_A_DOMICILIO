package com.pst.SuperShop.uiModels;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosItem;

import java.util.List;

/**
 * Un adapter para habilitar la multi selección, provee al contador un estado
 */
public class StickerItem extends RecyclerView.Adapter<StickerItem.MyViewHolder> {

    private List<DatosItem> listDatosItem;
    private int amber;

    // here, this func was public
    StickerItem(List<DatosItem> datosItemList) {
        listDatosItem = datosItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        amber = ResourcesCompat.getColor(context.getResources(),R.color.amber_300, null);
        View view = LayoutInflater.from(context).inflate(R.layout.sticker_item, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * Genera la vista cuando el sticker item está mostrándose en el recyclerview
     * El recyclerView es óptimo para largas listas de items
     * @param holder El view del elemento
     * @param position la posicion en el RecyclerView
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DatosItem datosItem = listDatosItem.get(position);
        holder.itemPrice.setText(datosItem.getPrecio());
        holder.view.setBackgroundColor(datosItem.isSelected() ? amber : Color.WHITE);

        // cuando realiza click en itemPrice, se ejecutará lo siguiente
        holder.itemPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datosItem.setSelected(!datosItem.isSelected());
                holder.view.setBackgroundColor(datosItem.isSelected() ? amber : Color.WHITE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDatosItem == null ? 0 : listDatosItem.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView itemPhoto;
        private View view;
        private TextView itemPrice;
        private TextView itemName;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            itemPrice = (TextView) itemView.findViewById(R.id.item_price);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemPhoto = (ImageView) itemView.findViewById(R.id.item_photoview);
        }
    }
}
