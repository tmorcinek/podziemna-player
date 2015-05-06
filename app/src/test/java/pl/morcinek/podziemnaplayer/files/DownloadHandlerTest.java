package pl.morcinek.podziemnaplayer.files;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class DownloadHandlerTest {

    private DownloadHandler downloadHandler;

    @Before
    public void setUp() throws Exception {
        downloadHandler = new DownloadHandler(null, null);

    }

    @Test
    public void getFileName() throws Exception {
        // given
        String uri = "http://sh183534.website.pl/podziemnatv/mp3/001_Czego_chc_Polacy_-_bolesna_prawda_-_zobacz_koniecznie.mp3";

        // when
        String fileName = downloadHandler.getFileName(uri);

        // then
        assertThat(fileName).isEqualTo("001_Czego_chc_Polacy_-_bolesna_prawda_-_zobacz_koniecznie.mp3");
    }

    @Test
    public void getHashCode() throws Exception {
        // given
        String fileName = "001_Czego_chc_Polacy_-_bolesna_prawda_-_zobacz_koniecznie.mp3";

        // when
        String hashCode = downloadHandler.getHashCode(fileName);

        // then
        assertThat(hashCode).isEqualTo("podziemnaTV_-842987883.mp3");
    }
}