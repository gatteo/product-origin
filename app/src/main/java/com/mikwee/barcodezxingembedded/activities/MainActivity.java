package com.mikwee.barcodezxingembedded.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.mikwee.barcodezxingembedded.R;
import com.mikwee.barcodezxingembedded.entities.BarcodeInfo;
import com.mikwee.barcodezxingembedded.fragments.CardBarcodeInfoFragment;
import com.mikwee.barcodezxingembedded.fragments.CardMenuFragment;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CardMenuFragment.CardMenuListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private View mContentView;
    private DecoratedBarcodeView barcodeView;
    private TextView overlayText;
    private FrameLayout overlayCard;
    private View overlayBlack;
    private FloatingActionButton creditsFab;

    //If the card fragment is in front of camera preview
    private boolean torchOn = false;
    private boolean cardInFront = false;
    private String KEY_CARD_IN_FRONT = "key_card_in_front";
    private BeepManager beepManager;

    private final Handler mHideHandler = new Handler();

    //-------------------------------------- START LIFECYCLE ---------------------------------------

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initViews();

        inflateBarcodeInfoFragment();

        inflateMenuFragment();

        if (savedInstanceState != null) {
            cardInFront = savedInstanceState.getBoolean(KEY_CARD_IN_FRONT);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(KEY_CARD_IN_FRONT, cardInFront);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();

        if (cardInFront) {
            mContentView.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressWarnings("deprecation")
                        @Override
                        public void onGlobalLayout() {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                mContentView.getViewTreeObserver()
                                        .removeOnGlobalLayoutListener(this);
                            } else {
                                mContentView.getViewTreeObserver()
                                        .removeGlobalOnLayoutListener(this);
                            }

                            //Finally when everything is ready
                            showCard();

                        }
                    });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    //-------------------------------------- END LIFECYCLE -----------------------------------------

    /**
     * View initialization
     */
    private void initViews() {
        setContentView(R.layout.main_activity);

        //Root
        mContentView = findViewById(R.id.root_container);

        //Barcode Camera todo implement custom view
        barcodeView = findViewById(R.id.barcode_scanner);

        Collection<BarcodeFormat> formats = Arrays
                .asList(BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.EAN_8,
                        BarcodeFormat.EAN_13, BarcodeFormat.RSS_14);

        barcodeView.getBarcodeView().setDecoderFactory(new DefaultDecoderFactory(formats));
        barcodeView.decodeContinuous(callback);
        barcodeView.setStatusText(""); //hides text on bottom

        //Beep when barcode found
        beepManager = new BeepManager(this);

        //Only text over camera view
        overlayText = findViewById(R.id.overlay_text);
        setTitleAnimation();

        //Card containing barcode info or menu
        overlayCard = findViewById(R.id.fragment_container);

        //Dark overlay that hides camera view
        overlayBlack = findViewById(R.id.overlay_black);
        overlayBlack.setAlpha(0.0f);
        overlayBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardInFront)
                    hideCard();
            }
        });

        //Fab, opens credits
        creditsFab = findViewById(R.id.fab_credits);
        creditsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(i);
            }
        });


    }

    /**
     * Inflate Menu Fragment
     */
    private void inflateMenuFragment() {
        //Try to see if fragment manager has an instance
        CardMenuFragment fragment = (CardMenuFragment) getSupportFragmentManager().findFragmentByTag(CardMenuFragment.TAG);

        //If not recreate it
        if (fragment == null) {
            fragment = new CardMenuFragment();
        }

        //and add fragment to container
        if (!fragment.isAdded())
            getSupportFragmentManager().beginTransaction()
                    .add(overlayCard.getId(), fragment, CardMenuFragment.TAG)
                    .commit();

        //attach if fragment is detached
        if (fragment.isDetached())
            getSupportFragmentManager().beginTransaction()
                    .attach(fragment)
                    .commit();

    }

    /**
     * Inflate BarcodeInfo Fragment
     */
    private void inflateBarcodeInfoFragment() {

        //Try to see if fragment manager has an instance
        CardBarcodeInfoFragment fragment = (CardBarcodeInfoFragment) getSupportFragmentManager().findFragmentByTag(CardBarcodeInfoFragment.TAG);

        //If not recreate it
        if (fragment == null)
            fragment = new CardBarcodeInfoFragment();

        //and add fragment to container
        if (!fragment.isAdded())
            getSupportFragmentManager().beginTransaction()
                    .add(overlayCard.getId(), fragment, CardBarcodeInfoFragment.TAG)
                    .commit();
    }

    @Override
    public void onBackPressed() {

        if (cardInFront)
            hideCard();
        else
            super.onBackPressed();

    }


    private void showCard() {
        cardInFront = true;

        //Detach menu fragment
        CardMenuFragment fragment = (CardMenuFragment) getSupportFragmentManager().findFragmentByTag(CardMenuFragment.TAG);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .detach(fragment)
                .commit();

        //Handle card animation in
        showCardAnim();

        //Handle FAB animation
        showFab();

        //Change top title
        toggleTitleText();
    }

    public void hideCard() {
        cardInFront = false;

        //Attach menu fragment
        CardMenuFragment fragment = (CardMenuFragment) getSupportFragmentManager().findFragmentByTag(CardMenuFragment.TAG);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .attach(fragment)
                .commit();

        //Handle card animation out
        hideCardAnim();

        //Handle FAB animation
        hideFab();

        //Change top title
        toggleTitleText();
    }

    private void toggleTitleText() {
        if (cardInFront)
            overlayText.setText(R.string.top_title_yes_card);
        else
            overlayText.setText(R.string.top_title_no_card);
    }

    //------------------------------------- CARD MENU LISTENER -------------------------------------

    @Override
    public void torchClicked() {
        if (torchOn)
            barcodeView.setTorchOff();
        else
            barcodeView.setTorchOn();

        torchOn = !torchOn;
    }

    @Override
    public void keyboardInputClicked() {

        final EditText txtUrl = new EditText(this);

        txtUrl.setHint("ex. 12345678");

        new AlertDialog.Builder(this)

                .setTitle(getString(R.string.dialog_insert_code))

                .setView(txtUrl)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String barcodeStr = txtUrl.getText().toString();

                        barcodeScanned(barcodeStr);
                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                })
                .show();

    }


    private void barcodeScanned(String barcodeString) {
        //Retrieve Barcode info fragment
        CardBarcodeInfoFragment fragment = (CardBarcodeInfoFragment) getSupportFragmentManager()
                .findFragmentByTag(CardBarcodeInfoFragment.TAG);

        //The actual Barcode number
        BarcodeInfo barcodeInfo = new BarcodeInfo(barcodeString);

        //Update info on fragment
        fragment.updateDetail(barcodeInfo);

        //Finally alert and bring up the card
        beepManager.playBeepSoundAndVibrate();

        showCard();
    }

    //------------------------------------- HIDE NAVIGATION BAR ------------------------------------

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide() {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, 400);
    }

    private final Runnable mHideRunnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    //-------------------------------------- BARCODE DECODER ---------------------------------------

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {

            //If scan results positive when a card is in front
            if (cardInFront || result == null)
                return;

            barcodeScanned(result.getText());

        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }

    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }


    //--------------------------------------- ANIMATIONS -------------------------------------------

    private void setTitleAnimation() {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        overlayText.startAnimation(anim);
    }

    private void showCardAnim() {

        //Calculate how much up to move card
        float upShift = -overlayCard.getHeight()                              //card height
                + getResources().getDimension(R.dimen.menu_height)             //menu height
                + getResources().getDimension(R.dimen.card_offscreen);         //part offscreen


        //Create Slide up card animation
        ObjectAnimator slideUpAnimator = ObjectAnimator.
                ofFloat(overlayCard, "translationY",
                        upShift);

        //Create dark hover view animation
        ObjectAnimator darkHoverAnimator = ObjectAnimator.
                ofFloat(overlayBlack, "alpha",
                        0.0f, 0.75f);

        //Play all animations
        AnimatorSet s = new AnimatorSet();
        s.playTogether(slideUpAnimator, darkHoverAnimator);
        s.setDuration(300);
        s.setInterpolator(new FastOutSlowInInterpolator());
        s.start();
    }

    private void hideCardAnim() {

        //Slide card back to initial position
        ObjectAnimator slideUpAnimator = ObjectAnimator.
                ofFloat(overlayCard, "translationY", 0);

        ObjectAnimator darkHoverAnimator = ObjectAnimator.
                ofFloat(overlayBlack, "alpha",
                        0.0f);

        AnimatorSet s = new AnimatorSet();
        s.playTogether(slideUpAnimator, darkHoverAnimator);
        s.setDuration(300);
        s.setInterpolator(new FastOutSlowInInterpolator());
        s.start();

    }

    private void hideFab()


































































    {

        creditsFab.animate()
                .scaleX(0)
                .scaleY(0)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(300)
                .withEndAction(
                        new Runnable() {
                            @Override
                            public void run() {
                                creditsFab.setVisibility(View.GONE);
                            }
                        })
                .start();
    }

    private void showFab() {
        creditsFab.setVisibility(View.VISIBLE);

        creditsFab.animate()
                .scaleX(1)
                .scaleY(1)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(300).start();
    }

}
