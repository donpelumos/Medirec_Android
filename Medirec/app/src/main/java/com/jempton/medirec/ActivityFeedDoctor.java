package com.jempton.medirec;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by BALE on 05/04/2017.
 */

public class ActivityFeedDoctor extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView messageButton;
    LinearLayout messageButtonFrame, messageButtonBackground;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.setContentView(R.layout.activity_feed_doctor);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.search_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.notification_));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.settings));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        messageButtonBackground = (LinearLayout)findViewById(R.id.messageButtonBackground);
        messageButtonFrame = (LinearLayout)findViewById(R.id.messageButtonFrame);

        //tabLayout.setBackgroundColor(getResources().getColor(R.color.backgroundRed));
        viewPager = (ViewPager)findViewById(R.id.pager);
        PagerDoctor adapter = new PagerDoctor(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);
        tabLayout.getTabAt(0).getIcon().setAlpha(255);
        tabLayout.getTabAt(1).getIcon().setAlpha(195);
        tabLayout.getTabAt(2).getIcon().setAlpha(195);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setScrollPosition(position,0,true);
                tabLayout.setSelected(true);

                if(position == 0){
                    tabLayout.getTabAt(position).getIcon().setAlpha(255);
                    tabLayout.getTabAt(1).getIcon().setAlpha(185);
                    tabLayout.getTabAt(2).getIcon().setAlpha(185);
                    messageButtonFrame.setVisibility(View.VISIBLE);
                }
                else if(position == 1){
                    tabLayout.getTabAt(position).getIcon().setAlpha(255);
                    tabLayout.getTabAt(0).getIcon().setAlpha(185);
                    tabLayout.getTabAt(2).getIcon().setAlpha(185);
                    messageButtonFrame.setVisibility(View.VISIBLE);
                }
                else if(position == 2){
                    tabLayout.getTabAt(position).getIcon().setAlpha(255);
                    tabLayout.getTabAt(1).getIcon().setAlpha(185);
                    tabLayout.getTabAt(0).getIcon().setAlpha(185);
                    messageButtonFrame.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        messageButton = (ImageView)findViewById(R.id.messageButton);
        messageButtonBackground.setOnTouchListener(new LinearLayoutHighlighterOnTouchListener(messageButtonBackground));
        messageButton.setOnTouchListener(new ImageHighlighterOnTouchListener(messageButton));
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFeedDoctor.this, ActivityMessagesDoctor.class);
                startActivity(intent);
            }
        });
        messageButtonBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFeedDoctor.this, ActivityMessagesDoctor.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
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
}