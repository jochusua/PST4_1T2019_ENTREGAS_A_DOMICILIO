package com.pst.SuperShop.uiModels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosItem;
import com.pst.SuperShop.models.DatosTienda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StockDeTiendaActivity extends AppCompatActivity {

    private List<DatosItem> listItems = new ArrayList<DatosItem>();
    private List<DatosItem> listItemsSelected = new ArrayList<DatosItem>();
    private RecyclerView mRecyclerView;
    private DatabaseReference reference;
    private String logoUrl;
    private Context context;
    private String nombretienda;
    private String email_cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_de_tienda_activity);

        ImageView logoView = (ImageView) findViewById(R.id.logoView);
        TextView nombretiendaTV = (TextView) findViewById(R.id.nombretiendatv);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(StockDeTiendaActivity.this);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(null);

        final String logoUrl = getIntent().getStringExtra("URL_LOGO");
        nombretienda = getIntent().getStringExtra("NOMBRE_TIENDA");
        final String uidTienda = getIntent().getStringExtra("UID_TIENDA");
        email_cliente = getIntent().getStringExtra("email_cliente");


        nombretiendaTV.setText(nombretienda);

        Glide.with(this)
                .load(logoUrl)
                .fitCenter()
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.broken_image_blue)
                .fallback(R.drawable.broken_image_blue)
                .into(logoView)
        ;

        inicializarFirebase();
        listarProductos();
    }

    private void listarProductos() {
        reference.child("producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listItems.clear(); // para persistencia ?
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){ // recorre nodo tiendas
                    DatosItem di = objSnapshot.getValue(DatosItem.class);
                    //Toast.makeText(context, "Tienda>"+su.getNombre(), Toast.LENGTH_LONG).show();
                    listItems.add(di);
                }
                mRecyclerView.setAdapter(new StickerItem(listItems));
                //Toast.makeText(context, "stickerTienda", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        //Toast.makeText(this, "Se inicializó firebase ", Toast.LENGTH_LONG).show();
    }

    public void ingresarShoppingCart(View view) {
        Intent i = new Intent(this, shoppingCart.class );
        listItemsSelected.clear();
        i.putExtra("tienda", nombretienda);
        i.putExtra("id_cliente", email_cliente);
        for(DatosItem d:listItems){
            if(d.isSelected()){
                listItemsSelected.add(d);
            }
        }
        if(listItemsSelected.isEmpty()){
            Toast.makeText( this, "No posee productos seleccionados", Toast.LENGTH_SHORT ).show();
        }else{
            i.putExtra("itemsSelecccionados", (Serializable) listItemsSelected);
            i.putExtra("URL_LOGO1",(Serializable) logoUrl);
            startActivity(i);
        }
    }
}
