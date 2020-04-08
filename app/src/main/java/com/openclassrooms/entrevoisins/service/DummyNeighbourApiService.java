package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * Modification Favoris
     * @param neighbour
     */
    @Override
    public void favoritesNeighbour (Neighbour neighbour){
        if (neighbour.getFavorites()==false){
                neighbour.setFavorites(true);
                Log.i("DATA", "favoritesNeighbour: modification favoris true "+neighbour.getName());}
        else {
            neighbour.setFavorites(false);
            Log.i("DATA", "favoritesNeighbour: modification favoris false " +neighbour.getName());
        }

    }

}
