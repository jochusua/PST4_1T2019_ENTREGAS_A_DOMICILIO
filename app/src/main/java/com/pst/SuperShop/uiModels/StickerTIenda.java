package com.pst.SuperShop.uiModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosTienda;

import java.io.InputStream;
import java.util.List;

/**
 * Un adapter para habilitar la multi selección, provee al contador un estado
 */
public class StickerTIenda extends RecyclerView.Adapter<StickerTIenda.MyViewHolder> {

    private List<DatosTienda> mItemFromStoreList;

    public StickerTIenda(List<DatosTienda> itemFromStoreList) {
        mItemFromStoreList = itemFromStoreList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sticker_tienda, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DatosTienda datosTienda = mItemFromStoreList.get(position);
        //holder.storePhotoview.setImageURI("");

        //holder.view.setBackgroundColor(datosTienda.isSelected() ? Color.CYAN : Color.WHITE);
//        holder.storePhotoview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                datosTienda.setSelected(!datosTienda.isSelected());
//                holder.view.setBackgroundColor(datosTienda.isSelected() ? Color.YELLOW : Color.WHITE);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mItemFromStoreList == null ? 0 : mItemFromStoreList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView storePhotoview;
        private TextView nombre;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            // descarga en un "threading" la imagen, permitiendo que la ui no se cuelgue
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

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
