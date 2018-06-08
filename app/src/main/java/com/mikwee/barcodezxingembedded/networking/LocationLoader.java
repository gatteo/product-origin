package com.mikwee.barcodezxingembedded.networking;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Create a Loader that fetches for latitude and longitude given country name
 */
public class LocationLoader extends AsyncTaskLoader<LatLng> {

    private String mUrl;

    public LocationLoader(Context context, String country) {
        super(context);
        //Create a valid endpoint url given a country
        mUrl = QueryUtils.createStringUrl(country);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public LatLng loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract LatLng obj.
        return QueryUtils.fetchLocationCoordinates(mUrl);
    }
}
