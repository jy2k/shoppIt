package com.jonmal.shoppit.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jonmal.shoppit.Interfaces.FragmentCommunication;
import com.jonmal.shoppit.R;
import com.jonmal.shoppit.objects.ShoppingItem;
import com.jonmal.shoppit.views.AddItemFragment;
import com.jonmal.shoppit.views.ShoppingListFragment;


public class MainActivity extends ActionBarActivity implements FragmentCommunication{


    //==========================================================
    //                  Activity Lifecycle
    //==========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, ShoppingListFragment.newInstance(), ShoppingListFragment.FRAGMENT_TAG)
                    .commit();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case AddItemActivity.ACTIVITY_ADD_ITEM:

                if (resultCode == Activity.RESULT_OK && data != null) {

                    String shoppingItemTitle = data.getStringExtra(AddItemFragment.SHOPPING_ITEM_TITLE);
                    String shoppingItemDescription = data.getStringExtra(AddItemFragment.SHOPPING_ITEM_DESCRIPTION);

                    ShoppingItem shoppingItem = new ShoppingItem(shoppingItemTitle, shoppingItemDescription);

                    addShoppingItem(shoppingItem);

                }

                break;
        }
    }

    //==========================================================
    //                  FragmentCommunication Implementation
    //==========================================================

    @Override
    public void addShoppingItem(ShoppingItem shoppingItem) {

        ShoppingListFragment shoppingListFragment = (ShoppingListFragment) getFragmentManager().findFragmentByTag(ShoppingListFragment.FRAGMENT_TAG);
        shoppingListFragment.addItem(shoppingItem);
    }

    @Override
    public void startAddItemActivity() {

        //launch second activity
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, AddItemActivity.ACTIVITY_ADD_ITEM);
    }

    //==========================================================
    //                  Menu implementation
    //==========================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:

                Intent launchSettingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(launchSettingsActivity);

                break;
            case R.id.action_share:

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Try this awesome app!");
                startActivity(Intent.createChooser(shareIntent, "Share the love"));

                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
