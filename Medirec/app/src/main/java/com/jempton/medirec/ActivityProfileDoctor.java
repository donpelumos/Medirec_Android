package com.jempton.medirec;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BALE on 10/04/2017.
 */

public class ActivityProfileDoctor extends AppCompatActivity {
    ActionBar appBar;
    EditText surname, firstname, designation, phoneNumber, email, folioNumber, hospitalName;
    ImageView profilePicture, backButton;
    LinearLayout fullnameFrame;
    TextView fullname, editProfile;
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    int editState = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile_doctor);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        robotoThin = Typeface.createFromAsset(getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getAssets(),"fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getAssets(),"fonts/avenir-next-regular.ttf");
        surname = (EditText)findViewById(R.id.surname);
        firstname = (EditText)findViewById(R.id.firstname);
        fullname = (TextView)findViewById(R.id.fullname);
        phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        hospitalName = (EditText)findViewById(R.id.address);
        profilePicture = (ImageView)findViewById(R.id.profilePicture);
        fullnameFrame = (LinearLayout) findViewById(R.id.fullnameFrame);
        email = (EditText)findViewById(R.id.email);
        profilePicture.setOnTouchListener(new ImageHighlighterOnTouchListener(profilePicture));
        designation = (EditText)findViewById(R.id.designation);
        folioNumber = (EditText)findViewById(R.id.folioNumber);
        hospitalName.setEnabled(false);
        designation.setEnabled(false);
        email.setEnabled(false);

        editProfile = (TextView)findViewById(R.id.editProfile);
        editProfile.setOnTouchListener(new TextHighlighterOnTouchListener(editProfile));
        backButton = (ImageView)findViewById(R.id.backButton);
        backButton.setOnTouchListener(new ImageHighlighterOnTouchListener(backButton));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        surname.setTypeface(gothic, Typeface.BOLD);
        firstname.setTypeface(gothic, Typeface.BOLD);
        fullname.setTypeface(gothic, Typeface.BOLD);
        email.setTypeface(gothic, Typeface.NORMAL);
        folioNumber.setTypeface(gothic, Typeface.NORMAL);
        hospitalName.setTypeface(gothic, Typeface.NORMAL);
        designation.setTypeface(gothic, Typeface.NORMAL);
        phoneNumber.setTypeface(gothic, Typeface.NORMAL);


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editState == 0){
                    editState = 1;
                    email.setEnabled(true); phoneNumber.setEnabled(false); designation.setEnabled(false);hospitalName.setEnabled(true);
                    designation.setEnabled(true);
                    fullnameFrame.setVisibility(View.VISIBLE);
                    fullname.setVisibility(View.GONE);
                    editProfile.setText("Done");
                    editProfile.setBackground(getResources().getDrawable(R.drawable.background_rounded_button_full));
                }
                else{
                    editState = 0;
                    email.setEnabled(false); phoneNumber.setEnabled(false); designation.setEnabled(true);hospitalName.setEnabled(false);
                    designation.setEnabled(false); 
                    fullnameFrame.setVisibility(View.GONE);
                    fullname.setVisibility(View.VISIBLE);
                    editProfile.setText("Edit");
                    editProfile.setBackground(getResources().getDrawable(R.drawable.background_rounded_button_full_green));
                }
            }
        });
    }
    public float getScreenDensity(){
        return getResources().getDisplayMetrics().density;
    }
    private class ImageHighlighterOnTouchListener implements View.OnTouchListener {
        //This
        final ImageView imageButton;

        public ImageHighlighterOnTouchListener(final ImageView imageButton) {
            super();
            this.imageButton = imageButton;
        }

        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //grey color filter, you can change the color as you like
                imageButton.setAlpha((float) 0.6);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                imageButton.setAlpha((float) 1.0);
            }
            return false;
        }

    }
    private class ButtonHighlighterOnTouchListener implements View.OnTouchListener {
        //This
        final Button imageButton;

        public ButtonHighlighterOnTouchListener(final Button imageButton) {
            super();
            this.imageButton = imageButton;
        }

        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //grey color filter, you can change the color as you like
                imageButton.setAlpha((float) 0.6);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                imageButton.setAlpha((float) 1.0);
            }
            return false;
        }

    }
    private class TextHighlighterOnTouchListener implements View.OnTouchListener {
        //This
        final TextView imageButton;

        public TextHighlighterOnTouchListener(final TextView imageButton) {
            super();
            this.imageButton = imageButton;
        }

        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //grey color filter, you can change the color as you like
                imageButton.setAlpha((float)0.6);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                imageButton.setAlpha((float) 1.0);
            }
            return false;
        }

    }
}
