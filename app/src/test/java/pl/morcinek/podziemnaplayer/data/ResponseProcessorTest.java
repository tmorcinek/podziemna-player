package pl.morcinek.podziemnaplayer.data;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import pl.morcinek.podziemnaplayer.util.InputStreamProvider;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class ResponseProcessorTest {

    private final InputStreamProvider inputStreamProvider = new InputStreamProvider();

    private ResponseProcessor responseProcessor;

    @Before
    public void setUp() throws Exception {
        responseProcessor = new ResponseProcessor();
    }

    @Test
    public void testRetrieveDataFromStream() throws Exception {
        // given
        InputStream inputStream = inputStreamProvider.getStreamFromResource("content.html");

        // when
        List<Resource> resources = responseProcessor.retrieveDataFromStream(inputStream);

        // then
        assertThat(resources).isNotNull().isNotEmpty().hasSize(104);
    }
}
