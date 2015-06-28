package com.kzhou.spotifystreamer.model;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

/**
 * User: Kevin
 * Date: 25/06/15
 * Time: 9:43 PM
 */
public class SpotifyProcess {

    private static SpotifyProcess instance;
    private SpotifyApi spotifyApi;

    public static SpotifyProcess getInstance() {
        if (instance == null) {
            instance = new SpotifyProcess();
        }

        return instance;
    }

    public SpotifyService getSpotifyService() {
        if (spotifyApi == null) {
            spotifyApi = new SpotifyApi();
        }
        return spotifyApi.getService();
    }

}
