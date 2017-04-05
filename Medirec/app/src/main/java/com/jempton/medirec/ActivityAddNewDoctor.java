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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import utilities.RoundRectCornerImageView;

/**
 * Created by BALE on 03/04/2017.
 */

public class ActivityAddNewDoctor extends AppCompatActivity{
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    ActionBar appBar;
    TextView hospName, docName, addOrRemoveDoctor, chatButton, doctorPhoneNumber, addDoc;
    ImageView searchButton, cancelSearchButton, backButton, docImage, smsButton, phoneCallButton;
    int searchVisible = 0;
    LinearLayout searchFrame;
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
        setContentView(R.layout.activity_add_new_doctor);
        backButton = (ImageView)findViewById(R.id.backButton);
        searchButton = (ImageView)findViewById(R.id.searchButton);
        searchBar = (EditText)findViewById(R.id.searchBar);
        searchBar.setTypeface(gothic, Typeface.NORMAL);
        cancelSearchButton = (ImageView)findViewById(R.id.cancelSearchButton);

        backButton.setOnTouchListener(new ImageHighlighterOnTouchListener(backButton));
        searchButton.setOnTouchListener(new ImageHighlighterOnTouchListener(searchButton));
        cancelSearchButton.setOnTouchListener(new ImageHighlighterOnTouchListener(cancelSearchButton));

        searchFrame = (LinearLayout)findViewById(R.id.searchFrame);
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
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        doctors = new ArrayList<>();
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture07,0));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture05,0));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture04,1));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture03,0));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture06,1));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture01,1));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture02,1));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture03,0));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture06,1));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture05,1));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture05,1));
        doctors.add(new ListAddNewDoctor("Doctor Name","Hospital Name",R.drawable.picture02,1));
        addNewDoctorList = (ListView)findViewById(R.id.addNewDoctorList);
        addNewDoctorAdapter = new AdapterAddNewDoctor(ActivityAddNewDoctor.this, R.layout.row_add_new_doctor,doctors, this);
        addNewDoctorList.setAdapter(addNewDoctorAdapter);
        addNewDoctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAddNewDoctor.this);
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
                if(doctors.get(position).toAdd == 0){
                    addOrRemoveDoctor.setText("Remove");
                    addOrRemoveDoctor.setBackgroundColor(Color.rgb(202,8,15));
                }
                else{
                    addOrRemoveDoctor.setText("Add");
                    addOrRemoveDoctor.setBackgroundColor(Color.rgb(4,70,4));
                }
                chatButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent messageIntent = new Intent(ActivityAddNewDoctor.this, ActivityMessageChatScreen.class);
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
