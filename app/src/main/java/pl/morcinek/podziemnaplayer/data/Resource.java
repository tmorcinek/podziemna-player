package pl.morcinek.podziemnaplayer.data;

import lombok.Getter;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class Resource {

    @Getter
    private String name;

    @Getter
    private String musicUrl;

    @Getter
    private String videoUrl;

    @Getter
    private String youtubeUrl;

    public Resource(String name, String musicUrl, String videoUrl, String youtubeUrl) {
        this.name = name;
        this.musicUrl = musicUrl;
        this.videoUrl = videoUrl;
        this.youtubeUrl = youtubeUrl;
    }
}
