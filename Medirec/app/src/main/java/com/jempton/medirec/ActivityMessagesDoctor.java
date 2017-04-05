package com.jempton.medirec;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BALE on 05/04/2017.
 */

public class ActivityMessagesDoctor extends AppCompatActivity {
    ActionBar appBar;
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    ListView messagesList;
    ArrayList<ListMessages> messagesArrayListst;
    AdapterMessages messagesListAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_messages_doctor);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        messagesList = (ListView)findViewById(R.id.messageList);
        messagesArrayListst = new ArrayList<>();
        messagesArrayListst.add(new ListMessages("User Full Name","Today | 10:05 am", 4));
        messagesArrayListst.add(new ListMessages("User Full Name","Today | 9:34 am", 3));
        messagesArrayListst.add(new ListMessages("User Full Name","Yesterday | 11:28 pm", 11));
        messagesArrayListst.add(new ListMessages("User Full Name","Yesterday | 7:54 pm", 8));
        messagesArrayListst.add(new ListMessages("User Full Name","21 Mar | 10:02 pm", 0));
        messagesArrayListst.add(new ListMessages("User Full Name","21 Mar | 6:43 pm", 0));
        messagesArrayListst.add(new ListMessages("User Full Name","20 Mar | 11:24 pm", 0));
        messagesArrayListst.add(new ListMessages("User Full Name","19 Mar | 10:44 am", 0));
        messagesArrayListst.add(new ListMessages("User Full Name","18 Mar | 9:15 pm", 0));
        messagesArrayListst.add(new ListMessages("User Full Name","18 Mar | 4:42 pm", 0));
        messagesArrayListst.add(new ListMessages("User Full Name","18 Mar | 2:33 am", 0));
        messagesListAdapter = new AdapterMessages(ActivityMessagesDoctor.this,R.layout.row_messages,messagesArrayListst);
        messagesList.setAdapter(messagesListAdapter);
        messagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityMessagesDoctor.this, ActivityMessageChatScreenDoctor.class);
                startActivity(intent);
            }
        });
    }
}