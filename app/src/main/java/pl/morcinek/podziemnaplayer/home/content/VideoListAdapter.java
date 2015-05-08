package pl.morcinek.podziemnaplayer.home.content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pl.morcinek.podziemnaplayer.R;
import pl.morcinek.podziemnaplayer.data.Resource;
import pl.morcinek.podziemnaplayer.general.adapter.AbstractRecyclerViewAdapter;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class VideoListAdapter extends AbstractRecyclerViewAdapter<Resource, VideoListAdapter.ViewHolder> {

    public VideoListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.video_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resource resource = getItem(position);
        holder.titleView.setText(resource.getName());
        registerOnClickListener(holder, resource);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.icon);
        }
    }

}
