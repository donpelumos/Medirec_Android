package com.jempton.medirec;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import utilities.RoundRectCornerImageView;

/**
 * Created by BALE on 05/04/2017.
 */

public class ActivityAddNewHospital extends AppCompatActivity {
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    ActionBar appBar;
    ImageView searchButton, cancelSearchButton;
    LinearLayout searchFrame, searchResultFrame;
    ListView addNewDoctorList;
    ArrayList<ListAddNewDoctor> doctors;
    AdapterAddNewDoctor addNewDoctorAdapter;
    EditText searchBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        robotoThin = Typeface.createFromAsset(getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getAssets(),"fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getAssets(),"fonts/avenir-next-regular.ttf");
        appBar = getSupportActionBar();
        appBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.backgroundRed)));
        appBar.setDisplayShowCustomEnabled(true);
        appBar.setDisplayShowTitleEnabled(false);
        appBar.setHomeButtonEnabled(true);
        appBar.setDisplayHomeAsUpEnabled(true);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.layout_action_bar, null);
        ((TextView)v.findViewById(R.id.title)).setText(this.getTitle());
        ((TextView)v.findViewById(R.id.title)).setTypeface(gothic, Typeface.BOLD);
        this.getSupportActionBar().setCustomView(v);
        setContentView(R.layout.activity_add_new_hospital);
        searchButton = (ImageView)findViewById(R.id.searchButton);
        searchBar = (EditText)findViewById(R.id.searchBar);
        searchBar.setTypeface(gothic, Typeface.NORMAL);
        cancelSearchButton = (ImageView)findViewById(R.id.cancelSearchButton);

        searchButton.setOnTouchListener(new ImageHighlighterOnTouchListener(searchButton));
        cancelSearchButton.setOnTouchListener(new ImageHighlighterOnTouchListener(cancelSearchButton));

        searchFrame = (LinearLayout)findViewById(R.id.searchFrame);
        searchResultFrame = (LinearLayout)findViewById(R.id.searchResultFrame);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.requestFocus();
                searchButton.setVisibility(View.GONE);
                searchFrame.setVisibility(View.VISIBLE);
            }
        });
        cancelSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchButton.setVisibility(View.VISIBLE);
                searchFrame.setVisibility(View.GONE);
            }
        });
        searchResultFrame.addView(addHospitals("Hospital Name","Hospital Address - No. X, Street Random, Off Road Random, Random Area Name, Lagos State.",
                "email@domail_name.com","08012345678","www.hopital_website.com",0,0));
        searchResultFrame.addView(addHospitals("Hospital Name","Hospital Address - No. X, Street Random, Off Road Random, Random Area Name, Lagos State.",
                "email@domail_name.com","08012345678","www.hopital_website.com",1,0));
        searchResultFrame.addView(addHospitals("Hospital Name","Hospital Address - No. X, Street Random, Off Road Random, Random Area Name, Lagos State.",
                "email@domail_name.com","08012345678","www.hopital_website.com",2,1));
        searchResultFrame.addView(addHospitals("Hospital Name","Hospital Address - No. X, Street Random, Off Road Random, Random Area Name, Lagos State.",
                "email@domail_name.com","08012345678","www.hopital_website.com",3,1));
        searchResultFrame.addView(addHospitals("Hospital Name","Hospital Address - No. X, Street Random, Off Road Random, Random Area Name, Lagos State.",
                "email@domail_name.com","08012345678","www.hopital_website.com",4,1));
        searchResultFrame.addView(addHospitals("Hospital Name","Hospital Address - No. X, Street Random, Off Road Random, Random Area Name, Lagos State.",
                "email@domail_name.com","08012345678","www.hopital_website.com",2,0));
    }
    private LinearLayout addHospitals(String hospName, String hospAddress, String hospEmail, String hospPhoneNo, String hospWebsite,
                                      int hospImage, final int userAdded){
        int [] sampleImages = {R.drawable.hospital1,R.drawable.hospital2,R.drawable.hospital3,R.drawable.hospital4,R.drawable.hospital5};
        LinearLayout rowCase = new LinearLayout(this);
        LinearLayout.LayoutParams rowCaseParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        rowCase.setLayoutParams(rowCaseParams);
        rowCaseParams.setMargins(0,(int)(5*getScreenDensity()),0,(int)(5*getScreenDensity()));
        rowCase.setBackground(getResources().getDrawable(R.drawable.background_rounded_dialog));

        FrameLayout rowFrame = new FrameLayout(this);
        FrameLayout.LayoutParams rowFrameParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        rowFrame.setLayoutParams(rowFrameParams);

        LinearLayout case1 = new LinearLayout(this);
        LinearLayout.LayoutParams case1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)(165*getScreenDensity()));
        case1.setLayoutParams(case1Params);
        case1.setOrientation(LinearLayout.HORIZONTAL);
        case1.setGravity(Gravity.CENTER_VERTICAL);
        case1.setPadding((int)(10*getScreenDensity()),(int)(10*getScreenDensity()),(int)(10*getScreenDensity()),(int)(10*getScreenDensity()));

        LinearLayout case2 = new LinearLayout(this);
        LinearLayout.LayoutParams case2Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        case2Params.setMargins((int)(115*getScreenDensity()),0,0,0);
        case2.setLayoutParams(case2Params);
        case2.setOrientation(LinearLayout.VERTICAL);
        case2.setGravity(Gravity.CENTER_VERTICAL);

        TextView name = new TextView(this);
        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        name.setLayoutParams(nameParams);
        name.setText(hospName);
        name.setTypeface(gothic, Typeface.BOLD);
        name.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        name.setTextColor(Color.rgb(18,18,18));

        LinearLayout addressFrame = new LinearLayout(this);
        LinearLayout.LayoutParams addressFrameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        addressFrame.setLayoutParams(addressFrameParams);
        addressFrameParams.setMargins(0,(int)(5*getScreenDensity()),0,0);
        addressFrame.setGravity(Gravity.CENTER_VERTICAL);
        addressFrame.setOrientation(LinearLayout.HORIZONTAL);

        ImageView addressIcon = new ImageView(this);
        LinearLayout.LayoutParams addressIconParams = new LinearLayout.LayoutParams((int)(20*getScreenDensity()),(int)(20*getScreenDensity()));
        addressFrameParams.setMargins((int)(-2*getScreenDensity()),0,0,0);
        addressIcon.setImageResource(R.drawable.location);
        addressIcon.setLayoutParams(addressIconParams);

        TextView address = new TextView(this);
        LinearLayout.LayoutParams addressParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        addressParams.setMargins((int)(1*getScreenDensity()),0,0,0);
        address.setLayoutParams(addressParams);
        address.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
        address.setTextColor(Color.rgb(35,35,35));
        address.setText(hospAddress);
        address.setTypeface(gothic,Typeface.NORMAL);

        addressFrame.addView(addressIcon);
        addressFrame.addView(address);

        View line = new View(this);
        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)(1*getScreenDensity()));
        viewParams.setMargins(0,(int)(5*getScreenDensity()),0,0);
        line.setLayoutParams(viewParams);
        line.setBackgroundColor(Color.rgb(221,221,221));

        TextView email = new TextView(this);
        LinearLayout.LayoutParams emailParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        emailParams.setMargins(0,(int)(3*getScreenDensity()),0,0);
        email.setLayoutParams(emailParams);
        email.setTextColor(Color.rgb(52,52,52));
        email.setTypeface(gothic, Typeface.NORMAL);
        email.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        email.setText(hospEmail);

        TextView phone = new TextView(this);
        LinearLayout.LayoutParams phoneParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        phoneParams.setMargins(0,(int)(3*getScreenDensity()),0,0);
        phone.setLayoutParams(emailParams);
        phone.setTextColor(Color.rgb(52,52,52));
        phone.setTypeface(gothic, Typeface.NORMAL);
        phone.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        phone.setText(hospPhoneNo);
        phone.setClickable(true);
        phone.setOnTouchListener(new TextHighlighterOnTouchListener(phone));

        TextView website = new TextView(this);
        LinearLayout.LayoutParams websiteParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        websiteParams.setMargins(0,(int)(3*getScreenDensity()),0,0);
        website.setLayoutParams(websiteParams);
        website.setTextColor(Color.rgb(202,8,15));
        website.setTypeface(gothic, Typeface.NORMAL);
        SpannableString s = new SpannableString(hospWebsite);
        s.setSpan(new UnderlineSpan(),0,hospWebsite.toString().length(),0);
        website.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        website.setText(s);
        website.setClickable(true);
        website.setOnTouchListener(new TextHighlighterOnTouchListener(website));

        RoundRectCornerImageView image = new RoundRectCornerImageView(this);
        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams((int)(105*getScreenDensity()),(int)(145*getScreenDensity()));
        image.setLayoutParams(imageParams);
        imageParams.setMargins((int)(10*getScreenDensity()),(int)(10*getScreenDensity()),0,0);
        image.setImageDrawable(getResources().getDrawable(sampleImages[hospImage]));
        image.setClickable(true);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setOnTouchListener(new ImageHighlighterOnTouchListener(image));

        LinearLayout addOrRemoveLayout = new LinearLayout(this);
        LinearLayout.LayoutParams addOrRemoveLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        addOrRemoveLayout.setLayoutParams(addOrRemoveLayoutParams);
        addOrRemoveLayout.setGravity(Gravity.RIGHT);

        TextView addOrRemove = new TextView(this);
        LinearLayout.LayoutParams addOrReoveParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        addOrReoveParams.setMargins(0,(int)(10*getScreenDensity()),(int)(10*getScreenDensity()),0);
        addOrRemove.setLayoutParams(addOrReoveParams);
        addOrRemove.setPadding((int)(4*getScreenDensity()),(int)(4*getScreenDensity()),(int)(4*getScreenDensity()),(int)(4*getScreenDensity()));
        addOrRemove.setTextColor(Color.rgb(255,255,255));
        addOrRemove.setClickable(true);
        addOrRemove.setTypeface(gothic, Typeface.NORMAL);
        addOrRemove.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        addOrRemove.setOnTouchListener(new TextHighlighterOnTouchListener(addOrRemove));
        if(userAdded == 0){
            addOrRemove.setBackgroundColor(Color.rgb(4,70,4));
            addOrRemove.setText("Add");
        }
        else{
            addOrRemove.setBackgroundColor(Color.rgb(202,8,15));
            addOrRemove.setText("Remove");
        }



        case2.addView(name);
        case2.addView(addressFrame);
        case2.addView(line);
        case2.addView(email);
        case2.addView(phone);
        case2.addView(website);

        case1.addView(case2);

        addOrRemoveLayout.addView(addOrRemove);

        rowFrame.addView(case1);
        rowFrame.addView(image);
        rowFrame.addView(addOrRemoveLayout);

        rowCase.addView(rowFrame);

        addOrRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAddNewHospital.this);
                builder.setIcon(R.drawable.signout);
                View inflate = getLayoutInflater().inflate(R.layout.dialog_sign_out,null);
                builder.setView(inflate);
                TextView optionYes = (TextView)inflate.findViewById(R.id.optionYes);
                TextView optionNo = (TextView)inflate.findViewById(R.id.optionNo);
                TextView title = (TextView)inflate.findViewById(R.id.title);
                TextView message = (TextView)inflate.findViewById(R.id.message);
                if(userAdded == 0){
                    title.setText("Add Hospital");
                    message.setText("Do you want to add Hospital Name to you hospitals?");
                }
                else{
                    title.setText("Remove Hospital");
                    message.setText("Do you want to remove Hospital Name from your hospitals?");
                }

                title.setTypeface(gothic, Typeface.BOLD);
                message.setTypeface(gothic, Typeface.NORMAL);
                optionNo.setTypeface(gothic, Typeface.NORMAL);
                optionYes.setTypeface(gothic, Typeface.NORMAL);
                optionNo.setOnTouchListener(new TextHighlighterOnTouchListener(optionNo));
                optionYes.setOnTouchListener(new TextHighlighterOnTouchListener(optionYes));
                title.setOnTouchListener(new TextHighlighterOnTouchListener(title));
                message.setOnTouchListener(new TextHighlighterOnTouchListener(message));
                final AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                optionYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                optionNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        return rowCase;

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
