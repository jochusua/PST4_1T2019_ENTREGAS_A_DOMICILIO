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

import androidx.recyclerview.widget.RecyclerView;

import com.pst.SuperShop.MapsActivity;
import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosTienda;

import java.io.InputStream;
import java.util.List;

/**
 * Un adapter para habilitar la multi selección, provee al contador un estado
 */
public class StickerTienda extends RecyclerView.Adapter<StickerTienda.MyViewHolder> {

    private List<DatosTienda> listTiendas;
    private Context context;

    public StickerTienda(List<DatosTienda> listTiendas) {
        this.listTiendas = listTiendas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.sticker_tienda, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DatosTienda datosTienda = listTiendas.get(position);

        // cuando realice click en el logo de tienda, se abrirá StockDeTienda
        holder.storePhotoview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                datosTienda.setSelected(!datosItem.isSelected());
//                holder.view.setBackgroundColor(datosItem.isSelected() ? amber : Color.WHITE);
                Intent intent=new Intent(context, StockDeTiendaActivity.class);
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
            // descarga en un "threading" la imagen, permitiendo que la ui no se cuelgue
            // TODO: Obtener fotourl de la base de datos e atar StockDeTiendaActivity a un click
            new DownloadImageTask((ImageView) itemView.findViewById(R.id.store_photoview))
                    .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
            nombre = itemView.findViewById(R.id.nombreTV);

        }
    }


    /**
     *  Provee servicio para descargar imagen de url sin congelar la UI
     */
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        // antes esto era public
        DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap result = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                result = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.toString());
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
