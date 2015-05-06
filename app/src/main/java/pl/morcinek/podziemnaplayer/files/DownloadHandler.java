package pl.morcinek.podziemnaplayer.files;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class DownloadHandler {

    private Context context;

    private DownloadManager downloadManager;

    public DownloadHandler(Context context, DownloadManager downloadManager) {
        this.context = context;
        this.downloadManager = downloadManager;
    }

    public void requestDownload(String url) {
        String uri = "http://sh183534.website.pl/podziemnatv/mp3/001_Czego_chc_Polacy_-_bolesna_prawda_-_zobacz_koniecznie.mp3";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));

        //Restrict the types of networks over which this download may proceed.
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //Set whether this download may proceed over a roaming connection.
        request.setAllowedOverRoaming(false);
        //Set the title of this download, to be displayed in notifications (if enabled).
        request.setTitle("My Data Download");
        //Set a description of this download, to be displayed in notifications (if enabled)
        request.setDescription("Android Data download using DownloadManager.");
        //Set the local destination for the downloaded file to a path within the application's external files directory
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_MOVIES,"CountryList.json");

        //Enqueue a new download and same the referenceId
        downloadManager.enqueue(request);

    }

    public String getFileName(String url) {
        String[] strings = url.split("/");
        return strings[strings.length - 1];
    }

    public String getHashCode(String fileName) {
        return "podziemnaTV_"+ fileName.hashCode() + fileName.substring(fileName.length() - 4, fileName.length());
    }
}
