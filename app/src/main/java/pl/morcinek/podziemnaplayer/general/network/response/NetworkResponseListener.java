package pl.morcinek.podziemnaplayer.general.network.response;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public interface NetworkResponseListener<T> {

    public void success(T object);

    public void failure(Object error);
}