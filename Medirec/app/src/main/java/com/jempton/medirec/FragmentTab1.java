package com.jempton.medirec;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by BALE on 05/12/2016.
 */

public class FragmentTab1 extends Fragment {
    View rootView;
    LinearLayout feedLayout;
    EditText searchBox;
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
        robotoThin = Typeface.createFromAsset(getActivity().getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getActivity().getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getActivity().getAssets(),"fonts/avenir-next-regular.ttf");
        searchBox = (EditText)rootView.findViewById(R.id.searchBox);
        searchBox.setTypeface(robotoLight);
        feedLayout = (LinearLayout)rootView.findViewById(R.id.feedLayout);
        feedLayout.addView(getNewFeedRow("Name of the surgical Record","Hospital Name","Doctor Name","Jun 29 - Present",1,1,
                new String[]{"Test Result 1--1","Test Result 2--1","Test Result 3--2"}));
        feedLayout.addView(getNewFeedRow("Name of the diagnosis and treatment record ","Hospital Name","Doctor Name","May 25 - Jun 2",2,2,
                new String[]{"Test Result 1--1","Test Result 2--2","Test Result 3--2","Test Result 4--4","Test Result 5--3"}));
        feedLayout.addView(getNewFeedRow("Name of the diagnosis and treatment record ","Hospital Name","Doctor Name","May 2 - May 12",2,2,
                new String[]{"Test Result 1--1","Test Result 2--3",}));
        feedLayout.addView(getNewFeedRow("Name of the diagnosis and treatment record ","Hospital Name","Doctor Name","Apr 15 - Apr 28",2,2,
                new String[]{"Test Result 1--1","Test Result 2--2","Test Result 3--2"}));
        feedLayout.addView(getNewFeedRow("Name of the diagnosis and treatment record ","Hospital Name","Doctor Name","Mar 29 - Apr 12",2,2,
                new String[]{"Test Result 1--1"}));
        feedLayout.addView(getNewFeedRow("Name of the surgical Record ","Hospital Name","Doctor Name","Mar 3 - Mar 7",1,2,
                new String[]{"Test Result 1--2"}));
        feedLayout.addView(getNewFeedRow("Name of the diagnosis and treatment record ","Hospital Name","Doctor Name","Feb 25 - Feb 28",2,2,
                new String[]{"Test Result 1--1","Test Result 2--1","Test Result 3--2","Test Result 4--3","Test Result 5--4","Test Result 6--4"}));
        return rootView;
    }
    public FrameLayout getNewFeedRow(String recordName, String hospitalName, String doctorName, String dateName, int type, int status,
                                      String[] files){
        FrameLayout recordRowFrame = new FrameLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams recordRowFrameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        recordRowFrameParams.setMargins((int)(3*getScreenDensity()),(int)(0*getScreenDensity()),(int)(3*getScreenDensity()),
                (int)(10*getScreenDensity()));

        recordRowFrame.setLayoutParams(recordRowFrameParams);

        LinearLayout recordRow = new LinearLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams recordRowParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        recordRow.setLayoutParams(recordRowParams);
        recordRow.setOrientation(LinearLayout.HORIZONTAL);
        recordRow.setGravity(Gravity.CENTER_VERTICAL);
        if(type == 1) {
            recordRow.setBackground(getResources().getDrawable(R.drawable.background_record_row));
        }
        else if(type == 2){
            recordRow.setBackground(getResources().getDrawable(R.drawable.background_record_row_two));
        }
        View box = new View(getActivity().getApplicationContext());
        LinearLayout.LayoutParams boxParams = new LinearLayout.LayoutParams(14*(int)getScreenDensity(),14*(int)getScreenDensity());
        boxParams.setMargins((int)(10*getScreenDensity()),(int)(10*getScreenDensity()),(int)(10*getScreenDensity()),
                (int)(10*getScreenDensity()));
        box.setLayoutParams(boxParams);
        if(type == 1){
            box.setBackgroundColor(getResources().getColor(R.color.surgicalRecord));
        }
        else if(type == 2){
            box.setBackgroundColor(getResources().getColor(R.color.diagnosisRecord));
        }
        LinearLayout rowDetails = new LinearLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams rowDetailsParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rowDetailsParams.setMargins(0,(int)(10*getScreenDensity()),(int)(10*getScreenDensity()),(int)(10*getScreenDensity()));
        rowDetails.setLayoutParams(rowDetailsParams);
        rowDetails.setOrientation(LinearLayout.VERTICAL);

        //FIRST ROW
        LinearLayout recordNameRow = new LinearLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams recordNameRowParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        recordNameRow.setLayoutParams(recordNameRowParams);
        TextView recordNameText = new TextView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams recordNameTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        recordNameTextParams.setMargins(0,(int)(0*getScreenDensity()),0,(int)(0*getScreenDensity()));
        recordNameText.setLayoutParams(recordNameTextParams);
        recordNameText.setText(recordName);
        recordNameText.setTextColor(Color.BLACK);
        recordNameText.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        recordNameText.setTypeface(gothic, Typeface.BOLD);


        //SECOND ROW
        LinearLayout hospitalNameRow = new LinearLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams hospitalNameRowParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        hospitalNameRow.setLayoutParams(recordNameRowParams);
        hospitalNameRow.setGravity(Gravity.CENTER_VERTICAL);
        ImageView hospitalNameIcon = new ImageView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams hospitalNameIconParams = new LinearLayout.LayoutParams((int)(14*getScreenDensity()),
                (int)(14*getScreenDensity()));
        hospitalNameIconParams.setMargins(0,0,((int)(5*getScreenDensity())),0);
        hospitalNameIcon.setLayoutParams(hospitalNameIconParams);
        hospitalNameIcon.setImageResource(R.drawable.hospital_letter);
        TextView hospitalNameText = new TextView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams hospitalNameTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        hospitalNameTextParams.setMargins(0,(int)(0*getScreenDensity()),0,(int)(0*getScreenDensity()));
        hospitalNameText.setLayoutParams(hospitalNameTextParams);
        hospitalNameText.setText(hospitalName + " - " + doctorName);
        hospitalNameText.setTextColor(Color.rgb(0,0,0));
        hospitalNameText.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
        hospitalNameText.setTypeface(robotoThin);
        hospitalNameRow.addView(hospitalNameIcon);
        hospitalNameRow.addView(hospitalNameText);


        //THIRD ROW
        LinearLayout dateTimeRow = new LinearLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams dateTimeRowParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dateTimeRow.setLayoutParams(dateTimeRowParams);
        dateTimeRow.setGravity(Gravity.CENTER_VERTICAL);
        ImageView dateTimeIcon = new ImageView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams dateTimeIconParams = new LinearLayout.LayoutParams((int)(16*getScreenDensity()),
                (int)(16*getScreenDensity()));
        dateTimeIconParams.setMargins(0,(int)(2*getScreenDensity()),((int)(5*getScreenDensity())),(int)(2*getScreenDensity()));
        dateTimeIcon.setLayoutParams(dateTimeIconParams);
        dateTimeIcon.setImageResource(R.drawable.calendar);
        TextView dateTimeText = new TextView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams dateTimeTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        dateTimeTextParams.setMargins(0,(int)(0*getScreenDensity()),0,(int)(0*getScreenDensity()));
        dateTimeText.setLayoutParams(dateTimeTextParams);
        dateTimeText.setText(dateName);
        dateTimeText.setTextColor(Color.rgb(0,0,0));
        dateTimeText.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        dateTimeText.setTypeface(robotoThin);
        dateTimeRow.addView(dateTimeIcon);
        dateTimeRow.addView(dateTimeText);


        TextView fileLabel = new TextView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams fileLabelParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        fileLabel.setLayoutParams(fileLabelParams);
        fileLabelParams.setMargins(0,(int)(15*getScreenDensity()),0,0);
        fileLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        fileLabel.setTextColor(Color.rgb(2,2,2));
        fileLabel.setText("Test Results");
        fileLabel.setTypeface(gothic);

        HorizontalScrollView testResultsScroll = new HorizontalScrollView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams testResultsScrollParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        testResultsScrollParams.setMargins(0,(int)(10*getScreenDensity()),0,0);
        testResultsScroll.setLayoutParams(testResultsScrollParams);

        LinearLayout testResults = new LinearLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams testResultsParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        testResults.setOrientation(LinearLayout.HORIZONTAL);
        testResults.setLayoutParams(testResultsParams);

        for (String key : files) {
            final int fileType = Integer.parseInt(key.split("--")[1]);
            ImageView fileIcon = new ImageView(getActivity().getApplicationContext());
            int iconResource = 0;
            switch (fileType){
                case 1:
                    iconResource = R.drawable.pdf_icon;
                    break;
                case 2:
                    iconResource = R.drawable.jpeg_icon;
                    break;
                case 3 :
                    iconResource = R.drawable.png_icon;
                    break;
                case 4:
                    iconResource = R.drawable.word_icon;
                    break;
            }
            fileIcon.setImageResource(iconResource);
            fileIcon.setClickable(true);
            fileIcon.setOnTouchListener(new ImageHighlighterOnTouchListener(fileIcon));
            fileIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(fileType){
                        case 2:
                            String url = "https://drive.google.com/open?id=0Bx4xmvlX9F5MVDd1dk5kWWNPVnM";
                            Intent i = new Intent(Intent.ACTION_VIEW );
                            i.setData(Uri.parse(url));
                            startActivity(i);
                            break;
                        case 1:
                            String url2 = "https://drive.google.com/open?id=0Bx4xmvlX9F5MVDd1dk5kWWNPVnM";
                            Intent i2 = new Intent(Intent.ACTION_VIEW);
                            i2.setData(Uri.parse(url2));
                            startActivity(i2);
                            break;
                        case 3:
                            String url3 = "https://drive.google.com/open?id=0Bx4xmvlX9F5MVDd1dk5kWWNPVnM";
                            Intent i3 = new Intent(Intent.ACTION_VIEW);
                            i3.setData(Uri.parse(url3));
                            startActivity(i3);
                            break;
                        case 4:
                            String url4 = "https://drive.google.com/open?id=0Bx4xmvlX9F5MVDd1dk5kWWNPVnM";
                            Intent i4 = new Intent(Intent.ACTION_VIEW);
                            i4.setData(Uri.parse(url4));
                            startActivity(i4);
                            break;
                    }
                }
            });
            LinearLayout fileCase = new LinearLayout(getActivity().getApplicationContext());
            LinearLayout.LayoutParams fileCaseParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            fileCaseParams.setMargins((int)(5*getScreenDensity()),0,(int)(5*getScreenDensity()),0);
            fileCase.setLayoutParams(fileCaseParams);
            fileCase.setGravity(Gravity.CENTER_HORIZONTAL);
            fileCase.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams fileIconParams = new LinearLayout.LayoutParams((int)(45*getScreenDensity()),(int)(45*getScreenDensity()));
            fileIcon.setLayoutParams(fileIconParams);
            TextView fileName = new TextView(getActivity().getApplicationContext());
            LinearLayout.LayoutParams fileNameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            fileNameParams.setMargins(0, (int)(getScreenDensity() * 5),0,0);
            fileName.setLayoutParams(fileNameParams);
            fileName.setText(key.split("--")[0]);
            fileName.setTypeface(avenirNextRegular);
            fileName.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            fileName.setTextColor(Color.rgb(2,2,2));
            fileCase.addView(fileIcon);
            fileCase.addView(fileName);

            testResults.addView(fileCase);
        }

        testResultsScroll.addView(testResults);

        LinearLayout viewMore = new LinearLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams viewMoreParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        viewMore.setLayoutParams(viewMoreParams);
        viewMore.setGravity(Gravity.RIGHT);

        TextView viewMoreButton = new TextView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams viewMoreButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        viewMoreButtonParams.setMargins(0,0,0,0);
        viewMoreButton.setLayoutParams(viewMoreButtonParams);
        viewMoreButton.setText("View More");
        viewMoreButton.setTextColor(Color.WHITE);
        viewMoreButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        viewMoreButton.setClickable(true);
        viewMoreButton.setTypeface(gothic);
        viewMoreButton.setPadding((int)(4*getScreenDensity()),(int)(4*getScreenDensity()),(int)(4*getScreenDensity()),(int)(4*getScreenDensity()));
        viewMoreButton.setBackgroundColor(Color.parseColor("#ca080f"));
        viewMoreButton.setOnTouchListener(new TextHighlighterOnTouchListener(viewMoreButton));
        viewMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ActivityMedicalRecord.class);
                startActivity(intent);
            }
        });

        LinearLayout statusFrame = new LinearLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams statusFrameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        statusFrame.setLayoutParams(statusFrameParams);
        statusFrame.setGravity(Gravity.RIGHT);

        LinearLayout statusLayout = new LinearLayout(getActivity().getApplicationContext());
        LinearLayout.LayoutParams statusLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        statusLayoutParams.setMargins((int)(0*getScreenDensity()),0,0,0);
        statusLayout.setLayoutParams(statusLayoutParams);

        ImageView statusImage = new ImageView(getActivity().getApplicationContext());
        LinearLayout.LayoutParams statusImageParams = new LinearLayout.LayoutParams((int)(20*getScreenDensity()),(int)(20*getScreenDensity()));
        statusImageParams.setMargins(0,0, (int)(10*getScreenDensity()) ,0);
        statusImage.setLayoutParams(statusImageParams);
        statusImageParams.gravity = Gravity.CENTER_VERTICAL;
        if(status == 1) {
            statusImage.setImageResource(R.drawable.ongoing);
        }
        else if(status == 2){
            statusImage.setImageResource(R.drawable.completed);
        }

        recordRow.addView(box);
        rowDetails.addView(recordNameText);
        rowDetails.addView(hospitalNameRow);
        rowDetails.addView(dateTimeRow);
        rowDetails.addView(fileLabel);
        rowDetails.addView(testResultsScroll);
        viewMore.addView(viewMoreButton);
        rowDetails.addView(viewMore);

        recordRow.addView(rowDetails);

        statusLayout.addView(statusImage);
        statusFrame.addView(statusLayout);

        recordRowFrame.addView(recordRow);
        recordRowFrame.addView(statusFrame);
        return recordRowFrame;
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
