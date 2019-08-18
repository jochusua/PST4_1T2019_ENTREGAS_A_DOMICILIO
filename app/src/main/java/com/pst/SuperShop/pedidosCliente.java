package com.pst.SuperShop;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class pedidosCliente extends Fragment {
    private DatabaseReference refPedidos;
    private Context context;

    public pedidosCliente() {
        // Required empty public constructor
    }

    public pedidosCliente( DatabaseReference refTiendas) {
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


        return inputFragment;

    }

    
}
