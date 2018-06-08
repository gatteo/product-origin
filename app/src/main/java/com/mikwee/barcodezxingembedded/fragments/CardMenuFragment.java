package com.mikwee.barcodezxingembedded.fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikwee.barcodezxingembedded.R;
import com.mikwee.barcodezxingembedded.activities.MainActivity;

public class CardMenuFragment extends Fragment {
    public static final String TAG = CardMenuFragment.class.getSimpleName();

    private CardMenuListener listener;

    private ImageView flashIcon;

    //------------------------------------ START LIFECYCLE -----------------------------------------

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.card_menu_fragment, container, false);
        initViews(v);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (MainActivity) context;

        //If phone has no flash
        if (!hasFlash(context)) {
            //hide icon
            flashIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    //------------------------------------- END LIFECYCLE ------------------------------------------

    private void initViews(View v) {
        //Right (Gallery) icon click listener,
        v.findViewById(R.id.icon_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.keyboardInputClicked();
            }
        });

        //Left (Flash) icon click listener
        flashIcon = v.findViewById(R.id.icon_flash);
        flashIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.torchClicked();
            }
        });

    }

    private boolean hasFlash(Context context) {
        return context.getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public interface CardMenuListener {

        void torchClicked();

        void keyboardInputClicked();

    }


}
