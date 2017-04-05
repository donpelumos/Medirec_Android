package com.jempton.medirec;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by BALE on 05/12/2016.
 */

public class FragmentTab3 extends Fragment {
    View rootView;
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    TextView subLabel1, subLabel1b, subLabel2, subLabel3;
    ListView subList1, subList2, subList3;
    AdapterAccountOptions list1Adapter, list2Adapter, list3Adapter;
    ArrayList<ListAccountOptions> list1, list2, list3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab3, container, false);
        robotoThin = Typeface.createFromAsset(getActivity().getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getActivity().getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getActivity().getAssets(),"fonts/avenir-next-regular.ttf");
        subLabel1 = (TextView)rootView.findViewById(R.id.subLabel1);
        subLabel1b = (TextView)rootView.findViewById(R.id.subLabel1b);
        subLabel2 = (TextView)rootView.findViewById(R.id.subLabel2);
        subLabel3 = (TextView)rootView.findViewById(R.id.subLabel3);
        subLabel1.setTypeface(gothic, Typeface.BOLD);
        subLabel1b.setTypeface(avenirNextRegular, Typeface.NORMAL);
        subLabel1b.setVisibility(View.GONE);
        subLabel2.setTypeface(gothic, Typeface.BOLD);
        subLabel3.setTypeface(gothic, Typeface.BOLD);
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list1.add(new ListAccountOptions("Basic Medical Data", R.drawable.hospital_letter));
        list1.add(new ListAccountOptions("Profile", R.drawable.profile));
        list1.add(new ListAccountOptions("Report an Issue", R.drawable.caution));
        list1.add(new ListAccountOptions("Reset Password", R.drawable.padlock));
        list1.add(new ListAccountOptions("Log Out", R.drawable.signout));
        list2.add(new ListAccountOptions("My Doctors", R.drawable.doctors));
        list2.add(new ListAccountOptions("My Hospitals", R.drawable.hospital));
        //list2.add(new ListAccountOptions("My Records", R.drawable.records));
        list3.add(new ListAccountOptions("About", R.drawable.about));
        list3.add(new ListAccountOptions("Privacy Policy", R.drawable.shield));
        list3.add(new ListAccountOptions("Terms of Use", R.drawable.terms));
        list1Adapter = new AdapterAccountOptions(getActivity().getApplicationContext(),R.layout.row_account_options,list1);
        list2Adapter = new AdapterAccountOptions(getActivity().getApplicationContext(),R.layout.row_medical_information_options,list2);
        list3Adapter = new AdapterAccountOptions(getActivity().getApplicationContext(),R.layout.row_more_information_options,list3);
        subList1 = (ListView)rootView.findViewById(R.id.subList1);
        subList1.setAdapter(list1Adapter);
        subList2 = (ListView)rootView.findViewById(R.id.subList2);
        subList2.setAdapter(list2Adapter);
        subList3 = (ListView)rootView.findViewById(R.id.subList3);
        subList3.setAdapter(list3Adapter);
        subList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent intent = new Intent(getActivity(),ActivityBasicMedicalInformation.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent profileIntent = new Intent(getActivity(),ActivityProfile.class);
                        startActivity(profileIntent);
                        break;
                    case 2:
                        Intent issueIntent = new Intent(getActivity(),ActivityReportIssue.class);
                        startActivity(issueIntent);
                        break;
                    case 3:
                        Intent resetIntent = new Intent(getActivity(),ActivityResetPassword.class);
                        startActivity(resetIntent);
                        break;
                    case 4:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setIcon(R.drawable.signout);
                        View inflate = getLayoutInflater(savedInstanceState).inflate(R.layout.dialog_sign_out,null);
                        builder.setView(inflate);
                        TextView optionYes = (TextView)inflate.findViewById(R.id.optionYes);
                        TextView optionNo = (TextView)inflate.findViewById(R.id.optionNo);
                        TextView title = (TextView)inflate.findViewById(R.id.title);
                        TextView message = (TextView)inflate.findViewById(R.id.message);
                        title.setTypeface(gothic, Typeface.BOLD);
                        message.setTypeface(gothic, Typeface.NORMAL);
                        optionNo.setTypeface(gothic, Typeface.NORMAL);
                        optionYes.setTypeface(gothic, Typeface.NORMAL);
                        optionNo.setOnTouchListener(new TextHighlighterOnTouchListener(optionNo));
                        optionYes.setOnTouchListener(new TextHighlighterOnTouchListener(optionYes));
                        title.setOnTouchListener(new TextHighlighterOnTouchListener(title));
                        message.setOnTouchListener(new TextHighlighterOnTouchListener(message));
                        final AlertDialog dialog = builder.create();
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        optionYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        optionNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        break;
                }
            }
        });
        subList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent myDoctorsIntent = new Intent(getActivity(), ActivityMyDoctors.class);
                        startActivity(myDoctorsIntent);
                        break;
                    case 1:
                        Intent myHospitalsIntent = new Intent(getActivity(), ActivityMyHospitals.class);
                        startActivity(myHospitalsIntent);
                        break;
                }
            }
        });
        subList3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        String url = "http://www.google.com";
                        Intent aboutIntent = new Intent(Intent.ACTION_VIEW);
                        aboutIntent.setData(Uri.parse(url));
                        startActivity(aboutIntent);
                        break;
                    case 1:
                        String url2 = "http://www.google.com";
                        Intent privacyIntent = new Intent(Intent.ACTION_VIEW);
                        privacyIntent.setData(Uri.parse(url2));
                        startActivity(privacyIntent);
                        break;
                    case 2:
                        String url3 = "http://www.google.com";
                        Intent termsIntent = new Intent(Intent.ACTION_VIEW);
                        termsIntent.setData(Uri.parse(url3));
                        startActivity(termsIntent);
                        break;
                }
            }
        });
        return rootView;
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