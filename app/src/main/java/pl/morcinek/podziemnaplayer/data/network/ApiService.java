package pl.morcinek.podziemnaplayer.data.network;

import java.util.List;

import pl.morcinek.podziemnaplayer.data.Resource;
import pl.morcinek.podziemnaplayer.general.network.Callback;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public interface ApiService {

    void getResources(Callback<List<Resource>> callback);
}
