package pl.morcinek.podziemnaplayer.general.network.response;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public interface ProgressController {

    void preExecute();

    void postExecuteWithSuccess(boolean success);
}
