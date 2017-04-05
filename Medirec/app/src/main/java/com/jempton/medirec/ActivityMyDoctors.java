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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import utilities.RoundRectCornerImageView;

/**
 * Created by BALE on 03/04/2017.
 */

public class ActivityMyDoctors extends AppCompatActivity {
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    ImageView addNewDoctor, backButton, docImage, smsButton, phoneCallButton;
    TextView hospName, docName, addOrRemoveDoctor, chatButton, doctorPhoneNumber;
    ActionBar appBar;
    ListView myDoctorsList;
    AdapterMyDoctors myDoctorsAdapter;
    ArrayList<ListMyDoctors> doctors;
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
        setContentView(R.layout.activity_my_doctors);
        backButton = (ImageView)findViewById(R.id.backButton);
        addNewDoctor = (ImageView)findViewById(R.id.addNewDoctor);
        backButton.setOnTouchListener(new ImageHighlighterOnTouchListener(backButton));
        addNewDoctor.setOnTouchListener(new ImageHighlighterOnTouchListener(addNewDoctor));
        addNewDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewDoctorIntent = new Intent(ActivityMyDoctors.this, ActivityAddNewDoctor.class);
                startActivity(addNewDoctorIntent);
            }
        });
        doctors = new ArrayList<>();
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture07));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture05));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture04));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture03));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture06));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture01));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture02));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture03));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture06));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture05));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture05));
        doctors.add(new ListMyDoctors("Doctor Name","Hospital Name",R.drawable.picture02));
        myDoctorsList = (ListView)findViewById(R.id.myDoctorsList);
        myDoctorsAdapter = new AdapterMyDoctors(ActivityMyDoctors.this, R.layout.row_my_doctors,doctors);
        myDoctorsList.setAdapter(myDoctorsAdapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        myDoctorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMyDoctors.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_doctor,null);
                builder.setView(view);
                docImage = (RoundRectCornerImageView)view.findViewById(R.id.docImage);
                docName = (TextView)view.findViewById(R.id.doctorName);
                hospName = (TextView)view.findViewById(R.id.hospitalName);
                addOrRemoveDoctor = (TextView)view.findViewById(R.id.addOrRemoveDoctor);
                addOrRemoveDoctor.setBackgroundColor(Color.rgb(202,8,15));
                doctorPhoneNumber = (TextView)view.findViewById(R.id.doctorPhoneNumber);
                chatButton = (TextView)view.findViewById(R.id.chatButton);
                smsButton = (ImageView)view.findViewById(R.id.smsButton);
                phoneCallButton = (ImageView)view.findViewById(R.id.phoneCallButton);
                smsButton.setOnTouchListener(new ImageHighlighterOnTouchListener(smsButton));
                smsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                        String temp = "08012345678";
                        sendIntent.setData(Uri.parse("smsto:"));
                        sendIntent.putExtra("sms_body", "");
                        sendIntent.putExtra("address"  , new String(temp));
                        sendIntent.setType("vnd.android-dir/mms-sms");
                        startActivity(sendIntent);
                    }
                });
                phoneCallButton.setOnTouchListener(new ImageHighlighterOnTouchListener(phoneCallButton));
                phoneCallButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        String temp = "08012345678";
                        String callText = "tel:"+temp;
                        intent.setData(Uri.parse(callText));
                        startActivity(intent);
                    }
                });
                docImage.setOnTouchListener(new ImageHighlighterOnTouchListener(docImage));
                addOrRemoveDoctor.setOnTouchListener(new TextHighlighterOnTouchListener(addOrRemoveDoctor));
                chatButton.setOnTouchListener(new TextHighlighterOnTouchListener(chatButton));
                docName.setTypeface(gothic, Typeface.BOLD);
                hospName.setTypeface(gothic, Typeface.NORMAL);
                addOrRemoveDoctor.setTypeface(gothic, Typeface.NORMAL);
                doctorPhoneNumber.setTypeface(gothic, Typeface.NORMAL);
                chatButton.setVisibility(View.VISIBLE);
                addOrRemoveDoctor.setText("Remove");
                chatButton.setTypeface(gothic, Typeface.NORMAL);
                chatButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent messageIntent = new Intent(ActivityMyDoctors.this, ActivityMessageChatScreen.class);
                        startActivity(messageIntent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
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
