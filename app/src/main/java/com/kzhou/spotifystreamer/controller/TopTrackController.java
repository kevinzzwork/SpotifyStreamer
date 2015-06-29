package com.kzhou.spotifystreamer.controller;

import com.kzhou.spotifystreamer.model.data.ListItem;

import java.util.ArrayList;

/**
 * User: Kevin
 * Date: 22/06/15
 * Time: 10:07 PM
 */
public interface TopTrackController {
    void getTop10Tracks(String artistId);

    void setTopTrackResult(ArrayList<ListItem> results);
}
