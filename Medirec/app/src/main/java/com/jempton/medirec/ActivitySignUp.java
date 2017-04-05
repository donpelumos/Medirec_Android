package com.jempton.medirec;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by BALE on 30/11/2016.
 */

public class ActivitySignUp extends AppCompatActivity {
    ImageView leftArrowButton, rightArrowButton;
    Button userSignUpButton, doctorSignUpButton;
    TextView userType, termsLink;
    Spinner hospitalIDSpinner, hospitalIDSpinner2 ;
    int currentSignUpType = 1;
    LinearLayout userSignUpLayout, doctorSignUpLayout, nurseSignUpLayout, paramedicSignUpLayout;
    String[] signUpTypes = {"User", "Doctor", "Nurse"};
    Typeface robotoThin, robotoLight, gothic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        robotoThin = Typeface.createFromAsset(getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getAssets(),"fonts/Gothic.TTF");
        userSignUpLayout = (LinearLayout)findViewById(R.id.userSignUpLayout);
        doctorSignUpLayout = (LinearLayout)findViewById(R.id.doctorSignUpLayout);
        nurseSignUpLayout = (LinearLayout)findViewById(R.id.nurseSignUpLayout);
        paramedicSignUpLayout = (LinearLayout)findViewById(R.id.paramedicSignUpLayout);
        hospitalIDSpinner = (Spinner)findViewById(R.id.hospitalIDSpinner);
        hospitalIDSpinner2 = (Spinner)findViewById(R.id.hospitalIDSpinner2);
        ArrayAdapter<String> hospitalIDSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                new String[]{"-- Select Hospital ID --","Hospital ID 0001","Hospital ID 0002", "Hospital ID 0003","Hospital ID 0004",
                        "Hospital ID 0005"}){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }

            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        hospitalIDSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hospitalIDSpinner.setAdapter(hospitalIDSpinnerAdapter);
        hospitalIDSpinner2.setAdapter(hospitalIDSpinnerAdapter);
        leftArrowButton = (ImageView) findViewById(R.id.leftArrow);
        rightArrowButton = (ImageView) findViewById(R.id.rightArrow);
        leftArrowButton.setOnTouchListener(new ImageHighlighterOnTouchListener(leftArrowButton));
        rightArrowButton.setOnTouchListener(new ImageHighlighterOnTouchListener(rightArrowButton));
        userSignUpButton = (Button) findViewById(R.id.userSignUpButton);
        doctorSignUpButton = (Button) findViewById(R.id.doctorSignUpButton);
        userSignUpButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(userSignUpButton));
        doctorSignUpButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(doctorSignUpButton));
        userType = (TextView) findViewById(R.id.userType);
        userType.setText("Sign Up As A "+"User");
        userType.setTypeface(gothic);
        termsLink = (TextView)findViewById(R.id.termsLink);
        termsLink.setOnTouchListener(new TextHighlighterOnTouchListener(termsLink));
        String udatax = termsLink.getText().toString();
        SpannableString contentx = new SpannableString(udatax);
        contentx.setSpan(new UnderlineSpan(), 0, udatax.length(), 0);
        termsLink.setText(contentx);

        leftArrowButton.setVisibility(View.GONE);
        leftArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSignUpType--;
                userType.setText("Sign Up As A "+signUpTypes[currentSignUpType - 1]);
                if (currentSignUpType == 1) {
                    leftArrowButton.setVisibility(View.GONE);
                    rightArrowButton.setVisibility(View.VISIBLE);
                }
                else {
                    leftArrowButton.setVisibility(View.VISIBLE);
                    rightArrowButton.setVisibility(View.VISIBLE);
                }
                changeLayout(currentSignUpType);
            }
        });
        rightArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSignUpType++;
                userType.setText("Sign Up As A "+signUpTypes[currentSignUpType - 1]);
                if (currentSignUpType == 2) {
                    rightArrowButton.setVisibility(View.GONE);
                    leftArrowButton.setVisibility(View.VISIBLE);
                }
                else{
                    leftArrowButton.setVisibility(View.VISIBLE);
                    rightArrowButton.setVisibility(View.VISIBLE);
                }
                changeLayout(currentSignUpType);
            }
        });
    }

    public void changeLayout(int index){
        if(index == 1){
            userSignUpLayout.setVisibility(View.VISIBLE);
            doctorSignUpLayout.setVisibility(View.GONE);
            nurseSignUpLayout.setVisibility(View.GONE);
            paramedicSignUpLayout.setVisibility(View.GONE);
        }
        else if(index == 2){
            userSignUpLayout.setVisibility(View.GONE);
            doctorSignUpLayout.setVisibility(View.VISIBLE);
            nurseSignUpLayout.setVisibility(View.GONE);
            paramedicSignUpLayout.setVisibility(View.GONE);
        }
        else if(index == 3){
            userSignUpLayout.setVisibility(View.GONE);
            doctorSignUpLayout.setVisibility(View.GONE);
            nurseSignUpLayout.setVisibility(View.VISIBLE);
            paramedicSignUpLayout.setVisibility(View.GONE);
        }
        else if(index == 4){
            userSignUpLayout.setVisibility(View.GONE);
            doctorSignUpLayout.setVisibility(View.GONE);
            nurseSignUpLayout.setVisibility(View.GONE);
            paramedicSignUpLayout.setVisibility(View.VISIBLE);
        }
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
}
