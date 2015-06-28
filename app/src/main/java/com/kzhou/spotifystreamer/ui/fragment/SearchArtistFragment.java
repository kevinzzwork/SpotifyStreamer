package com.kzhou.spotifystreamer.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.kzhou.spotifystreamer.Constants;
import com.kzhou.spotifystreamer.R;
import com.kzhou.spotifystreamer.controller.ArtistSearchController;
import com.kzhou.spotifystreamer.controller.ArtistSearchControllerImpl;
import com.kzhou.spotifystreamer.model.view.ListItem;
import com.kzhou.spotifystreamer.ui.activity.MainActivity;
import com.kzhou.spotifystreamer.ui.element.ItemListAdapter;
import com.kzhou.spotifystreamer.ui.element.ListItemClickedListener;
import com.kzhou.spotifystreamer.ui.view.DividerItemDecoration;
import com.kzhou.spotifystreamer.ui.view.SearchArtistView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * User: Kevin
 * Date: 22/06/15
 * Time: 9:33 PM
 */

public class SearchArtistFragment extends Fragment implements ListItemClickedListener, SearchArtistView {
    private static final int NUM_COLUMN = 1;
    private ArtistSearchController controller;
    private ItemListAdapter itemListAdapter;
    private ProgressDialog progressDialog;

    @InjectView(R.id.artistList)  RecyclerView artistList;
    @InjectView(R.id.artistSearchField) EditText artistSearchField;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.search_artist_fragment, container, false);

        ButterKnife.inject(this, rootView);
        controller = new ArtistSearchControllerImpl(this);
        initArtistSearchField();
        initArtistList();
        setupActionBar();

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void initArtistSearchField() {
        artistSearchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    controller.doSearchByArtistName(textView.getText().toString());
                }
                return false;
            }
        });
    }

    private void initArtistList() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), NUM_COLUMN, GridLayoutManager.VERTICAL, false);
        artistList.setLayoutManager(layoutManager);
        itemListAdapter = new ItemListAdapter(getActivity(), new ArrayList<ListItem>(), this, false);
        artistList.setAdapter(itemListAdapter);
        artistList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void setupActionBar() {
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.main_activity_label));
            actionBar.setSubtitle(null);
        }
    }

    @Override
    public void onItemClicked(int position) {
        controller.listItemClicked(itemListAdapter.getItem(position));
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
    public void refreshArtistList(ArrayList<ListItem> artistList) {
        itemListAdapter.refreshListByData(artistList);
    }

    @Override
    public void openTopTracksScreen(String artistId, String artistName) {
        TopTrackFragment fragment = new TopTrackFragment();
        Bundle arguments = new Bundle();
        arguments.putString(Constants.ARTIST_ID_KEY, artistId);
        arguments.putString(Constants.ARTIST_NAME_KEY, artistName);
        fragment.setArguments(arguments);
        ((MainActivity)getActivity()).openNewFragment(fragment);
    }
}
