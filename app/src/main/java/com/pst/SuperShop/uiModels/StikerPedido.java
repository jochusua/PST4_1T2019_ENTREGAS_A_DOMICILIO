package com.pst.SuperShop.uiModels;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pst.SuperShop.MapsActivity;
import com.pst.SuperShop.R;
import com.pst.SuperShop.Registro;
import com.pst.SuperShop.models.DatosTienda;
import com.pst.SuperShop.models.Pedido;

import java.util.List;

public class StikerPedido extends RecyclerView.Adapter<StikerPedido.MyViewHolder>{

    private List<Pedido> listPedidos;
    private Context context;

    public StikerPedido(List<Pedido> listPedidos) {
        this.listPedidos = listPedidos;
    }

    @NonNull
    @Override
    public StikerPedido.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.sticker_pedido, parent, false);
        return new StikerPedido.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StikerPedido.MyViewHolder holder, int position) {
        final Pedido pedidos = listPedidos.get(position);


        String pre= Double.toString(pedidos.getPrecio());
        holder.identificacion.setText("Cliente: "+pedidos.getId_cliente());
        holder.precio.setText("Precio: "+pre);
        holder.estado.setText(pedidos.getEstado());

        if (pedidos.getEstado().equalsIgnoreCase("pendiente")){
            holder.storePhotoview.setImageResource(R.drawable.pendiente2);
        }


        // cuando realice click en el logo de tienda, se abrirá StockDeTienda
        holder.storePhotoview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pedidos.getEstado().equalsIgnoreCase("finalizado")){
                    Toast.makeText(context,"Ya se realizo este encargo",Toast.LENGTH_SHORT).show();

                }else if (pedidos.getEstado().equalsIgnoreCase("pendiente")){
                    Toast.makeText(context,"El encargo es aprobado",Toast.LENGTH_SHORT).show();
                    // todo: iniciar mapsactivity
                    Intent i = new Intent(context, MapsActivity.class);
                    i.putExtra("latitude",pedidos.getLatitud());
                    i.putExtra("longitude",pedidos.getLongitud());
                    context.startActivity(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPedidos == null ? 0 : listPedidos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView storePhotoview;
        private TextView identificacion;
        private TextView precio;
        private TextView estado;


        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView; // view o "layout" de stickerTienda
            // cargar atributos a la clase
            storePhotoview = (ImageView) itemView.findViewById(R.id.store_photoview);
            identificacion = (TextView) itemView.findViewById(R.id.id_pedido);
            precio = (TextView) itemView.findViewById(R.id.text_precio);
            estado = (TextView) itemView.findViewById(R.id.textestado);
        }
    }
}
