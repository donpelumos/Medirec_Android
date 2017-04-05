package com.jempton.medirec;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by BALE on 29/03/2017.
 */

public class ActivityProfile extends AppCompatActivity {
    EditText surname, firstname, address, dateOfBirth, phoneNumber, email, nextOfKinPhoneNumber, nextOfKinName, nextOfKinRelationship;
    ImageView profilePicture, backButton;
    LinearLayout fullnameFrame;
    TextView fullname, nextOfKin, addNextOfKin, editProfile;
    Spinner day, month, year;
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    int editState = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        robotoThin = Typeface.createFromAsset(getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getAssets(),"fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getAssets(),"fonts/avenir-next-regular.ttf");
        surname = (EditText)findViewById(R.id.surname);
        address = (EditText)findViewById(R.id.address);
        firstname = (EditText)findViewById(R.id.firstname);
        fullname = (TextView)findViewById(R.id.fullname);
        dateOfBirth = (EditText)findViewById(R.id.dateOfBirth);
        phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        nextOfKin = (TextView) findViewById(R.id.nextOfKin);
        addNextOfKin = (TextView) findViewById(R.id.addNextOfKin);
        profilePicture = (ImageView)findViewById(R.id.profilePicture);
        fullnameFrame = (LinearLayout) findViewById(R.id.fullnameFrame);
        email = (EditText)findViewById(R.id.email);
        nextOfKinName = (EditText)findViewById(R.id.nextOfKinName);
        profilePicture.setOnTouchListener(new ImageHighlighterOnTouchListener(profilePicture));
        nextOfKinRelationship = (EditText)findViewById(R.id.nextOfKinRelationship);
        nextOfKinPhoneNumber = (EditText)findViewById(R.id.nextOfKinPhoneNumber);
        addNextOfKin.setOnTouchListener(new TextHighlighterOnTouchListener(addNextOfKin));
        nextOfKinName.setTypeface(gothic, Typeface.NORMAL);
        nextOfKinPhoneNumber.setTypeface(gothic, Typeface.NORMAL);
        nextOfKinRelationship.setTypeface(gothic, Typeface.NORMAL);
        addNextOfKin.setVisibility(View.INVISIBLE);
        String udatax = nextOfKin.getText().toString();
        SpannableString contentx = new SpannableString(udatax);
        contentx.setSpan(new UnderlineSpan(), 0, udatax.length(), 0);
        nextOfKin.setText(contentx);

        address.setEnabled(false); email.setEnabled(false); phoneNumber.setEnabled(false); dateOfBirth.setEnabled(false);
        nextOfKinName.setEnabled(false); nextOfKinRelationship.setEnabled(false); nextOfKinPhoneNumber.setEnabled(false);

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
        addNextOfKin.setTypeface(gothic, Typeface.NORMAL);
        address.setTypeface(gothic, Typeface.NORMAL);
        email.setTypeface(gothic, Typeface.NORMAL);
        phoneNumber.setTypeface(gothic, Typeface.NORMAL);
        dateOfBirth.setTypeface(gothic, Typeface.NORMAL);
        nextOfKin.setTypeface(gothic, Typeface.BOLD);

        dateOfBirth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    final String [] days = new String [31];
                    for(int i=0; i<31; i++){
                        days[i] = String.valueOf(i+1);
                    }
                    final String [] months = {"January","February","March","April","May","june","July","August","September","October","November","December"};
                    final ArrayList<String> years = new ArrayList<String>();
                    for(int i=1931; i<=2017; i++){
                        years.add(String.valueOf(i));
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityProfile.this);
                    View view = getLayoutInflater().inflate(R.layout.dialog_calender,null);
                    builder.setView(view);
                    day = (Spinner)view.findViewById(R.id.daySpinner);
                    month = (Spinner)view.findViewById(R.id.monthSpinner);
                    year = (Spinner)view.findViewById(R.id.yearSpinner);

                    ArrayAdapter<String> a1 = new ArrayAdapter<String>(ActivityProfile.this, android.R.layout.simple_spinner_item, days);
                    ArrayAdapter<String> a2 = new ArrayAdapter<String>(ActivityProfile.this, android.R.layout.simple_spinner_item, months);
                    ArrayAdapter<String> a3 = new ArrayAdapter<String>(ActivityProfile.this, android.R.layout.simple_spinner_item, years);
                    a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    a3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    day.setAdapter(a1);
                    month.setAdapter(a2);
                    year.setAdapter(a3);
                    final AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            dateOfBirth.clearFocus();
                        }
                    });
                }
                return false;
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editState == 0){
                    editState = 1;
                    address.setEnabled(true); email.setEnabled(true); phoneNumber.setEnabled(false); dateOfBirth.setEnabled(true);
                    nextOfKinName.setEnabled(true); nextOfKinRelationship.setEnabled(true); nextOfKinPhoneNumber.setEnabled(true);
                    fullnameFrame.setVisibility(View.VISIBLE);
                    fullname.setVisibility(View.GONE);
                    editProfile.setText("Done");
                    editProfile.setBackground(getResources().getDrawable(R.drawable.background_rounded_button_full));
                }
                else{
                    editState = 0;
                    address.setEnabled(false); email.setEnabled(false); phoneNumber.setEnabled(false); dateOfBirth.setEnabled(false);
                    nextOfKinName.setEnabled(false); nextOfKinRelationship.setEnabled(false); nextOfKinPhoneNumber.setEnabled(false);
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
