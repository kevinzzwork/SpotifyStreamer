package com.kzhou.spotifystreamer.controller;

import com.kzhou.spotifystreamer.model.view.ListItem;

import java.util.ArrayList;

/**
 * User: Kevin
 * Date: 15/06/15
 * Time: 10:22 PM
 */
public interface ArtistSearchController {
    void doSearchByArtistName(String name);

    void listItemClicked(ListItem listItem);

    void setSearchResult(ArrayList<ListItem> artistList);
}
