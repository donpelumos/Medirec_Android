package com.jempton.medirec;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by BALE on 16/03/2017.
 */

public class ActivityMedicalRecord extends AppCompatActivity {
    TextView hospitalName, doctorName, dateTime, summary, testResult;
    EditText summaryText;
    View underline, underline2;
    ImageView backButton;
    ListView testResultsList;
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    AdapterTestResults testResultsAdapter;
    ArrayList<ListTestResults> resultsArrayList;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.setContentView(R.layout.activity_medical_record);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        robotoThin = Typeface.createFromAsset(getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getAssets(),"fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getAssets(),"fonts/avenir-next-regular.ttf");
        hospitalName = (TextView)findViewById(R.id.hospitalName);
        doctorName = (TextView)findViewById(R.id.doctorName);
        dateTime = (TextView)findViewById(R.id.dateTime);
        summary = (TextView)findViewById(R.id.summary);
        testResult = (TextView)findViewById(R.id.testResults);
        summaryText = (EditText)findViewById(R.id.summaryText);
        testResultsList = (ListView)findViewById(R.id.testResultsList);
        summaryText.setTypeface(gothic, Typeface.NORMAL);
        summaryText.setText("I had a pretty great childhood growing up in the Philadelphia region. My cousins and I were all very close like brothers and sisters. " +
                "Our collective parents had all of us when they were quite young and still needed each other for sibling support. There were 7 of us cousins born between 1977 and 1983. None of our individual families were very well off and we were never able to go away on big long vacations together so we went on hiking/camping/fishing trips nearly every weekend of our lives from birth until around the last born entered high school. Our parents would gas up, pack picnic food and our dogs and just drive in any given direction towards a green part of a map until their tanks were near empty. ");
        underline = findViewById(R.id.underline);
        underline2 = findViewById(R.id.underline2);
        summary.measure(0,0);
        testResult.measure(0,0);
        LinearLayout.LayoutParams underlineParams = new LinearLayout.LayoutParams(summary.getMeasuredWidth()+5,(
                int)(1*getScreenDensity()));
        underline.setLayoutParams(underlineParams);
        LinearLayout.LayoutParams underline2Params = new LinearLayout.LayoutParams(testResult.getMeasuredWidth()+5,
                (int)(1*getScreenDensity()));
        underline2.setLayoutParams(underline2Params);
        hospitalName.setTypeface(gothic, Typeface.BOLD);
        doctorName.setTypeface(gothic, Typeface.NORMAL);
        dateTime.setTypeface(gothic, Typeface.NORMAL);
        summary.setTypeface(gothic, Typeface.NORMAL);
        testResult.setTypeface(gothic, Typeface.NORMAL);
        resultsArrayList = new ArrayList<>();
        resultsArrayList.add(new ListTestResults("Malaria Test Result",0));
        resultsArrayList.add(new ListTestResults("Urine Test Result",2));
        resultsArrayList.add(new ListTestResults("X-Ray Test Result",3));
        testResultsAdapter = new AdapterTestResults(ActivityMedicalRecord.this,R.layout.row_test_results,resultsArrayList);
        testResultsList.setAdapter(testResultsAdapter);

        testResultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = "https://drive.google.com/open?id=0Bx4xmvlX9F5MVDd1dk5kWWNPVnM";
                Intent i = new Intent(Intent.ACTION_VIEW );
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        backButton = (ImageView)findViewById(R.id.backButton);
        backButton.setOnTouchListener(new ImageHighlighterOnTouchListener(backButton));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
}
