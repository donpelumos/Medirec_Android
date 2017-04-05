package com.jempton.medirec;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import utilities.RoundedImage;

/**
 * Created by BALE on 05/12/2016.
 */

public class FragmentTab2 extends Fragment {
    View rootView;
    ListView notificationList;
    Typeface robotoThin, robotoLight, gothic;
    RoundedImage roundedImage1,roundedImage2,roundedImage3,roundedImage4,roundedImage5,roundedImage6,roundedImage7;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        robotoThin = Typeface.createFromAsset(getActivity().getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getActivity().getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Gothic.TTF");
        notificationList = (ListView)rootView.findViewById(R.id.notificationList);
        ArrayList<ListNotifications> list = new ArrayList<>();
        list.add(new ListNotifications("Random Notification 1",R.drawable.picture01,"Today - 22:04",1,0));
        list.add(new ListNotifications("Random Notification 2",R.drawable.picture01,"Today - 23:08",1,1));
        list.add(new ListNotifications("Random Notification 3",R.drawable.picture02,"Today - 20:58",1,2));
        list.add(new ListNotifications("Random Notification 4",R.drawable.picture03,"Yesterday - 13:04",1,3));
        list.add(new ListNotifications("Random Notification 5",R.drawable.picture04,"Yesterday - 23:08",0,0));
        list.add(new ListNotifications("Random Notification 6",R.drawable.picture05,"Yesterday - 20:35",0,1));
        list.add(new ListNotifications("Random Notification 7",R.drawable.picture06,"Nov. 23 - 12:59",0,2));
        list.add(new ListNotifications("Random Notification 8",R.drawable.picture07,"Oct. 25 - 10:32",0,3));
        list.add(new ListNotifications("Random Notification 9",R.drawable.picture02,"Oct. 2 - 11:45",0,0));
        list.add(new ListNotifications("Random Notification 10",R.drawable.picture05,"Aug. 19 - 12:23",0,1));
        list.add(new ListNotifications("Random Notification 11",R.drawable.picture06,"Jul. 27 - 07:15",0,2));
        list.add(new ListNotifications("Random Notification 12",R.drawable.picture01,"Jul. 12 - 09:19",0,3));
        list.add(new ListNotifications("Random Notification 13",R.drawable.picture03,"May 23 - 15:29",0,0));
        AdapterNotifications adp = new AdapterNotifications(getActivity().getApplicationContext(),R.layout.row_notification,list);
        notificationList.setAdapter(adp);
        notificationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog deleteNotificationDialog = new AlertDialog.Builder(getActivity())
                        .setIcon(R.drawable.bin)
                        .setTitle("Delete Notification")
                        .setMessage("Are you sure you want to delete this notification ?")
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
                TextView messageText = (TextView) deleteNotificationDialog.findViewById(android.R.id.message);
                Typeface face=Typeface.createFromAsset(getActivity().getApplication().getAssets(),"fonts/RobotoLight.ttf");
                messageText.setTypeface(face);
                return true;
            }
        });
        return rootView;
    }


}