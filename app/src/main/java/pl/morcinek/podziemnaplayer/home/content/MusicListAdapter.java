package pl.morcinek.podziemnaplayer.home.content;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

import pl.morcinek.podziemnaplayer.BuildConfig;
import pl.morcinek.podziemnaplayer.R;
import pl.morcinek.podziemnaplayer.data.Resource;
import pl.morcinek.podziemnaplayer.files.DownloadHandler;
import pl.morcinek.podziemnaplayer.general.adapter.AbstractRecyclerViewAdapter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class MusicListAdapter extends AbstractRecyclerViewAdapter<Resource, MusicListAdapter.ViewHolder> {

    private final File externalFilesDir;

    private OnResourceClickListener resourceClickListener;

    public MusicListAdapter(Context context, OnResourceClickListener resourceClickListener, OnItemClickListener<Resource> onItemClickListener) {
        super(context);
        this.resourceClickListener = resourceClickListener;
        setItemClickListener(onItemClickListener);

        externalFilesDir = context.getExternalFilesDir(BuildConfig.DOWNLOAD_DIRECTORY);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.music_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Resource resource = getItem(i);
        viewHolder.titleView.setText(resource.getName());
        if (isDownloaded(resource)) {
            registerOnClickListener(viewHolder, resource);
            viewHolder.titleView.setEnabled(true);
            viewHolder.downloadButton.setVisibility(View.GONE);
            viewHolder.downloadButton.setOnClickListener(null);
        } else {
            viewHolder.titleView.setEnabled(false);
            viewHolder.downloadButton.setVisibility(View.VISIBLE);
            viewHolder.downloadButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    resourceClickListener.onResourceClicked(view, resource);
                }
            });
        }
    }

    private boolean isDownloaded(Resource resource) {
        return new File(externalFilesDir, DownloadHandler.getUrlHashCode(resource.getMusicUrl())).exists();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView;
        private final ImageButton downloadButton;

        public ViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.title);
            downloadButton = (ImageButton) view.findViewById(R.id.download_button);
        }
    }

    public interface OnResourceClickListener {

        void onResourceClicked(View view, Resource item);
    }
}
