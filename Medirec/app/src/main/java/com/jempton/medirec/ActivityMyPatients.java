package com.jempton.medirec;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by BALE on 10/04/2017.
 */

public class ActivityMyPatients extends AppCompatActivity {
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    ActionBar appBar;
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
        setContentView(R.layout.activity_my_patients);
    }
}
