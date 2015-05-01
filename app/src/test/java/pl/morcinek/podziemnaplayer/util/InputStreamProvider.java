package pl.morcinek.podziemnaplayer.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class InputStreamProvider {

    public InputStream getStreamFromResource(String name) throws FileNotFoundException {
        return new FileInputStream("src/test/resources/" + name);
    }
}
