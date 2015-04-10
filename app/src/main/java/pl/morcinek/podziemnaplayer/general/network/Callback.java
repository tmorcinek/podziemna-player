package pl.morcinek.podziemnaplayer.general.network;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public interface Callback<T> {

    void success(T object);

    void failure(Exception error);
}
