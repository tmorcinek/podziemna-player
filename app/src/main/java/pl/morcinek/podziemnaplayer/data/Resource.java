package pl.morcinek.podziemnaplayer.data;

import hrisey.Parcelable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
@Parcelable
@NoArgsConstructor
public class Resource {

    @Getter
    private String name;

    @Getter
    @Setter
    private String musicUrl;

    @Getter
    @Setter
    private String videoUrl;

    @Getter
    @Setter
    private String youtubeUrl;

    public Resource(String name) {
        this.name = name;
    }

    public Resource(String name, String musicUrl, String videoUrl, String youtubeUrl) {
        this.name = name;
        this.musicUrl = musicUrl;
        this.videoUrl = videoUrl;
        this.youtubeUrl = youtubeUrl;
    }
}
