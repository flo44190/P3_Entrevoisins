package com.openclassrooms.entrevoisins.controler;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        mAvatar = (ImageView) findViewById(R.id.activity_info_neighbour_avatar_img);
        mName = (TextView) findViewById(R.id.activity_info_neighbour_name2_tkt);
        mAdress = (TextView) findViewById(R.id.activity_info_neighbour_adress_txt);
        mPhone = (TextView) findViewById(R.id.activity_info_neighbour_phone_txt);
        mWeb = (TextView) findViewById(R.id.activity_info_neightbour_web_txt);
        mAbout = (TextView) findViewById(R.id.activity_info_neighbour_about_txt);

        mApiService = DI.getNeighbourApiService();
        initList();

        if (intent != null){
            int position = intent.getIntExtra(NeighbourFragment.EXTRA_BUNDLE_POSITION,0);
            DisplayNeighbour(mNeighbours.get(position));
        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void DisplayNeighbour (Neighbour neighbour){
        Glide.with(this).load(neighbour.getAvatarUrl()).into(mAvatar);
        setTitle(neighbour.getName());
        mName.setText(neighbour.getName());
        mAdress.setText(neighbour.getAddress());
        mPhone.setText(neighbour.getPhoneNumber());
        mAbout.setText(neighbour.getAboutMe());
        mWeb.setText(neighbour.getWeb());
    }

    private void initList() {
        mNeighbours = mApiService.getNeighbours();
    }

}
