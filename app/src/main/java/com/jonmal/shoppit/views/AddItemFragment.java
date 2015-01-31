package com.jonmal.shoppit.views;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jonmal.shoppit.R;
import com.jonmal.shoppit.activities.AddItemActivity;

public class AddItemFragment extends Fragment {

    //==========================================================
    //                  Constants
    //==========================================================

    public static final String SHOPPING_ITEM_DESCRIPTION = "com.jonmal.shopit.SHOPPING_ITEM_DESCRIPTION";
    public static final String SHOPPING_ITEM_TITLE = "com.jonmal.shopit.SHOPPING_ITEM_TITLE";

    //==========================================================
    //                  Fields
    //==========================================================

    //ui references
    private EditText mTitleET;
    private EditText mDescriptionET;
    private View mSubmitButton;

    private AddItemActivity mActivity;

    //==========================================================
    //                  Constructor + Factory
    //==========================================================

    public AddItemFragment() {
    }

    public static AddItemFragment newInstance() {

        AddItemFragment fragment = new AddItemFragment();

        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_item, null);

        mTitleET = (EditText) rootView.findViewById(R.id.addItemFragment_titleET);

        mDescriptionET = (EditText) rootView.findViewById(R.id.addItemFragment_decriptionET);

        mSubmitButton = rootView.findViewById(R.id.addItemFragment_submitButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String title = mTitleET.getEditableText().toString();
                String description = mDescriptionET.getEditableText().toString();

                if(title.isEmpty()){

                    mTitleET.setError(getResources().getString(R.string.addItemFragment_titleMissing));

                } else if (description.isEmpty()) {

                    mDescriptionET.setError(getResources().getString(R.string.addItemFragment_descriptionMissing));

                } else {
                    // both editTexts have been filled properly

                    Intent data = new Intent();
                    data.putExtra(SHOPPING_ITEM_TITLE, title);
                    data.putExtra(SHOPPING_ITEM_DESCRIPTION, description);

                    mActivity.onItemSubmitted(data);

                }
                
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mActivity = (AddItemActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
