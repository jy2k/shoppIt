package com.jonmal.shoppit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jonmal.shoppit.MainApplication;
import com.jonmal.shoppit.R;
import com.jonmal.shoppit.objects.ShoppingItem;

import java.util.ArrayList;

public class ShoppingListAdapter extends BaseAdapter {

    //==========================================================
    //                  Fields
    //==========================================================

    private ArrayList<ShoppingItem> mShoppingItemsList;

    //==========================================================
    //                  Constructor
    //==========================================================

    public ShoppingListAdapter() {

        super();

        mShoppingItemsList = new ArrayList<ShoppingItem>();
    }

    //==========================================================
    //                  BaseAdatper Implmentation
    //==========================================================

    @Override
    public int getCount() {
        return mShoppingItemsList.size();
    }

    @Override
    public ShoppingItem getItem(int position) {
        return mShoppingItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(MainApplication.getAppContext());

        View rootView = layoutInflater.inflate(R.layout.shopping_list_item, null);

        // ui references
        TextView title = (TextView) rootView.findViewById(R.id.shoppingListItem_title);
        TextView description = (TextView) rootView.findViewById(R.id.shoppingListItem_description);

        ShoppingItem shoppingItem = mShoppingItemsList.get(position);

        title.setText(shoppingItem.getTitle());
        description.setText(shoppingItem.getDescritption());

        return rootView;
    }

    //==========================================================
    //                  Public Methods
    //==========================================================

    public void addItem(ShoppingItem shoppingItem) {

        mShoppingItemsList.add(shoppingItem);
    }

    public void clearItemList() {

        mShoppingItemsList.clear();
        notifyDataSetChanged();
    }
}
