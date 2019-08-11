package com.pst.SuperShop;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pst.SuperShop.uiModels.StoreActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTiendas extends Fragment {


    public FragmentTiendas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inputFragment = inflater.inflate(R.layout.fragment_home, container, false);
        final View store1 = inputFragment.findViewById(R.id.store1);
        store1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentTiendas.this.getActivity(), StoreActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return inputFragment;

    }


}
