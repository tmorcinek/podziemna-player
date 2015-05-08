package pl.morcinek.podziemnaplayer.files;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import pl.morcinek.podziemnaplayer.BuildConfig;
import pl.morcinek.podziemnaplayer.R;

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
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setVisibleInDownloadsUi(false);
        request.setTitle(getFileName(url));
        request.setDescription(context.getString(R.string.download_file_description));
        String hashCode = getUrlHashCode(url);
        Log.e("HASH_CODE", hashCode);
        request.setDestinationInExternalFilesDir(context, BuildConfig.DOWNLOAD_DIRECTORY, hashCode);
//        request.allowScanningByMediaScanner();

        downloadManager.enqueue(request);
    }

    private String getDirectory(String url) {
        return isMp3(url) ? Environment.DIRECTORY_MUSIC : Environment.DIRECTORY_MOVIES;
//        return Environment.DIRECTORY_DOWNLOADS;
    }

    private boolean isMp3(String url) {
        return url.endsWith(".mp3");
    }

    public static String getFileName(String url) {
        String[] strings = url.split("/");
        return strings[strings.length - 1];
    }

    public static String getUrlHashCode(String url) {
        String fileName = getFileName(url);
        return "podziemnaTV_" + fileName.hashCode() + fileName.substring(fileName.length() - 4, fileName.length());
    }
}
