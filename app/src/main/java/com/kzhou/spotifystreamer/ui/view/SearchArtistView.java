package com.kzhou.spotifystreamer.ui.view;

import android.content.Context;

import com.kzhou.spotifystreamer.model.view.ListItem;

import java.util.ArrayList;

/**
 * User: Kevin
 * Date: 15/06/15
 * Time: 9:48 PM
 */
public interface SearchArtistView {
    Context getContext();

    void closeKeyboard();

    void showSearchProgressBar();

    void hideSearchProgressBar();

    void refreshArtistList(ArrayList<ListItem> artistList);

    void openTopTracksScreen(String artistId, String artistName);
}
