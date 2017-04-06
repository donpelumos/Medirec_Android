package com.jempton.medirec;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.logging.Level;

/**
 * Created by BALE on 06/04/2017.
 */

public class ActivitySecurityLevel extends AppCompatActivity{
    TextView title, body, subTitle, level1Text, level2Text, level3Text, level4Text, level1Description, level2Description, level3Description,
            level4Description, submitButton;
    CheckBox level1, level2, level3, level4;
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
        setContentView(R.layout.activity_security_level);
        title = (TextView)findViewById(R.id.title);
        body = (TextView)findViewById(R.id.body);
        subTitle = (TextView)findViewById(R.id.subTitle);
        level1 = (CheckBox)findViewById(R.id.checkbox1);
        level2 = (CheckBox)findViewById(R.id.checkbox2);
        level3 = (CheckBox)findViewById(R.id.checkbox3);
        level4 = (CheckBox)findViewById(R.id.checkbox4);
        level1Text =(TextView)findViewById(R.id.level1);
        level2Text =(TextView)findViewById(R.id.level2);
        level3Text =(TextView)findViewById(R.id.level3);
        level4Text =(TextView)findViewById(R.id.level4);
        submitButton = (TextView)findViewById(R.id.submit);
        level1Description = (TextView)findViewById(R.id.level1Description);
        level2Description = (TextView)findViewById(R.id.level2Description);
        level3Description = (TextView)findViewById(R.id.level3Description);
        level4Description = (TextView)findViewById(R.id.level4Description);
        title.setTypeface(gothic, Typeface.BOLD);
        SpannableString s1 = new SpannableString(title.getText().toString());
        s1.setSpan(new UnderlineSpan(),0,title.getText().toString().length(),0);
        title.setText(s1);
        body.setText("Security level enables user to have control over which medical doctor can access their medical records and also " +
                "control the extent to which the medical doctor can access. One of the four levels below must be selected.");
        body.setTypeface(gothic, Typeface.NORMAL);
        level1Text.setTypeface(gothic, Typeface.BOLD);
        level2Text.setTypeface(gothic, Typeface.BOLD);
        level3Text.setTypeface(gothic, Typeface.BOLD);
        level4Text.setTypeface(gothic, Typeface.BOLD);
        level1Description.setTypeface(gothic, Typeface.NORMAL);
        level2Description.setTypeface(gothic, Typeface.NORMAL);
        level3Description.setTypeface(gothic, Typeface.NORMAL);
        level4Description.setTypeface(gothic, Typeface.NORMAL);
        level1Text.setText("Level 1 - Full Access");
        level2Text.setText("Level 2 - Partial Access");
        level3Text.setText("Level 3 - Permitted Access");
        level4Text.setText("Level 4 - No Access");
        level1Description.setText("Any doctor can view your account anc access all your records");
        level2Description.setText("Any doctor can only view your medical information but not your medical records. Only your doctors can" +
                " view your medical records");
        level3Description.setText("Any doctor who is not your doctor will need your confirmation/verification before viewing your account");
        level4Description.setText("Only your doctors can view your profile");
        level1.setChecked(true);
        submitButton.setTypeface(gothic, Typeface.NORMAL);
        submitButton.setOnTouchListener(new TextHighlighterOnTouchListener(submitButton));
        level1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    level2.setChecked(false);
                    level3.setChecked(false);
                    level4.setChecked(false);
                }
            }
        });
        level2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    level1.setChecked(false);
                    level3.setChecked(false);
                    level4.setChecked(false);
                }
            }
        });
        level3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    level2.setChecked(false);
                    level1.setChecked(false);
                    level4.setChecked(false);
                }
            }
        });
        level4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    level2.setChecked(false);
                    level3.setChecked(false);
                    level1.setChecked(false);
                }
            }
        });
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
