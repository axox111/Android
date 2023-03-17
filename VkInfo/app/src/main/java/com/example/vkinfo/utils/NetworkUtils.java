package com.example.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_API_URL = "https://api.vk.com/";
    private static final String VK_USERS_METHOD = "method/users.get";
    private static final String VK_USER_ID = "user_id";
    private static final String VK_VERSION = "v";
    private static final String VK_TOKEN = "access_token";

    public static URL generateURL(String userId) {
        Uri builtUri = Uri.parse(VK_API_URL + VK_USERS_METHOD)
                .buildUpon()
                .appendQueryParameter(VK_TOKEN, ) //нужен токен
                .appendQueryParameter(VK_USER_ID, userId)
                .appendQueryParameter(VK_VERSION, "5.89")
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public static String getResponseFromURL (URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
        InputStream in = urlConnection.getInputStream();
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");
        boolean hasInput = scanner.hasNext();
        if (hasInput){
            return scanner.next();
        } else{
            return null;
        }
        } finally {
            urlConnection.disconnect();
        }
    }
}
