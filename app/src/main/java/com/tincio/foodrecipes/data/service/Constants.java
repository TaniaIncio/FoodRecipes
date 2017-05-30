package com.tincio.foodrecipes.data.service;


import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;

public class Constants {

    public static String KEY = "39335edf6af0e5ee10c4be3cded34eb1";
    public static String param = "api_key";
    public static String param_page = "page";
    public static class serviceNames{
        public static String BASE_RECIPES = "https://api.backendless.com/031B3089-8257-FFD1-FFA5-D28BDC932500/B17C0B5D-6590-15D0-FF32-A18099591A00/";
        public static String GET_MOVIES = "popular";
        public static String GET_IMAGE_MOVIES = "http://image.tmdb.org/t/p/w780";

/*
        public static String GET_TRAILERS(Integer id){
            StringBuilder builderBase = new StringBuilder();
            builderBase.append(BASE_MOVIES);
            builderBase.append(id);
            builderBase.append("/videos?");
            Uri builUri = Uri.parse(builderBase.toString()).buildUpon()
                    .appendQueryParameter(param,KEY).build();
            Log.i("taggg",builUri.toString());
            return builUri.toString();
        }

        public static String GET_LIST_MOVIES(String option, int page){
            StringBuilder builderBase = new StringBuilder();
            builderBase.append(BASE_MOVIES);
            builderBase.append(option);
            builderBase.append("?");
            Uri builUri = Uri.parse(builderBase.toString()).buildUpon()
                    .appendQueryParameter(param,KEY)
                    .appendQueryParameter(param_page,String.valueOf(page)).build();

            return builUri.toString();
        }
*/
    }
}