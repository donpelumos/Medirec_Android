package com.jempton.medirec;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BALE on 29/11/2016.
 */

public class ActivityLogin extends AppCompatActivity {
    EditText emailText, passwordText;
    Button loginButton;
    Spinner userType;
    TextView signUpLink, notJoined;
    Typeface robotoThin, robotoLight, gothic;
    int loginType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        robotoThin = Typeface.createFromAsset(getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getAssets(),"fonts/Gothic.TTF");
        emailText = (EditText)findViewById(R.id.emailText);
        emailText.setTypeface(gothic);
        passwordText = (EditText)findViewById(R.id.passwordText);
        passwordText.setTypeface(gothic);
        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnTouchListener(new ButtonHighlighterOnTouchListener(loginButton));
        userType = (Spinner)findViewById(R.id.userType);
        ArrayAdapter<String> a1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"--Select Type--","User",
        "Doctor"}){
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
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(a1);
        userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loginType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        notJoined = (TextView)findViewById(R.id.notJoined);
        notJoined.setTypeface(gothic);
        signUpLink = (TextView)findViewById(R.id.signUpLink);
        String udatax = signUpLink.getText().toString();
        SpannableString contentx = new SpannableString(udatax);
        contentx.setSpan(new UnderlineSpan(), 0, udatax.length(), 0);
        signUpLink.setText(contentx);
        signUpLink.setTypeface(gothic);
        signUpLink.setOnTouchListener(new TextHighlighterOnTouchListener(signUpLink));
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivitySignUp.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(loginType) {
                    case 0:
                        Toast.makeText(ActivityLogin.this,"Select a valid type", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent = new Intent(ActivityLogin.this, ActivityFeed.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent doctorIntent = new Intent(ActivityLogin.this, ActivityFeedDoctor.class);
                        startActivity(doctorIntent);
                        break;
                }
            }
        });
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
                imageButton.setAlpha((float)0.6);
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