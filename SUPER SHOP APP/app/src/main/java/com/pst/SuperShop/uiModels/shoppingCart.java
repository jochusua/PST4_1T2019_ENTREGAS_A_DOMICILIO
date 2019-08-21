package com.pst.SuperShop.uiModels;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pst.SuperShop.MainActivity;
import com.pst.SuperShop.MapsActivity;
import com.pst.SuperShop.R;
import com.pst.SuperShop.models.DatosItem;
import com.pst.SuperShop.models.Pedido;
import com.pst.SuperShop.models.Usuario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class shoppingCart extends AppCompatActivity {
    private List<DatosItem> mDatosItemList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LocationManager locManager;
    private Location loc;
    private DatabaseReference reference;
    double lat;
    double longitud;
    String estado="Pendiente";
    String nombreTienda;
    String usuario;
    String logoUrl;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ImageView logoView = (ImageView) findViewById(R.id.logoView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new StickerItem(getListData());
        LinearLayoutManager manager = new LinearLayoutManager(shoppingCart.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        logoUrl = getIntent().getStringExtra("URL_LOGO1");
        nombreTienda = getIntent().getStringExtra("NOMBRETIENDA");

        Glide.with(this)
                .load(logoUrl)
                .fitCenter()
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.broken_image_blue)
                .fallback(R.drawable.broken_image_blue)
                .into(logoView)
        ;
        inicializarFirebase();

        usuario = getIntent().getStringExtra("dato2");
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lat=loc.getLatitude();
        longitud=loc.getLongitude();

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void comprar(View view) {
        Intent i = new Intent(this, compraRealizada.class );
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        Pedido p= new Pedido();
        p.setEstado(estado);
        p.setNombreTienda(nombreTienda);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        p.setFecha(LocalDateTime.now().format(formatter));
        p.setId_cliente("dlara@espol.edu.ec");
        //p.setId_cliente(usuario);
        p.setPrecio(totalPrecio());
        p.setLatitud(lat);
        p.setLongitud(longitud);
        referencia.child("pedidos").child(UUID.randomUUID().toString()).setValue(p);
        Toast.makeText( this, "Compra realizada exitosamente", Toast.LENGTH_SHORT ).show();
        finish();
        i.putExtra("itemsSelecccionados", (Serializable) mDatosItemList);
        i.putExtra("total", String.valueOf(totalPrecio()));
        startActivity(i);
    }

    public double totalPrecio(){
        double total=0;
        for(DatosItem d:mDatosItemList){
            total+=d.getPrecio();
        }
        return total;
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        //Toast.makeText(this, "Se inicializó firebase ", Toast.LENGTH_LONG).show();
    }

}
