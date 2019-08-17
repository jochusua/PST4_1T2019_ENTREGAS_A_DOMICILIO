package com.pst.SuperShop.uiModels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosItem;
import com.pst.SuperShop.models.DatosTienda;

import java.util.ArrayList;
import java.util.List;

public class StockDeTiendaActivity extends AppCompatActivity {

    private List<DatosItem> mDatosItemList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<DatosTienda> listStores = new ArrayList<DatosTienda>();
    ArrayAdapter<DatosTienda> arrayAdapterStoreUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_de_tienda_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new StickerItem(getListData());
        LinearLayoutManager manager = new LinearLayoutManager(StockDeTiendaActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<DatosItem> getListData() {
        mDatosItemList = new ArrayList<>();

        //for (int i = 1; i <= 25; i++) {
          //  mDatosItemList.add(new DatosItem("Precio $ " + i,"Nombre: "+i));
        //}
        return mDatosItemList;
    }

    /*
    public void listaitem(){
        refTiendas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listStores.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    DatosTienda su  = objSnapshot.getValue(DatosTienda.class);
                    listStores.add(su);
                    //stickerTienda = new StickerTIenda()
                    //arrayAdapterStoreUnit = new ArrayAdapter<DatosTienda>(context,android.R.layout.simple_list_item_1,listStores);
                    //rv_stores.setAdapter(arrayAdapterStoreUnit);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    */
}
