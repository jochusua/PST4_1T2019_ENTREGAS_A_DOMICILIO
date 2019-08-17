package com.pst.SuperShop;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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


    FragmentTiendas( DatabaseReference refTiendas) {
        //this.database = database;
        this.refTiendas = refTiendas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inputFragment = inflater.inflate(R.layout.fragment_home, container, false);
        // populate tiendas frome refTiendas
        listarTiendas();

        // make each tienda open stockdetiendaactivity

        context = getActivity().getApplicationContext();
        return inputFragment;

    }

    private void listarTiendas() {
        refTiendas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listStores.clear(); // para persistencia ?
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){ // recorre nodo tiendas
                    DatosTienda su  = objSnapshot.getValue(DatosTienda.class);
                    listStores.add(su);
                }
                stickerTienda = new StickerTienda(listStores);
                rv_stores.setAdapter(stickerTienda);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}



//        final View store1 = inputFragment.findViewById(R.id.store1);
//        store1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(FragmentTiendas.this.getActivity(), StockDeTiendaActivity.class);
//                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        });
