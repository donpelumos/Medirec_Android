package com.jempton.medirec;

import android.content.DialogInterface;
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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import utilities.RoundRectCornerImageView;

/**
 * Created by BALE on 04/04/2017.
 */

public class ActivityMyHospitals extends AppCompatActivity{
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    ImageView addNewHospital, backButton, hospImage;
    TextView hospName, hospPhoneNo, addOrRemoveHospital, hospEmail, hospWebsite, hospAddress;
    ActionBar appBar;
    ListView myHospitalsList;
    AdapterMyHospitals myHospitalsAdapter;
    ArrayList<ListMyHospitals> hospitals;
    int viewFullHospitalImage = 0;
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
        setContentView(R.layout.activity_my_hospitals);
        backButton = (ImageView)findViewById(R.id.backButton);
        addNewHospital = (ImageView)findViewById(R.id.addNewHospital);
        backButton.setOnTouchListener(new ActivityMyHospitals.ImageHighlighterOnTouchListener(backButton));
        addNewHospital.setOnTouchListener(new ActivityMyHospitals.ImageHighlighterOnTouchListener(addNewHospital));
        addNewHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewHospitalIntent = new Intent(ActivityMyHospitals.this, ActivityAddNewHospital.class);
                startActivity(addNewHospitalIntent);
            }
        });
        hospitals = new ArrayList<>();
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital1));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Addredd",R.drawable.hospital2));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital3));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital4));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital5));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital1));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital1));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital2));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital3));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital4));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital1));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital4));
        hospitals.add(new ListMyHospitals("Hospital Name","Hospital Address",R.drawable.hospital2));
        myHospitalsList = (ListView)findViewById(R.id.myHospitalsList);
        myHospitalsAdapter = new AdapterMyHospitals(ActivityMyHospitals.this, R.layout.row_my_hospitals,hospitals);
        myHospitalsList.setAdapter(myHospitalsAdapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        myHospitalsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMyHospitals.this);
                final View view = getLayoutInflater().inflate(R.layout.dialog_hospital,null);
                builder.setView(view);
                int [] hospImages = {R.drawable.hospital1,R.drawable.hospital2,R.drawable.hospital3,R.drawable.hospital4,
                        R.drawable.hospital5};
                addOrRemoveHospital = (TextView)view.findViewById(R.id.addOrRemoveDoctor);
                hospName = (TextView)view.findViewById(R.id.hospitalName);
                hospAddress = (TextView)view.findViewById(R.id.hospitalAddress);
                hospEmail = (TextView)view.findViewById(R.id.hospitalEmail);
                hospPhoneNo = (TextView)view.findViewById(R.id.hospitalPhoneNumber);
                hospWebsite = (TextView)view.findViewById(R.id.hospitalWebsite);
                hospImage = (RoundRectCornerImageView)view.findViewById(R.id.hospitalImage);
                hospEmail.setOnTouchListener(new TextHighlighterOnTouchListener(hospEmail));
                hospPhoneNo.setOnTouchListener(new TextHighlighterOnTouchListener(hospPhoneNo));
                hospWebsite.setOnTouchListener(new TextHighlighterOnTouchListener(hospWebsite));
                addOrRemoveHospital.setOnTouchListener(new TextHighlighterOnTouchListener(addOrRemoveHospital));
                hospImage.setOnTouchListener(new ImageHighlighterOnTouchListener(hospImage));
                hospImage.setImageResource(hospImages[new Random().nextInt(4)]);
                hospName.setTypeface(gothic, Typeface.BOLD);
                hospAddress.setTypeface(gothic, Typeface.NORMAL);
                hospPhoneNo.setTypeface(gothic, Typeface.NORMAL);
                hospEmail.setTypeface(gothic, Typeface.NORMAL);
                hospWebsite.setTypeface(gothic, Typeface.NORMAL);
                String udatax = hospWebsite.getText().toString();
                SpannableString contentx = new SpannableString(udatax);
                contentx.setSpan(new UnderlineSpan(), 0, udatax.length(), 0);
                hospWebsite.setText(contentx);
                hospPhoneNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        String temp = "08012345678";
                        String callText = "tel:"+temp;
                        intent.setData(Uri.parse(callText));
                        startActivity(intent);
                    }
                });
                hospWebsite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "http://www.google.com";
                        Intent aboutIntent = new Intent(Intent.ACTION_VIEW);
                        aboutIntent.setData(Uri.parse(url));
                        startActivity(aboutIntent);
                    }
                });
                hospImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(viewFullHospitalImage == 0){
                            viewFullHospitalImage = 1;
                            FrameLayout.LayoutParams hospImageParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                    FrameLayout.LayoutParams.MATCH_PARENT);
                            hospImageParams.setMargins((int)(10*getScreenDensity()),(int)(10*getScreenDensity()),(int)(10*getScreenDensity()),
                                    (int)(10*getScreenDensity()));
                            hospImage.setLayoutParams(hospImageParams);
                            hospImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }
                        else{
                            viewFullHospitalImage=0;
                            FrameLayout.LayoutParams hospImageParams = new FrameLayout.LayoutParams((int)(105*getScreenDensity()),
                                    (int)(145*getScreenDensity()));
                            hospImageParams.setMargins((int)(10*getScreenDensity()),(int)(10*getScreenDensity()),(int)(10*getScreenDensity()),
                                    (int)(10*getScreenDensity()));
                            hospImage.setLayoutParams(hospImageParams);
                            hospImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        viewFullHospitalImage=0;
                    }
                });
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
