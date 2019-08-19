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
import com.pst.SuperShop.models.Pedido;
import com.pst.SuperShop.uiModels.StickerTienda;
import com.pst.SuperShop.uiModels.StikerPedido;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class pedidosCliente extends Fragment {

    private List<Pedido> listPedidos = new ArrayList<Pedido>();
    ArrayAdapter<Pedido> arrayAdapterStoreUnit;


    private DatabaseReference refPedidos;
    private Context context;
    private RecyclerView rv_stores;
    private StikerPedido stickerPedido;

    public pedidosCliente() {
        // Required empty public constructor
    }

    public pedidosCliente( DatabaseReference refPedidos) {
        //this.database = database;
        this.refPedidos = refPedidos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pedidos_cliente, container, false);
        final View inputFragment = inflater.inflate(R.layout.fragment_pedidos_cliente, container, false);
        context = getContext();

        rv_stores = (RecyclerView)inputFragment.findViewById(R.id.rv_stores2);
        rv_stores.setLayoutManager(new LinearLayoutManager(context));
        rv_stores.setAdapter(null);

        listarPedidos();


        return inputFragment;

    }

    private void listarPedidos() {
        refPedidos.child("pedidos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPedidos.clear(); // para persistencia ?
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){ // recorre nodo pedidos
                    Pedido pedido  = objSnapshot.getValue(Pedido.class);
                    //pedido.setUid(objSnapshot.getKey()); // identificador o uid
                    //Toast.makeText(context, "pedido>"+su.getNombre(), Toast.LENGTH_LONG).show();
                    listPedidos.add(pedido);
                }
                stickerPedido = new StikerPedido(listPedidos);
                rv_stores.setAdapter(stickerPedido);
                //Toast.makeText(context, "stickerTienda", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
