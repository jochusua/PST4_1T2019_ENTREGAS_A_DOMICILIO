package com.pst.SuperShop.uiModels;

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

import com.pst.SuperShop.R;
import com.pst.SuperShop.models.ItemFromStore;
import com.pst.SuperShop.models.StoreUnit;

import java.io.InputStream;
import java.util.List;

/**
 * Un adapter para habilitar la multi selección, provee al contador un estado
 */
public class StoreRowAdapter extends RecyclerView.Adapter<StoreRowAdapter.MyViewHolder> {

    private List<StoreUnit> mItemFromStoreList;

    public StoreRowAdapter(List<StoreUnit> itemFromStoreList) {
        mItemFromStoreList = itemFromStoreList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final StoreUnit storeUnit = mItemFromStoreList.get(position);
        //holder.storePhotoview.setImageURI("");

        //holder.view.setBackgroundColor(storeUnit.isSelected() ? Color.CYAN : Color.WHITE);
//        holder.storePhotoview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                storeUnit.setSelected(!storeUnit.isSelected());
//                holder.view.setBackgroundColor(storeUnit.isSelected() ? Color.YELLOW : Color.WHITE);
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
        private TextView itemDescTextview;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            new DownloadImageTask((ImageView) itemView.findViewById(R.id.store_photoview))
                    .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
            storePhotoview = (ImageView) itemView.findViewById(R.id.store_photoview);
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
