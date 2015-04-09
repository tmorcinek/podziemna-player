package pl.morcinek.podziemnaplayer.home.drawer;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pl.morcinek.podziemnaplayer.R;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class DrawerController implements AdapterView.OnItemClickListener {

    public static final int HIDE_DRAWER_LIST_DELAY_MILLIS = 150;

    private final DrawerLayout drawerLayout;

    private final ListView drawerListView;

    public DrawerController(FragmentActivity activity, DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;

        drawerListView = (ListView) activity.findViewById(R.id.drawer_list);
        drawerListView.setOnItemClickListener(this);
        setupDrawerListAdapter(activity);
    }

    private void setupDrawerListAdapter(FragmentActivity activity) {
        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(activity);
        drawerListAdapter.setList(prepareDrawerItemList());
        drawerListView.setAdapter(drawerListAdapter);
    }

    private List<DrawerItem> prepareDrawerItemList() {
        ArrayList<DrawerItem> drawerItems = new ArrayList<>();
        return drawerItems;
    }

    private void selectItemAtPosition(DrawerItem drawerItem, int position) {
        //TODO implement what to do in case of selection
        drawerListView.setItemChecked(position, true);
        hideDrawerWithDelay();
    }

    private void hideDrawerWithDelay() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                drawerLayout.closeDrawer(drawerListView);
            }
        }, HIDE_DRAWER_LIST_DELAY_MILLIS);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItemAtPosition((DrawerItem) parent.getItemAtPosition(position), position);
    }

    public void showDefaultFragment() {
//        selectItemAtPosition((DrawerItem) drawerListView.getItemAtPosition(0), 0);
    }
}