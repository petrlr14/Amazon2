package com.pdm2018.amazon2;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;





public class Login extends Fragment {
    private TextView register_access;
    private Button sign_in_button;
    private EditText username, password;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login, container, false);
        findviews(v);

        clicklisteners();
        return v;
    }


    //FIND VIEWS ALL BY ID
    public void findviews(View v) {


    }

    //CLICK LISTENERS FOR BUTTONS
    public void clicklisteners() {
    }





}

