package com.example.aravindraj.vaterkanes.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aravindraj.vaterkanes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCustomerFragment extends Fragment {


    public AddCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_customer, container, false);
    }

}
