package com.pst.SuperShop.uiModels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pst.SuperShop.MapsActivity;
import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosTienda;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Un adapter para habilitar la multi selección, provee al contador un estado
 */
public class StickerTienda extends RecyclerView.Adapter<StickerTienda.MyViewHolder> {

    private final String email_cliente;
    private List<DatosTienda> listTiendas;
    private Context context;

    public StickerTienda(List<DatosTienda> listTiendas, String email_cliente) {
        this.listTiendas = listTiendas;
        this.email_cliente = email_cliente;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.sticker_tienda, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final DatosTienda datosTienda = listTiendas.get(position);
        // cargar imagen desde la url, no cuelga la UI porque funciona como un threading
        Glide.with(context)
                .load(datosTienda.getLogourl())
                .fitCenter()
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.broken_image_blue)
                .fallback(R.drawable.broken_image_blue)
                .into(holder.storePhotoview)
        ;

        holder.nombre.setText(datosTienda.getNombre());
        // cuando realice click en el logo de tienda, se abrirá StockDeTienda
        holder.storePhotoview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: abrir "stock personal" a traves de atributos en datosTienda
                Intent intent=new Intent(context, StockDeTiendaActivity.class);
                intent.putExtra("NOMBRE_TIENDA",datosTienda.getNombre());
                intent.putExtra("URL_LOGO", datosTienda.getLogourl());
                intent.putExtra("UID_TIENDA", datosTienda.getUid());
                intent.putExtra("email_cliente", email_cliente);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTiendas == null ? 0 : listTiendas.size();
    }

    // antes esto era public
    class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView storePhotoview;
        private TextView nombre;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView; // view o "layout" de stickerTienda
            // cargar atributos a la clase
            storePhotoview = (ImageView) itemView.findViewById(R.id.store_photoview);
            nombre = (TextView) itemView.findViewById(R.id.nombreTV);

        }
    }
}
