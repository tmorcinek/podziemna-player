package pl.morcinek.podziemnaplayer.data;

import com.google.common.collect.Lists;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class ResponseProcessor {

    public static final String CHARSET_NAME = "iso-8859-2";

    public static final String HREF_ATTRIBUTE = "href";

    public List<Resource> retrieveDataFromStream(InputStream inputStream) throws IOException {
        return extractWidgetNodesWithId(inputStream);
    }

    private List<Resource> extractWidgetNodesWithId(InputStream inputStream) throws IOException {
        Document document = Jsoup.parse(inputStream, CHARSET_NAME, "");
        List<Resource> resources = Lists.newArrayList();
        for (Element element : document.select("tr:has(td:has(a[href*=.mp3]))")) {
            Elements cells = element.getElementsByTag("td");
            Resource resource = new Resource(getFirstCellText(cells));
            for (int i = 1; i < cells.size(); i++) {
                setupResourceWithAttribute(resource, cells.get(i), ".mp3");
                setupResourceWithAttribute(resource, cells.get(i), ".3gp");
            }
            resources.add(resource);
        }
        return resources;
    }

    private void setupResourceWithAttribute(Resource resource, Element dataElement, String valueSuffix) {
        for (Element mp3Element : getElementsWithUrlSuffix(dataElement, valueSuffix)) {
            resource.setMusicUrl(mp3Element.attr(HREF_ATTRIBUTE));
        }
    }

    private String getFirstCellText(Elements cells) {
        return cells.get(0).text();
    }

    private Elements getElementsWithUrlSuffix(Element dataElement, String valueSuffix) {
        return dataElement.getElementsByAttributeValueEnding(HREF_ATTRIBUTE, valueSuffix);
    }
}