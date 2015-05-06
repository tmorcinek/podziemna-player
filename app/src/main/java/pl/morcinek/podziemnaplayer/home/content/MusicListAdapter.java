package pl.morcinek.podziemnaplayer.home.content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.morcinek.podziemnaplayer.R;
import pl.morcinek.podziemnaplayer.data.Resource;
import pl.morcinek.podziemnaplayer.general.adapter.AbstractRecyclerViewAdapter;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class MusicListAdapter extends AbstractRecyclerViewAdapter<Resource,MusicListAdapter.ViewHolder> {

    public MusicListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.music_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Resource education = getItem(i);
        viewHolder.titleView.setText(education.getName());
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView;

        public ViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.title);
        }
    }

}
