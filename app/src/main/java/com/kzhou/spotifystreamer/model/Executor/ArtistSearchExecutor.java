package com.kzhou.spotifystreamer.model.Executor;

import android.os.AsyncTask;

import com.kzhou.spotifystreamer.controller.ArtistSearchController;
import com.kzhou.spotifystreamer.model.SpotifyProcess;
import com.kzhou.spotifystreamer.model.data.ListItem;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * User: Kevin
 * Date: 15/06/15
 * Time: 10:22 PM
 */
public class ArtistSearchExecutor {
    private ArtistSearchController controller;

    public ArtistSearchExecutor(ArtistSearchController controller) {
        this.controller = controller;
    }

    public void doSearchByArtistName(String name) {

        SpotifyProcess.getInstance().getSpotifyService().searchArtists(name, new Callback<ArtistsPager>() {
            @Override
            public void success(ArtistsPager artistsPager, Response response) {
                ProcessResultTask processResultTask = new ProcessResultTask();
                processResultTask.execute(artistsPager);
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });

    }


    class ProcessResultTask extends AsyncTask<ArtistsPager, Void, ArrayList<ListItem>> {
        private ArrayList<ListItem> parseArtistListResults(ArtistsPager results) {
            ArrayList<ListItem> arrayList = new ArrayList<>();

            if (results.artists.items.size() > 0) {
                for (int i = 0; i < results.artists.items.size(); i++) {
                    Artist artist = results.artists.items.get(i);
                    ListItem viewItem = new ListItem();
                    if (artist.images.size() > 0) {
                        viewItem.setImageUrl(artist.images.get(0) == null ? null : artist.images.get(0).url);
                    }

                    viewItem.setName(artist.name);
                    viewItem.setId(artist.id);
                    arrayList.add(viewItem);
                }
            }

            return arrayList;
        }

        @Override
        protected ArrayList<ListItem> doInBackground(ArtistsPager... args) {
            return parseArtistListResults(args[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<ListItem> results) {
            controller.setSearchResult(results);
        }
    }
}
