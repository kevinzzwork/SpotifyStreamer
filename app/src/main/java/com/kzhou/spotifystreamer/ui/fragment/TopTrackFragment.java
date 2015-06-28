package com.kzhou.spotifystreamer.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.kzhou.spotifystreamer.Constants;
import com.kzhou.spotifystreamer.R;
import com.kzhou.spotifystreamer.controller.TopTrackController;
import com.kzhou.spotifystreamer.controller.TopTrackControllerImpl;
import com.kzhou.spotifystreamer.model.view.ListItem;
import com.kzhou.spotifystreamer.ui.activity.MainActivity;
import com.kzhou.spotifystreamer.ui.element.ItemListAdapter;
import com.kzhou.spotifystreamer.ui.element.ListItemClickedListener;
import com.kzhou.spotifystreamer.ui.view.DividerItemDecoration;
import com.kzhou.spotifystreamer.ui.view.TopTrackView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * User: Kevin
 * Date: 22/06/15
 * Time: 9:41 PM
 */
public class TopTrackFragment extends Fragment implements ListItemClickedListener, TopTrackView {
    private static final int NUM_COLUMN = 1;
    private TopTrackController controller;
    private ItemListAdapter itemListAdapter;
    private ProgressDialog progressDialog;
    private String artistId;
    private String artistName;

    @InjectView(R.id.trackList) RecyclerView trackList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.track_list_activity, container, false);

        ButterKnife.inject(this, rootView);
        controller = new TopTrackControllerImpl(this);
        initArgument();
        initTrackList();
        setupActionBar();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!TextUtils.isEmpty(artistId)) {
            controller.getTop10Tracks(artistId);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initArgument() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            artistId = bundle.getString(Constants.ARTIST_ID_KEY);
            artistName = bundle.getString(Constants.ARTIST_NAME_KEY);
        }
    }

    private void initTrackList() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), NUM_COLUMN, GridLayoutManager.VERTICAL, false);
        trackList.setLayoutManager(layoutManager);
        itemListAdapter = new ItemListAdapter(getActivity(), new ArrayList<ListItem>(), this, true);
        trackList.setAdapter(itemListAdapter);
        trackList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void setupActionBar() {
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.track_list_fragment_label));
            actionBar.setSubtitle(artistName);
        }
    }

    @Override
    public void onItemClicked(int position) {

    }

    //
    // ArtistSearchView methods
    //
    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void showSearchProgressBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Searching...");
        }
        progressDialog.show();
    }

    @Override
    public void hideSearchProgressBar() {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }

    @Override
    public void refreshTrackList(ArrayList<ListItem> trackList) {
        itemListAdapter.refreshListByData(trackList);
    }

    @Override
    public void showListEmptyToast() {
        Toast.makeText(getActivity(), getString(R.string.top_track_list_empty), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openTrackStreamScreen() {

    }
}
