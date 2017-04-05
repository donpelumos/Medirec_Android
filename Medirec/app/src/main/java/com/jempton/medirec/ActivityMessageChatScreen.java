package com.jempton.medirec;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import utilities.RoundRectCornerImageView;
import utilities.RoundedImage;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BALE on 21/03/2017.
 */

public class ActivityMessageChatScreen extends AppCompatActivity {
    ActionBar appBar;
    EditText messageText;
    ImageView sendButton, doctorImage, docImage, smsButton, phoneCallButton;
    TextView doctorName, docName, hospName, addOrRemoveDoctor, doctorPhoneNumber, chatButton;
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    LinearLayout messageTray;
    ScrollView scrollTray;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        robotoThin = Typeface.createFromAsset(getAssets(), "fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(), "fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getAssets(), "fonts/avenir-next-regular.ttf");
        appBar = getSupportActionBar();
        appBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.backgroundRed)));
        appBar.setDisplayShowCustomEnabled(true);
        appBar.setDisplayShowTitleEnabled(false);
        //appBar.setTitle("Messages");
        appBar.setHomeButtonEnabled(true);
        appBar.setDisplayHomeAsUpEnabled(true);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.layout_action_bar_chat_screen, null);
        //((TextView) v.findViewById(R.id.title)).setText(this.getTitle());
        LinearLayout doctorLayout = (LinearLayout)v.findViewById(R.id.doctorLayout);
        LinearLayout.LayoutParams doctorLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        doctorLayout.setLayoutParams(doctorLayoutParams);
        doctorLayout.setOnTouchListener(new LinearLayoutHighlighterOnTouchListener(doctorLayout));
        doctorName = (TextView) v.findViewById(R.id.doctorName);
        doctorName.setTypeface(gothic, Typeface.BOLD);
        doctorName.setClickable(true);
        doctorName.setOnTouchListener(new TextHighlighterOnTouchListener(doctorName));
        doctorImage = (ImageView)v.findViewById(R.id.doctorImage);
        doctorImage.setClickable(true);
        doctorImage.setOnTouchListener(new ImageHighlighterOnTouchListener(doctorImage));
        doctorImage.setImageDrawable(new RoundedImage(drawableToBitmap(getResources().getDrawable(R.drawable.picture04))));
        doctorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMessageChatScreen.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_doctor,null);
                builder.setView(view);
                docImage = (RoundRectCornerImageView)view.findViewById(R.id.docImage);
                docName = (TextView)view.findViewById(R.id.doctorName);
                hospName = (TextView)view.findViewById(R.id.hospitalName);
                addOrRemoveDoctor = (TextView)view.findViewById(R.id.addOrRemoveDoctor);
                doctorPhoneNumber = (TextView)view.findViewById(R.id.doctorPhoneNumber);
                chatButton = (TextView)view.findViewById(R.id.chatButton);
                docImage.setOnTouchListener(new ImageHighlighterOnTouchListener(docImage));
                //hospName.setOnTouchListener(new TextHighlighterOnTouchListener(hospName));
                //docName.setOnTouchListener(new TextHighlighterOnTouchListener(docName));
                addOrRemoveDoctor.setOnTouchListener(new TextHighlighterOnTouchListener(addOrRemoveDoctor));
                chatButton.setOnTouchListener(new TextHighlighterOnTouchListener(chatButton));
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
                docName.setTypeface(gothic, Typeface.BOLD);
                hospName.setTypeface(gothic, Typeface.NORMAL);
                addOrRemoveDoctor.setTypeface(gothic, Typeface.NORMAL);
                doctorPhoneNumber.setTypeface(gothic, Typeface.NORMAL);
                chatButton.setTypeface(gothic, Typeface.NORMAL);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        doctorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMessageChatScreen.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_doctor,null);
                builder.setView(view);
                docImage = (RoundRectCornerImageView)view.findViewById(R.id.docImage);
                docName = (TextView)view.findViewById(R.id.doctorName);
                hospName = (TextView)view.findViewById(R.id.hospitalName);
                addOrRemoveDoctor = (TextView)view.findViewById(R.id.addOrRemoveDoctor);
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
                //hospName.setOnTouchListener(new TextHighlighterOnTouchListener(hospName));
                //docName.setOnTouchListener(new TextHighlighterOnTouchListener(docName));
                addOrRemoveDoctor.setOnTouchListener(new TextHighlighterOnTouchListener(addOrRemoveDoctor));
                chatButton.setOnTouchListener(new TextHighlighterOnTouchListener(chatButton));
                docName.setTypeface(gothic, Typeface.BOLD);
                hospName.setTypeface(gothic, Typeface.NORMAL);
                addOrRemoveDoctor.setTypeface(gothic, Typeface.NORMAL);
                doctorPhoneNumber.setTypeface(gothic, Typeface.NORMAL);
                chatButton.setTypeface(gothic, Typeface.NORMAL);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        this.getSupportActionBar().setCustomView(v);
        setContentView(R.layout.activity_message_chat_screen);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Toolbar parent =(Toolbar) getSupportActionBar().getCustomView().getParent();
        parent.setPadding(-10,0,0,0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0,0);

        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageText = (EditText)findViewById(R.id.messageText);
        sendButton.setOnTouchListener(new ImageHighlighterOnTouchListener(sendButton));
        messageText.setTypeface(gothic, Typeface.NORMAL);
        messageTray = (LinearLayout)findViewById(R.id.messageTray);
        messageTray.addView(messageToBeReceived("Chrbrvf rngnbhfjf efkewoeepfe  rf","Yesterday | 11:23pm"));
        messageTray.addView(messageToBeSent("Takd coccccrn nunt v rvrv ","Today | 7:55pm",1));
        messageTray.addView(messageToBeSent("Pnfrfr hvhv  vrrrrvr rrr","Today | 8:04pm",1));
        messageTray.addView(messageToBeSent("Pir fiit ttv tr","Today | 8:25pm",1));
        messageTray.addView(messageToBeSent("Good day","Today | 8:33pm",1));
        messageTray.addView(messageToBeSent("djdhrrh rncrhc","Today | 8:33pm",1));
        messageTray.addView(messageToBeReceived("Testing this","Today | 8:33pm"));
        messageTray.addView(messageToBeSent("Qyye rrnr frhj hrv bnrfr fgr r vrrrvv rv  rv rf r rfbrvgrvr rbrvrvrvrv ","Today | 8:33pm",1));
        messageTray.addView(messageToBeSent("Pnfrfr hvhv  vrrrrvr rrr","Today | 8:39pm",1));
        messageTray.addView(messageToBeSent("Hfrhfr rhfrhvr rhr","Today | 8:44pm",1));
        messageTray.addView(messageToBeReceived("Udhr frbycdy eb h ","Today | 8:53pm"));
        messageTray.addView(messageToBeReceived("Eynui ioiu jjnubvvgvd cr","Today | 9:12pm"));
        messageTray.addView(messageToBeSent("Yuii jfbhvbr ufh nrh4","Today | 10:04pm",1));
        messageTray.addView(messageToBeSent("Lftfrhb fvbh  ggg  b b vfeb  h bd gc  g bs cgsc g fbgtbdv  u yeveefeydufe fdyhdvd" +
                " tfe ydvyvvhvcsvcvcvgscsc c bcy y  bv uvvgdfqpdpqdqfvbe vgdqafegreriebr fjwfhyrfrg wnfuwfbrge vjhbygeve " +
                "cwywfguw fjgeg gjgugthr gkrnjguhg8oyhpg f dqtdwyfif 32673n  fhbfbwjfu8fwbfv u8rr2 fjhfw fwufufef2f uiwhurw ve" +
                "wjc7wwfvj v q ufyfhq   fq :) :) :) :)","Today | 10:19pm",0));
        messageTray.addView(messageToBeSent("thi bvemb vjfefe :( :(","Today | 10:19pm",0));
        scrollTray= (ScrollView)findViewById(R.id.scrollTray);
        scrollTray.post(new Runnable() {
            @Override
            public void run() {
                scrollTray.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateYear = new SimpleDateFormat("yyyy");
                DateFormat dateMonth = new SimpleDateFormat("MM");
                DateFormat dateDay = new SimpleDateFormat("dd");
                DateFormat dateHour = new SimpleDateFormat("HH");
                DateFormat dateMinute = new SimpleDateFormat("mm");
                DateFormat dateSecond = new SimpleDateFormat("ss");
                Date date = new Date();
                String timeSector = "";
                String hourString=dateHour.format(date);
                if(Integer.parseInt(dateHour.format(date))>12){
                    timeSector = "pm";
                    hourString = String.valueOf(Integer.parseInt(hourString) - 12);
                }
                else{
                    timeSector = "am";
                }
                messageTray.addView(messageToBeSent(messageText.getText().toString(), "Today | "+hourString+":"+dateMinute.format(date)+" "+timeSector,0));
                messageText.setText("");
                scrollTray.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollTray.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });
    }

    public RelativeLayout messageToBeSent(String messageText, String dateTimeText, int seen){
        RelativeLayout messageFrame = new RelativeLayout(ActivityMessageChatScreen.this);
        LinearLayout.LayoutParams messageFrameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        messageFrameParams.setMargins((int)(20*getScreenDensity()),(int)(5*getScreenDensity()),0,(int)(5*getScreenDensity()));
        messageFrame.setLayoutParams(messageFrameParams);
        LinearLayout messageCase = new LinearLayout(ActivityMessageChatScreen.this);
        RelativeLayout.LayoutParams messageCaseParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        messageCaseParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        messageCase.setOrientation(LinearLayout.VERTICAL);
        messageCase.setLayoutParams(messageCaseParams);
        messageCase.setBackground(getResources().getDrawable(R.drawable.background_convex_2));
        messageCase.setPadding((int)(4*getScreenDensity()),0,(int)(4*getScreenDensity()),(int)(2*getScreenDensity()));

        TextView message = new TextView(ActivityMessageChatScreen.this);
        LinearLayout.LayoutParams messageParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        message.setLayoutParams(messageParams);
        message.setTextColor(Color.rgb(55,55,55));
        message.setTypeface(gothic, Typeface.NORMAL);
        message.setText(messageText);

        LinearLayout dateTimeFrame = new LinearLayout(ActivityMessageChatScreen.this);
        LinearLayout.LayoutParams dateTimeFrameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dateTimeFrameParams.gravity = Gravity.RIGHT;
        dateTimeFrame.setLayoutParams(dateTimeFrameParams);
        LinearLayout dateTimeCase = new LinearLayout(ActivityMessageChatScreen.this);
        RelativeLayout.LayoutParams dateTimeCaseParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        dateTimeCaseParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        dateTimeCase.setOrientation(LinearLayout.HORIZONTAL);
        dateTimeCase.setGravity(Gravity.CENTER_VERTICAL);
        dateTimeCase.setLayoutParams(dateTimeCaseParams);
        View view = new View(ActivityMessageChatScreen.this);
        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams((int)(4 * getScreenDensity()),(int)(4*getScreenDensity()));
        view.setLayoutParams(viewParams);
        viewParams.gravity = Gravity.CENTER_VERTICAL;
        if(seen == 1){
            view.setBackground(getResources().getDrawable(R.drawable.background_seen_dot));
        }
        else{
            view.setBackground(getResources().getDrawable(R.drawable.background_unseen_dot));
        }
        TextView dateTime = new TextView(ActivityMessageChatScreen.this);
        LinearLayout.LayoutParams dateTimeParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dateTime.setLayoutParams(dateTimeParams);
        dateTimeParams.setMargins((int)(5*getScreenDensity()),0,0,0);
        dateTime.setTypeface(gothic, Typeface.NORMAL);
        dateTime.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        dateTime.setText(dateTimeText);

        dateTimeCase.addView(view);
        dateTimeCase.addView(dateTime);
        dateTimeFrame.addView(dateTimeCase);

        messageCase.addView(message);
        messageCase.addView(dateTimeFrame);
        messageFrame.addView(messageCase);
        return messageFrame;
    }

    public RelativeLayout messageToBeReceived(String messageText, String dateTimeText){
        RelativeLayout messageFrame = new RelativeLayout(ActivityMessageChatScreen.this);
        LinearLayout.LayoutParams messageFrameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        messageFrameParams.setMargins(0,(int)(5*getScreenDensity()),(int)(20*getScreenDensity()),(int)(5*getScreenDensity()));
        messageFrame.setLayoutParams(messageFrameParams);
        LinearLayout messageCase = new LinearLayout(ActivityMessageChatScreen.this);
        RelativeLayout.LayoutParams messageCaseParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        messageCaseParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        messageCase.setOrientation(LinearLayout.VERTICAL);
        messageCase.setLayoutParams(messageCaseParams);
        messageCase.setBackground(getResources().getDrawable(R.drawable.background_convex));
        messageCase.setPadding((int)(4*getScreenDensity()),(int)(2*getScreenDensity()),(int)(4*getScreenDensity()),0);

        TextView message = new TextView(ActivityMessageChatScreen.this);
        LinearLayout.LayoutParams messageParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        message.setLayoutParams(messageParams);
        message.setTextColor(Color.rgb(55,55,55));
        message.setTypeface(gothic, Typeface.NORMAL);
        message.setText(messageText);

        LinearLayout dateTimeFrame = new LinearLayout(ActivityMessageChatScreen.this);
        LinearLayout.LayoutParams dateTimeFrameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dateTimeFrame.setLayoutParams(dateTimeFrameParams);
        LinearLayout dateTimeCase = new LinearLayout(ActivityMessageChatScreen.this);
        RelativeLayout.LayoutParams dateTimeCaseParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        dateTimeCaseParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        dateTimeCase.setOrientation(LinearLayout.HORIZONTAL);
        dateTimeCase.setGravity(Gravity.CENTER_VERTICAL);
        dateTimeCase.setLayoutParams(dateTimeCaseParams);

        TextView dateTime = new TextView(ActivityMessageChatScreen.this);
        LinearLayout.LayoutParams dateTimeParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dateTime.setLayoutParams(dateTimeParams);
        dateTimeParams.setMargins((int)(0*getScreenDensity()),0,0,0);
        dateTime.setTypeface(gothic, Typeface.NORMAL);
        dateTime.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        dateTime.setText(dateTimeText);

        dateTimeCase.addView(dateTime);
        dateTimeFrame.addView(dateTimeCase);

        messageCase.addView(message);
        messageCase.addView(dateTimeFrame);
        messageFrame.addView(messageCase);
        return messageFrame;
    }

    public float getScreenDensity(){
        return getResources().getDisplayMetrics().density;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat_screen, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.clearMessages:
                new android.support.v7.app.AlertDialog.Builder(ActivityMessageChatScreen.this)
                        .setIcon(R.drawable.bin)
                        .setTitle("Clear Messages")
                        .setMessage("Are you sure you want to clear all the messages?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                break;
        }
        return true;
    }
    public Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        }
        else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
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
    private class LinearLayoutHighlighterOnTouchListener implements View.OnTouchListener {
        //This
        final LinearLayout imageButton;

        public LinearLayoutHighlighterOnTouchListener(final LinearLayout imageButton) {
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
