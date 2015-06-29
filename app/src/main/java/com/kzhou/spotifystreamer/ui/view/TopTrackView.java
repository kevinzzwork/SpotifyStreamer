package com.kzhou.spotifystreamer.ui.view;

import android.content.Context;

import com.kzhou.spotifystreamer.model.data.ListItem;

import java.util.ArrayList;

/**
 * User: Kevin
 * Date: 20/06/15
 * Time: 12:30 AM
 */
public interface TopTrackView {
    Context getContext();

    void closeKeyboard();

    void showSearchProgressBar();

    void hideSearchProgressBar();

    void refreshTrackList(ArrayList<ListItem> trackList);

    void showListEmptyToast();

    void openTrackStreamScreen();
}
