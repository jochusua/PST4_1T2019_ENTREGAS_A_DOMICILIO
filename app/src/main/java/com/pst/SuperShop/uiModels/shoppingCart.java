package com.pst.SuperShop.uiModels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosItem;

import java.util.ArrayList;
import java.util.List;

public class shoppingCart extends AppCompatActivity {
    private List<DatosItem> mDatosItemList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new StickerItem(getListData());
        LinearLayoutManager manager = new LinearLayoutManager(shoppingCart.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<DatosItem> getListData() {
        // TODO: Listar items desde la base de datos
        mDatosItemList = (ArrayList<DatosItem>)getIntent().getSerializableExtra("itemsSelecccionados");
        return mDatosItemList;
    }

    public void noComprar(View view) {
        Toast.makeText( this, "Compra no realizada", Toast.LENGTH_SHORT ).show();
        finish();
    }
}
