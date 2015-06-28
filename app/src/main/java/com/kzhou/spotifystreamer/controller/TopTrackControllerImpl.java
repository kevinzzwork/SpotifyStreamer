package com.kzhou.spotifystreamer.controller;

import com.kzhou.spotifystreamer.model.Executor.TopTrackExecutor;
import com.kzhou.spotifystreamer.model.view.ListItem;
import com.kzhou.spotifystreamer.ui.view.TopTrackView;

import java.util.ArrayList;

/**
 * User: Kevin
 * Date: 22/06/15
 * Time: 10:07 PM
 */
public class TopTrackControllerImpl implements TopTrackController {
    private TopTrackView topTrackView;
    private TopTrackExecutor executor;


    public TopTrackControllerImpl(TopTrackView topTrackView) {
        this.topTrackView = topTrackView;
        executor = new TopTrackExecutor(this);
    }

    @Override
    public void getTop10Tracks(String artistId) {
        topTrackView.showSearchProgressBar();
        executor.doSearchTop10Tracks(artistId);
    }

    @Override
    public void setTopTrackResult(ArrayList<ListItem> results) {
        topTrackView.hideSearchProgressBar();
        if (results.size() > 0) {
            topTrackView.refreshTrackList(results);
        } else {
            topTrackView.showListEmptyToast();
        }

    }
}
