package com.kzhou.spotifystreamer.model.Executor;

import android.os.AsyncTask;

import com.kzhou.spotifystreamer.Constants;
import com.kzhou.spotifystreamer.controller.TopTrackController;
import com.kzhou.spotifystreamer.model.SpotifyProcess;
import com.kzhou.spotifystreamer.model.data.ListItem;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * User: Kevin
 * Date: 25/06/15
 * Time: 9:41 PM
 */
public class TopTrackExecutor {
    private TopTrackController controller;

    public TopTrackExecutor(TopTrackController controller) {
        this.controller = controller;
    }

    public void doSearchTop10Tracks(String artistId) {
        SpotifyProcess.getInstance().getSpotifyService().getArtistTopTrack(artistId, Constants.countryCodeMap,
                new Callback<Tracks>() {
                    @Override
                    public void success(Tracks tracks, Response response) {
                        ProcessResultTask processResultTask = new ProcessResultTask();
                        processResultTask.execute(tracks);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        if (error != null) {

                        }
                    }
                });
    }

    class ProcessResultTask extends AsyncTask<Tracks, Void, ArrayList<ListItem>> {
        private ArrayList<ListItem> parseArtistListResults(Tracks tracks) {
            ArrayList<ListItem> arrayList = new ArrayList<>();

            if (tracks.tracks.size() > 0) {
                for (int i = 0; i < tracks.tracks.size(); i++) {
                    Track track = tracks.tracks.get(i);
                    ListItem viewItem = new ListItem();
                    if (track.album.images.size() > 0) {
                        viewItem.setImageUrl(track.album.images.get(0) == null ? null : track.album.images.get(0).url);
                    }

                    viewItem.setName(track.name);
                    viewItem.setSubName(track.album.name);

                    arrayList.add(viewItem);
                }
            }

            return arrayList;
        }

        @Override
        protected ArrayList<ListItem> doInBackground(Tracks... args) {
            return parseArtistListResults(args[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<ListItem> results) {
            controller.setTopTrackResult(results);
        }
    }
}
