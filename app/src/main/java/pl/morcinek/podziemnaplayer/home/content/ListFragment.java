package pl.morcinek.podziemnaplayer.home.content;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import pl.morcinek.podziemnaplayer.BuildConfig;
import pl.morcinek.podziemnaplayer.R;
import pl.morcinek.podziemnaplayer.data.Resource;
import pl.morcinek.podziemnaplayer.data.network.NetworkRequester;
import pl.morcinek.podziemnaplayer.files.DownloadHandler;
import pl.morcinek.podziemnaplayer.general.adapter.AbstractRecyclerViewAdapter;
import pl.morcinek.podziemnaplayer.general.progress.RefreshProgressController;
import pl.morcinek.podziemnaplayer.general.dagger.components.DaggerFragment;
import pl.morcinek.podziemnaplayer.general.handlers.RetryErrorHandler;
import pl.morcinek.podziemnaplayer.general.network.response.NetworkResponseListener;
import pl.morcinek.podziemnaplayer.general.ui.DividerItemDecoration;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class ListFragment extends DaggerFragment implements Runnable, SwipeRefreshLayout.OnRefreshListener, NetworkResponseListener<List<Resource>>,MusicListAdapter.OnResourceClickListener, AbstractRecyclerViewAdapter.OnItemClickListener<Resource> {

    @Inject
    RetryErrorHandler errorHandler;

    @Inject
    NetworkRequester networkRequester;

    @Inject
    DownloadHandler downloadHandler;

    protected RefreshProgressController refreshProgressController;

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;
    private File externalFilesDir;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.list_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupSwipeRefreshLayout(view);
        setupRefreshProgressController();

        setupRecyclerView(view);

        networkRequester.initialize(this, refreshProgressController);

        errorHandler.registerAction(this);
        onRefresh();

        externalFilesDir = getActivity().getExternalFilesDir(BuildConfig.DOWNLOAD_DIRECTORY);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        networkRequester.cancelRequest();
    }

    private void setupSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.accent, R.color.primary_dark);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void setupRefreshProgressController() {
        refreshProgressController = new RefreshProgressController(swipeRefreshLayout);
    }

    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.setAdapter(new MusicListAdapter(getActivity(), this, this));
    }

    @Override
    public void run() {
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        networkRequester.requestResources();
    }

    @Override
    public void success(List<Resource> object) {
        MusicListAdapter adapter = (MusicListAdapter) recyclerView.getAdapter();
        adapter.setList(object);
        invokeRecyclerViewAnimation();
    }

    private void invokeRecyclerViewAnimation() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
        fadeInAnimation.setDuration(getResources().getInteger(R.integer.config_listItemAnimTime));
        recyclerView.setLayoutAnimation(new LayoutAnimationController(fadeInAnimation));
        recyclerView.startLayoutAnimation();
    }

    @Override
    public void failure(Object error) {
        errorHandler.handleError(error);
    }

    @Override
    public void onResourceClicked(View view, Resource item) {
        downloadHandler.requestDownload(item.getMusicUrl());
    }

    @Override
    public void onItemClicked(Resource item) {
        File file = new File(externalFilesDir, DownloadHandler.getFileName(item.getMusicUrl()));
        try {
            mediaPlayer.setDataSource(getActivity(), Uri.fromFile(file));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
