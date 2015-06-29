package com.kzhou.spotifystreamer.controller;

import com.kzhou.spotifystreamer.model.Executor.ArtistSearchExecutor;
import com.kzhou.spotifystreamer.model.data.ListItem;
import com.kzhou.spotifystreamer.ui.view.SearchArtistView;

import java.util.ArrayList;

/**
 * User: Kevin
 * Date: 15/06/15
 * Time: 10:52 PM
 */
public class ArtistSearchControllerImpl implements ArtistSearchController {

    private SearchArtistView searchArtistView;
    private ArtistSearchExecutor executor;

    public ArtistSearchControllerImpl(SearchArtistView searchArtistView) {
        this.searchArtistView = searchArtistView;

        executor = new ArtistSearchExecutor(this);
    }

    //
    // ArtistSearchController Methods
    //
    @Override
    public void doSearchByArtistName(String name) {
        searchArtistView.closeKeyboard();
        searchArtistView.showSearchProgressBar();
        executor.doSearchByArtistName(name);
    }

    @Override
    public void listItemClicked(ListItem listItem) {
        searchArtistView.openTopTracksScreen(listItem.getId(), listItem.getName());
    }

    @Override
    public void setSearchResult(ArrayList<ListItem> artistList) {
        searchArtistView.hideSearchProgressBar();
        searchArtistView.refreshArtistList(artistList);
    }
}
