package com.mikwee.barcodezxingembedded.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mikwee.barcodezxingembedded.R;

public class CreditsActivity extends AppCompatActivity {
    TextView email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits_activity);
        //Initialize layout views
        initViews();
    }

    //Initialize layout views
    private void initViews() {
        email = findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"mikwee1@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Message from an admirer"); //todo translate
                i.putExtra(Intent.EXTRA_TEXT, "I love your app!"); //todo translate
                try {
                    startActivity(Intent.createChooser(i, "Email this awesome young developer")); //todo translate
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(CreditsActivity.this, "No mail app found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
