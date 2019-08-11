package com.pst.SuperShop.uiModels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.pst.SuperShop.MainActivity;
import com.pst.SuperShop.R;
import com.pst.SuperShop.models.ItemFromStore;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private List<ItemFromStore> mItemFromStoreList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(getListData());
        LinearLayoutManager manager = new LinearLayoutManager(StoreActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }
    private List<ItemFromStore> getListData() {
        mItemFromStoreList = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            mItemFromStoreList.add(new ItemFromStore("Precio $ " + i,"Desc: "+i));
        }
        return mItemFromStoreList;
    }
}
