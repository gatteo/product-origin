package com.mikwee.barcodezxingembedded.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikwee.barcodezxingembedded.R;
import com.mikwee.barcodezxingembedded.entities.BarcodeInfo;
import com.mikwee.barcodezxingembedded.networking.LocationLoader;

public class CardBarcodeInfoFragment extends Fragment
        implements OnMapReadyCallback, LoaderManager.LoaderCallbacks<LatLng> {

    public static final String TAG = CardBarcodeInfoFragment.class.getSimpleName();

    private static final int LOCATION_LOADER_ID = 1;
    private static final String KEY_LOCATION_EXTRA = "location_url_extra";

    private BarcodeInfo scannedBarcode;

    private GoogleMap mMap;
    private MapView mMapView;
    private ImageView flagImage;
    private TextView countryName;
    private TextView errorScreen;


    //----------------------------------- START LIFECYCLE ------------------------------------------

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.card_barcode_info_fragment, container, false);
        initViews(v, savedInstanceState);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateDetail(scannedBarcode);
    }

    //----------------------------------- END LIFECYCLE --------------------------------------------

    private void initViews(View v, Bundle sis) {

        //Flag
        flagImage = v.findViewById(R.id.flag_imageview);

        //Country name
        countryName = v.findViewById(R.id.country_textview);

        //Map View
        mMapView = v.findViewById(R.id.mapView);
        mMapView.onCreate(sis);
        mMapView.onResume();
        mMapView.getMapAsync(this);

        if (!hasInternetAccess(getContext()))
            mMapView.setVisibility(View.GONE);

        //Error screen
        errorScreen = v.findViewById(R.id.error_screen);

    }

    private boolean hasInternetAccess(Context context) {

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Called when a new barcode is scanned
     *
     * @param info @{Barcode} info object
     */
    public void updateDetail(BarcodeInfo info) {

        if (info == null)
            return;

        this.scannedBarcode = info;

        //Set flag
        if (scannedBarcode.isFlagValid())
            flagImage.setImageResource(info.getFlagRes());
        else
            flagImage.setVisibility(View.GONE);

        //Set countryName
        if (scannedBarcode.isCountryValid()) {
            countryName.setText(info.getCountry());
            //Set country location on map
            getLocationCoordinates();
        }else {
            countryName.setVisibility(View.GONE);
            mMapView.setVisibility(View.GONE);
        }

        //Check if there is nothing to show
        if(!scannedBarcode.isValid())
            errorScreen.setVisibility(View.VISIBLE);
        else
            errorScreen.setVisibility(View.GONE);

    }

    private void getLocationCoordinates() {

        Bundle b = new Bundle();
        b.putString(KEY_LOCATION_EXTRA, scannedBarcode.getCountry());

        LoaderManager loaderManager = getActivity().getSupportLoaderManager();

        // Get our Loader by calling getLoader and passing the ID we specified
        Loader<LatLng> loader = loaderManager.getLoader(LOCATION_LOADER_ID);

        // If the Loader was null, initialize it. Else, restart it.
        if (loader == null)
            loaderManager.initLoader(LOCATION_LOADER_ID, b, this);
        else
            loaderManager.restartLoader(LOCATION_LOADER_ID, b, this);


    }

    @Override //Map's ready for some manipulation
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (scannedBarcode != null)
            getLocationCoordinates();
    }

    //------------------------------ LOCATION LOADER CALLBACKS -------------------------------------

    @NonNull
    @Override
    public Loader<LatLng> onCreateLoader(int id, @Nullable Bundle args) {
        assert args != null;
        return new LocationLoader(getContext(), args.getString(KEY_LOCATION_EXTRA));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<LatLng> loader, LatLng country) {

        if (mMap == null || country == null) {
            mMapView.setVisibility(View.GONE);
            return;
        }

        mMapView.setVisibility(View.VISIBLE);

        //Removes all markers set previously
        mMap.clear();

        //Add marker and center location
        mMap.addMarker(new MarkerOptions().position(country).title(getResources().getString(R.string.card_map_marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(country, 3.0f));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<LatLng> loader) {

    }

}


//----------------------------- BLEAH METHODS
/*
    private void bleahInit() {
        this.countyCodeArrayList = new ArrayList();
        this.countyCodeArrayList2 = new ArrayList();

        this.countyCodeArrayList = loadJSONFromAsset();


        Iterator it = this.countyCodeArrayList.iterator();
        while (it.hasNext()) {
            CountryCode c = (CountryCode) it.next();
            if (c.getMarge() != 0) {
                this.countyCodeArrayList2.add(c);
                int val = c.getId();
                for (int k = 0; k < c.getMarge(); k++) {
                    val++;
                    this.countyCodeArrayList2.add(new CountryCode(val, c.getContry_name(), c.getMarge(), c.getFly(), c.getLongitude(), c.getLatitude()));
                }
            } else {
                this.countyCodeArrayList2.add(c);
            }
        }
    }

    private void bleahFind(String code) {
        int v = Integer.parseInt(code);
        Iterator it2 = this.countyCodeArrayList2.iterator();
        while (it2.hasNext()) {
            CountryCode c = (CountryCode) it2.next();
            if (c.getId() == v) {
                this.countryCode = c;
                Log.e(TAG, "found ");
            }
        }
    }

    private ArrayList<CountryCode> loadJSONFromAsset() {
        try {
            InputStream is = getActivity().getAssets().open("codes.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            try {
                JSONArray m_jArry = new JSONObject(json).getJSONArray("codes");
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    CountryCode countryCode = new CountryCode();
                    countryCode.setId(Integer.parseInt(jo_inside.getString("id")));
                    countryCode.setContry_name(jo_inside.getString("contry_name"));
                    countryCode.setMarge(jo_inside.getInt("marge"));
                    countryCode.setFly(jo_inside.getString("fly"));
                    countryCode.setLatitude(jo_inside.getString("latitude"));
                    countryCode.setLongitude(jo_inside.getString("longitude"));
                    this.countyCodeArrayList.add(countryCode);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String str = json;
            return this.countyCodeArrayList;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    */


