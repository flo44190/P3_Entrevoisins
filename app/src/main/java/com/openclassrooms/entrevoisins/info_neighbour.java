package com.openclassrooms.entrevoisins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class info_neighbour extends AppCompatActivity {

    private ImageView mAvatar;
    private TextView mName;
    private TextView mAdress;
    private TextView mPhone;
    private TextView mWeb;
    private TextView mAbout;

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAvatar = (ImageView) findViewById(R.id.activity_info_neighbour_avatar_img);
        mName = (TextView) findViewById(R.id.activity_info_neighbour_name2_tkt);
        mAdress = (TextView) findViewById(R.id.activity_info_neighbour_adress_txt);
        mPhone = (TextView) findViewById(R.id.activity_info_neighbour_phone_txt);
        mWeb = (TextView) findViewById(R.id.activity_info_neightbour_web_txt);
        mAbout = (TextView) findViewById(R.id.activity_info_neighbour_about_txt);
        mApiService = DI.getNeighbourApiService();
        initList();
        DisplayNeighbour(mNeighbours.get(6));


    }

    private void DisplayNeighbour (Neighbour neighbour){
        mAvatar.setImageBitmap(neighbour.getAvatarUrl());
        mName.setText(neighbour.getName());
        mAdress.setText(neighbour.getAddress());
        mPhone.setText(neighbour.getPhoneNumber());
        mAbout.setText(neighbour.getAboutMe());
    }

    private void initList() {
        mNeighbours = mApiService.getNeighbours();
    }

    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, info_neighbour.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}
