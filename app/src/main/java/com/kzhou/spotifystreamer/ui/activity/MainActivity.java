package com.kzhou.spotifystreamer.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kzhou.spotifystreamer.R;
import com.kzhou.spotifystreamer.ui.fragment.SearchArtistFragment;

/**
 * User: Kevin
 * Date: 28/06/15
 * Time: 10:10 PM
 */
public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        shouldDisplayHomeUp();

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new SearchArtistFragment());
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp(){
        boolean canback = getSupportFragmentManager().getBackStackEntryCount()>0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }

    public void openNewFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }
}
