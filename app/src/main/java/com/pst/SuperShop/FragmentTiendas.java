package com.pst.SuperShop;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.pst.SuperShop.models.DatosTienda;
import com.pst.SuperShop.uiModels.StickerTienda;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTiendas extends Fragment {


    private List<DatosTienda> listStores = new ArrayList<DatosTienda>();
    ArrayAdapter<DatosTienda> arrayAdapterStoreUnit;

    //private FirebaseDatabase database;
    private DatabaseReference refTiendas;
    private Context context;
    private RecyclerView rv_stores;
    private StickerTienda stickerTienda;

    public FragmentTiendas() {
        // Required empty public constructor
    }

    public FragmentTiendas( DatabaseReference refTiendas) {
        //this.database = database;
        this.refTiendas = refTiendas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inputFragment = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        rv_stores = (RecyclerView)inputFragment.findViewById(R.id.rv_stores);
        rv_stores.setLayoutManager(new LinearLayoutManager(context));
        rv_stores.setAdapter(null);

        // populate tiendas frome refTiendas
        listarTiendas();
        return inputFragment;
    }

    private void listarTiendas() {
        refTiendas.child("tiendas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listStores.clear(); // para persistencia ?
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){ // recorre nodo tiendas
                    DatosTienda su  = objSnapshot.getValue(DatosTienda.class);
                    su.setUid(objSnapshot.getKey()); // identificador o uid
                    //Toast.makeText(context, "Tienda>"+su.getNombre(), Toast.LENGTH_LONG).show();
                    listStores.add(su);
                }
                stickerTienda = new StickerTienda(listStores);
                rv_stores.setAdapter(stickerTienda);
                //Toast.makeText(context, "stickerTienda", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
