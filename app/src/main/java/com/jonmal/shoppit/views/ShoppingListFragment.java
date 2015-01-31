package com.jonmal.shoppit.views;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.jonmal.shoppit.Interfaces.FragmentCommunication;
import com.jonmal.shoppit.MainApplication;
import com.jonmal.shoppit.R;
import com.jonmal.shoppit.activities.SettingsActivity;
import com.jonmal.shoppit.adapters.ShoppingListAdapter;
import com.jonmal.shoppit.data.ShopContract;
import com.jonmal.shoppit.objects.ShoppingItem;

public class ShoppingListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    //==========================================================
    //                  Constants
    //==========================================================

    public static final String FRAGMENT_TAG = "com.jonmal.shoppit.FRAGMENT_TAG";

    private static final int SHOPPING_LIST_LOADER = 0;

    private static final String[] ITEM_COLUMNS = {
            ShopContract.ItemEntry.TABLE_NAME + "." + ShopContract.ItemEntry._ID,
            ShopContract.ItemEntry.COLUMN_TITLE,
            ShopContract.ItemEntry.COLUMN_DESCRIPTION,
    };
    //==========================================================
    //                  Fields
    //==========================================================

    private FragmentCommunication mActivity;

    // ui references
    private ListView mListView;
    private View mAddButton;
    private ShoppingListAdapter mShoppingListAdapter;


    //==========================================================
    //                  Constructor + Factory
    //==========================================================

    public ShoppingListFragment() {
    }

    public static ShoppingListFragment newInstance() {

        ShoppingListFragment fragment = new ShoppingListFragment();

        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_shopping_list, null);

        mListView = (ListView) rootView.findViewById(R.id.shoppingListFragment_listView);

        mShoppingListAdapter = new ShoppingListAdapter();

        mListView.setAdapter(mShoppingListAdapter);

        mAddButton = rootView.findViewById(R.id.shoppingListFragment_addButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isListLocked = MainApplication.getSharedPreferences().getBoolean(SettingsActivity.PREF_LOCK_SHOPPING_LIST, false);

                if (mActivity != null && !isListLocked) {

                    mActivity.startAddItemActivity();
                } else {

                    Toast.makeText(MainApplication.getAppContext(), getResources().getString(R.string.activitySettings_lockListToastMessage), Toast.LENGTH_SHORT).show();
                }
            }
        });

        getLoaderManager().initLoader(SHOPPING_LIST_LOADER, null, this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(SHOPPING_LIST_LOADER, null, this);
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mActivity = (FragmentCommunication) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentCommunication");
        }


    }


    public void addItem(ShoppingItem shoppingItem) {
        //add item

        ContentValues values = new ContentValues();
        values.put(ShopContract.ItemEntry.COLUMN_TITLE, shoppingItem.getTitle());
        values.put(ShopContract.ItemEntry.COLUMN_DESCRIPTION, shoppingItem.getDescritption());

        getActivity().getContentResolver().insert(ShopContract.ItemEntry.getItemsUri(), values);

    }

    //==========================================================
    //                  LoaderManager
    //==========================================================


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri itemsURI = ShopContract.ItemEntry.getItemsUri();

        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(
                getActivity(),
                itemsURI,
                ITEM_COLUMNS,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mShoppingListAdapter.clearItemList();
        if (data != null) {

            while (data.moveToNext()) {

                // Read description from cursor and update view
                String title = data.getString(data.getColumnIndex(ShopContract.ItemEntry.COLUMN_TITLE));
                String description = data.getString(data.getColumnIndex(ShopContract.ItemEntry.COLUMN_DESCRIPTION));

                ShoppingItem shoppingItem = new ShoppingItem(title, description);

                mShoppingListAdapter.addItem(shoppingItem);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}

