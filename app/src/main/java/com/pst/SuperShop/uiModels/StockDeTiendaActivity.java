package com.pst.SuperShop.uiModels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosItem;

import java.util.ArrayList;
import java.util.List;

public class StockDeTiendaActivity extends AppCompatActivity {

    private List<DatosItem> mDatosItemList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
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
        for (int i = 1; i <= 25; i++) {
            mDatosItemList.add(new DatosItem("Precio $ " + i,"Nombre: "+i));
        }
        return mDatosItemList;
    }
}
