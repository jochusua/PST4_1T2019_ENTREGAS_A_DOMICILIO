package com.pst.SuperShop.uiModels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pst.SuperShop.MainActivity;
import com.pst.SuperShop.MapsActivity;
import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosItem;
import com.pst.SuperShop.models.Pedido;
import com.pst.SuperShop.models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class shoppingCart extends AppCompatActivity{
    private List<DatosItem> mDatosItemList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LocationManager locationManager;

    String usuario;


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

        usuario =getIntent().getStringExtra("dato2");

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

    public void comprar(View view) {
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        Pedido p= new Pedido();
        p.setId_cliente("123");
        p.setPrecio(totalPrecio());
        p.setLatitud(-2.15);
        p.setLongitud(-90.1);
        referencia.child("pedidos").child(UUID.randomUUID().toString()).setValue(p);
        Toast.makeText( this, "Compra realizada exitosamente", Toast.LENGTH_SHORT ).show();
        finish();
    }

    public double totalPrecio(){
        double total=0;
        for(DatosItem d:mDatosItemList){
            total+=d.getPrecio();
        }
        return total;
    }


}
