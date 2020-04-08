package com.openclassrooms.entrevoisins.controler;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

import java.util.List;
import java.util.Objects;

import butterknife.OnClick;


public class info_neighbour extends AppCompatActivity {

    private ImageView mAvatar;
    private TextView mName;
    private TextView mAdress;
    private TextView mPhone;
    private TextView mWeb;
    private TextView mAbout;
    private int position;

    private FloatingActionButton mFavoritesBtn;

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        mAvatar = (ImageView) findViewById(R.id.activity_info_neighbour_avatar_img);
        mName = (TextView) findViewById(R.id.activity_info_neighbour_name2_tkt);
        mAdress = (TextView) findViewById(R.id.activity_info_neighbour_adress_txt);
        mPhone = (TextView) findViewById(R.id.activity_info_neighbour_phone_txt);
        mWeb = (TextView) findViewById(R.id.activity_info_neightbour_web_txt);
        mAbout = (TextView) findViewById(R.id.activity_info_neighbour_about_txt);

        mFavoritesBtn = (FloatingActionButton) findViewById(R.id.activity_info_neighbour_favorites_btn);

        mApiService = DI.getNeighbourApiService();
        initList();

        /**
         * Recuperation de la position du Neighbour et Affichage
         */

        if (intent != null){
            position = intent.getIntExtra(NeighbourFragment.EXTRA_BUNDLE_POSITION,0);
            DisplayNeighbour(mNeighbours.get(position));
        }

        mFavoritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifFavories(mNeighbours.get(position));
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    /**
     * Afichage dynamique des infos utilisateurs
     */

    private void DisplayNeighbour (Neighbour neighbour){
        Glide.with(this).load(neighbour.getAvatarUrl()).into(mAvatar);
        setTitle(neighbour.getName());
        mName.setText(neighbour.getName());
        mAdress.setText(neighbour.getAddress());
        mPhone.setText(neighbour.getPhoneNumber());
        mAbout.setText(neighbour.getAboutMe());
        mWeb.setText(neighbour.getWeb());
        if (neighbour.getFavorites() == true){
            mFavoritesBtn.setImageResource(R.drawable.ic_star_yellow_24dp);
        }else {
            mFavoritesBtn.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mNeighbours = mApiService.getNeighbours();
    }

    public void modifFavories (Neighbour neighbour){
        mApiService.favoritesNeighbour(neighbour);
        if (neighbour.getFavorites() == true){
            mFavoritesBtn.setImageResource(R.drawable.ic_star_yellow_24dp);
        }else {
            mFavoritesBtn.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }
}
