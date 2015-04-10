package pl.morcinek.podziemnaplayer.data.network;

import android.os.Handler;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import pl.morcinek.podziemnaplayer.data.Resource;
import pl.morcinek.podziemnaplayer.general.network.Callback;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class MockApiService implements ApiService {

    @Override
    public void getResources(final Callback<List<Resource>> callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Resource> resources = Lists.newArrayList(
                        new Resource("001 Czego chcą Polacy - bolesna prawda - zobacz koniecznie",
                                "http://sh183534.website.pl/podziemnatv/mp3/001_Czego_chc_Polacy_-_bolesna_prawda_-_zobacz_koniecznie.mp3",
                                "http://sh183534.website.pl/podziemnatv/video/001_Czego_chc_Polacy_-_bolesna_prawda_-_zobacz_koniecznie.3gp",
                                "https://www.youtube.com/watch?v=B6vvk7IpqxE"));
                callback.success(resources);
            }
        }, 500);
    }
}
