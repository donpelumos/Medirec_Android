package com.jempton.medirec;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by BALE on 07/04/2017.
 */

public class ActivityPatientProfile extends AppCompatActivity {
    int accessType;
    TextView title, patientFullname, patientPhoneNumber, nextOfKin, tab1, tab2,  tab3, bloodGroup, genotype, height, weight,
            confirmPassword, dialogTitle, dialogMessage, addAllergy, editRecord, addChildhoodAilment, addFamilyHistory,
            addMedicalCondition, topicAllergies, topicChildhoodAIlment, topicFamilyHistory, topicMedicalCondition;
    ImageView backButton;
    EditText password,recordBody,recordTitle;
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    View subTab1, subTab2, subTab3;
    int currentTab = 1; int editAdvancedRredordStatus = 0;
    LinearLayout basicLayout, advancedLayout, historyLayout;
    ListView allergiesList, childhoodAilmentsList, familyHistoryList, medicalCOnditionsList;
    ArrayList<ListAllergy> allergies, childhoodAilments, familyHistory, medicalConditions;
    AdapterAllergy adapterAllergy;
    AdapterChildhoodAilments adapterChildhoodAilments;
    AdapterFamilyHistory adapterFamilyHistory;
    AdapterMedicalCondition adapterMedicalCondition;
    ArrayAdapter<String> heightOptions1Adapter, heightOptions2Adapter, weightOptions1Adapter, weightOptions2Adapter,
            genotypeOptionsAdapter, bloodGroupOptionsAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_patient_profile);
        accessType = getIntent().getIntExtra("accessType",0);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        title = (TextView)findViewById(R.id.title);
        title.setTypeface(gothic, Typeface.BOLD);
        robotoThin = Typeface.createFromAsset(getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(),"fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getAssets(),"fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getAssets(),"fonts/avenir-next-regular.ttf");
        patientFullname = (TextView)findViewById(R.id.patientFullname);
        patientPhoneNumber = (TextView)findViewById(R.id.patientPhoneNumber);
        nextOfKin = (TextView)findViewById(R.id.nextOfKin);
        backButton = (ImageView)findViewById(R.id.backButton);


        patientFullname.setTypeface(gothic, Typeface.BOLD);
        patientPhoneNumber.setTypeface(gothic, Typeface.NORMAL);
        nextOfKin.setTypeface(gothic, Typeface.NORMAL);
        SpannableString s1 = new SpannableString(nextOfKin.getText().toString());
        s1.setSpan(new UnderlineSpan(),0,nextOfKin.getText().toString().length(),0);
        nextOfKin.setText(s1);
        backButton.setOnTouchListener(new ImageHighlighterOnTouchListener(backButton));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        nextOfKin.setOnTouchListener(new TextHighlighterOnTouchListener(nextOfKin));
        patientPhoneNumber.setOnTouchListener(new TextHighlighterOnTouchListener(patientPhoneNumber));
        patientPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "08012345678";
                String callText = "tel:"+temp;
                intent.setData(Uri.parse(callText));
                startActivity(intent);
            }
        });
        basicLayout = (LinearLayout)findViewById(R.id.basicLayout);
        advancedLayout = (LinearLayout)findViewById(R.id.advancedLayout);
        historyLayout = (LinearLayout)findViewById(R.id.historyLayout);
        advancedLayout.setVisibility(View.GONE);
        historyLayout.setVisibility(View.GONE);
        topicAllergies = (TextView)findViewById(R.id.topicAllergies);
        topicChildhoodAIlment = (TextView)findViewById(R.id.topicChildhoodAilments);
        topicFamilyHistory = (TextView)findViewById(R.id.topicFamilyHistory);
        topicMedicalCondition = (TextView)findViewById(R.id.topicMedicalCondition);
        topicAllergies.setTypeface(gothic, Typeface.BOLD);
        topicChildhoodAIlment.setTypeface(gothic, Typeface.BOLD);
        topicFamilyHistory.setTypeface(gothic, Typeface.BOLD);
        topicMedicalCondition.setTypeface(gothic, Typeface.BOLD);
        backButton.setOnTouchListener(new ImageHighlighterOnTouchListener(backButton));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tab1 = (TextView)findViewById(R.id.tab1);
        tab2 = (TextView)findViewById(R.id.tab2);
        tab3 = (TextView)findViewById(R.id.tab3);
        subTab1 = findViewById(R.id.tabLine1);
        subTab2 = findViewById(R.id.tabLine2);
        subTab3 = findViewById(R.id.tabLine3);
        tab1.setOnTouchListener(new TextHighlighterOnTouchListener(tab1));
        tab2.setOnTouchListener(new TextHighlighterOnTouchListener(tab2));
        tab3.setOnTouchListener(new TextHighlighterOnTouchListener(tab3));
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTab == 3 || currentTab == 2){
                    currentTab = 1;
                    if(accessType == 0){
                        tab3.setBackgroundColor(Color.rgb(221,221,221));
                        tab3.setTextColor(Color.rgb(170,170,170));
                        historyLayout.setVisibility(View.GONE);
                        subTab3.setVisibility(View.INVISIBLE);
                    }
                    tab1.setBackgroundColor(Color.rgb(255,255,255));
                    tab1.setTextColor(Color.rgb(119,119,119));
                    tab2.setBackgroundColor(Color.rgb(221,221,221));
                    tab2.setTextColor(Color.rgb(170,170,170));
                    basicLayout.setVisibility(View.VISIBLE);
                    advancedLayout.setVisibility(View.GONE);
                    subTab2.setVisibility(View.INVISIBLE);
                    subTab1.setVisibility(View.VISIBLE);

                }
                else{
                }
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTab == 1 || currentTab == 3){
                    currentTab = 2;
                    if(accessType==0){
                        tab3.setBackgroundColor(Color.rgb(221,221,221));
                        tab3.setTextColor(Color.rgb(170,170,170));
                        historyLayout.setVisibility(View.GONE);
                        subTab3.setVisibility(View.INVISIBLE);
                    }
                    tab2.setBackgroundColor(Color.rgb(255,255,255));
                    tab2.setTextColor(Color.rgb(119,119,119));
                    tab1.setBackgroundColor(Color.rgb(221,221,221));
                    tab1.setTextColor(Color.rgb(170,170,170));
                    basicLayout.setVisibility(View.GONE);
                    advancedLayout.setVisibility(View.VISIBLE);
                    subTab2.setVisibility(View.VISIBLE);
                    subTab1.setVisibility(View.INVISIBLE);

                }
                else{
                }
            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTab == 1 || currentTab == 2){
                    currentTab = 3;
                    tab3.setBackgroundColor(Color.rgb(255,255,255));
                    tab3.setTextColor(Color.rgb(119,119,119));
                    tab2.setBackgroundColor(Color.rgb(221,221,221));
                    tab2.setTextColor(Color.rgb(170,170,170));
                    tab1.setBackgroundColor(Color.rgb(221,221,221));
                    tab1.setTextColor(Color.rgb(170,170,170));
                    basicLayout.setVisibility(View.GONE);
                    advancedLayout.setVisibility(View.GONE);
                    historyLayout.setVisibility(View.VISIBLE);
                    subTab3.setVisibility(View.VISIBLE);
                    subTab2.setVisibility(View.INVISIBLE);
                    subTab1.setVisibility(View.INVISIBLE);

                }
                else{
                }
            }
        });
        if(accessType == 1){
            tab3.setVisibility(View.GONE);
            subTab3.setVisibility(View.GONE);

        }

        bloodGroup = (TextView)findViewById(R.id.bloodGroup);
        genotype = (TextView)findViewById(R.id.genotype);
        height = (TextView)findViewById(R.id.height);
        weight = (TextView)findViewById(R.id.weight);
        bloodGroup.setTypeface(gothic, Typeface.NORMAL);
        genotype.setTypeface(gothic, Typeface.NORMAL);
        height.setTypeface(gothic, Typeface.NORMAL);
        weight.setTypeface(gothic, Typeface.NORMAL);


        allergiesList = (ListView)findViewById(R.id.allergiesList);
        allergies = new ArrayList<>();
        allergies.add(new ListAllergy("Allergy Name"));  allergies.add(new ListAllergy("Allergy Name"));
        allergies.add(new ListAllergy("Allergy Name"));  allergies.add(new ListAllergy("Allergy Name"));
        adapterAllergy = new AdapterAllergy(ActivityPatientProfile.this,R.layout.row_allergies, allergies, accessType);
        allergiesList.setAdapter(adapterAllergy);
        LinearLayout.LayoutParams allergiesListParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)((52*allergies.size()*getScreenDensity()-(int)(2*getScreenDensity()))));
        allergiesList.setLayoutParams(allergiesListParams);
        addAllergy = (TextView)findViewById(R.id.addAllergy);
        if(accessType == 1){
            addAllergy.setVisibility(View.GONE);
        }
        addAllergy.setOnTouchListener(new TextHighlighterOnTouchListener(addAllergy));
        addAllergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPatientProfile.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_advanced_record,null);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                recordTitle = (EditText) view.findViewById(R.id.title);
                recordTitle.setEnabled(true);
                recordTitle.setText("");
                recordTitle.setHint("Allergy Name");
                recordBody = (EditText)view.findViewById(R.id.message);
                recordBody.setText("");
                recordBody.setHint("Description here . . .");
                editRecord = (TextView)view.findViewById(R.id.editButton);
                recordBody.setEnabled(true);
                recordTitle.setTypeface(gothic, Typeface.BOLD);
                recordBody.setTypeface(gothic, Typeface.NORMAL);
                editRecord.setTypeface(gothic, Typeface.NORMAL);
                editRecord.setText("Add");
                editRecord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        allergiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog = recordDialog("Allergy");
                dialog.show();
            }
        });


        childhoodAilmentsList = (ListView)findViewById(R.id.childhoodAilmentsList);
        childhoodAilments = new ArrayList<>();
        childhoodAilments.add(new ListAllergy("Childhood Ailment Name"));
        childhoodAilments.add(new ListAllergy("Childhood Ailment Name"));
        childhoodAilments.add(new ListAllergy("Childhood Ailment Name"));
        childhoodAilments.add(new ListAllergy("Childhood Ailment Name"));
        adapterChildhoodAilments = new AdapterChildhoodAilments(ActivityPatientProfile.this,R.layout.row_allergies,
                childhoodAilments, accessType);
        childhoodAilmentsList.setAdapter(adapterChildhoodAilments);
        LinearLayout.LayoutParams childhoodAilmentsParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)((52*childhoodAilments.size()*getScreenDensity()-(int)(2*getScreenDensity()))));
        childhoodAilmentsList.setLayoutParams(childhoodAilmentsParams);
        addChildhoodAilment = (TextView)findViewById(R.id.addChildhoodAilment);
        if(accessType == 1){
            addChildhoodAilment.setVisibility(View.GONE);
        }
        addChildhoodAilment.setOnTouchListener(new TextHighlighterOnTouchListener(addChildhoodAilment));
        addChildhoodAilment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPatientProfile.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_advanced_record,null);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                recordTitle = (EditText) view.findViewById(R.id.title);
                recordTitle.setEnabled(true);
                recordTitle.setText("");
                recordTitle.setHint("Childhood Ailment Name");
                recordBody = (EditText)view.findViewById(R.id.message);
                recordBody.setText("");
                recordBody.setHint("Description here . . .");
                editRecord = (TextView)view.findViewById(R.id.editButton);
                recordBody.setEnabled(true);
                recordTitle.setTypeface(gothic, Typeface.BOLD);
                recordBody.setTypeface(gothic, Typeface.NORMAL);
                editRecord.setTypeface(gothic, Typeface.NORMAL);
                editRecord.setText("Add");
                editRecord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        childhoodAilmentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog = recordDialog("Childhood Ailment");
                dialog.show();
            }
        });


        familyHistoryList = (ListView)findViewById(R.id.familyHistoryList);
        familyHistory = new ArrayList<>();
        familyHistory.add(new ListAllergy("Family History Name"));
        familyHistory.add(new ListAllergy("Family History Name"));
        familyHistory.add(new ListAllergy("Family History Name"));
        familyHistory.add(new ListAllergy("Family History Name"));
        familyHistory.add(new ListAllergy("Family History Name"));
        adapterFamilyHistory = new AdapterFamilyHistory(ActivityPatientProfile.this,R.layout.row_allergies,
                familyHistory, accessType);
        familyHistoryList.setAdapter(adapterFamilyHistory);
        LinearLayout.LayoutParams familyHistoryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)((52*familyHistory.size()*getScreenDensity()-(int)(2*getScreenDensity()))));
        familyHistoryList.setLayoutParams(familyHistoryParams);
        addFamilyHistory = (TextView)findViewById(R.id.addFamilyHistory);
        if(accessType == 1){
            addFamilyHistory.setVisibility(View.GONE);
        }
        addFamilyHistory.setOnTouchListener(new TextHighlighterOnTouchListener(addFamilyHistory));
        addFamilyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPatientProfile.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_advanced_record,null);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                recordTitle = (EditText) view.findViewById(R.id.title);
                recordTitle.setEnabled(true);
                recordTitle.setText("");
                recordTitle.setHint("Family History Name");
                recordBody = (EditText)view.findViewById(R.id.message);
                recordBody.setText("");
                recordBody.setHint("Description here . . .");
                editRecord = (TextView)view.findViewById(R.id.editButton);
                recordBody.setEnabled(true);
                recordTitle.setTypeface(gothic, Typeface.BOLD);
                recordBody.setTypeface(gothic, Typeface.NORMAL);
                editRecord.setTypeface(gothic, Typeface.NORMAL);
                editRecord.setText("Add");
                editRecord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        familyHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog = recordDialog("Family History");
                dialog.show();
            }
        });


        medicalCOnditionsList = (ListView)findViewById(R.id.medicalConditionList);
        medicalConditions = new ArrayList<>();
        medicalConditions.add(new ListAllergy("Medical Condition Name"));
        medicalConditions.add(new ListAllergy("Medical Condition Name"));
        medicalConditions.add(new ListAllergy("Medical Condition Name"));
        adapterMedicalCondition = new AdapterMedicalCondition(ActivityPatientProfile.this,R.layout.row_allergies,
                medicalConditions, accessType);
        medicalCOnditionsList.setAdapter(adapterMedicalCondition);
        LinearLayout.LayoutParams medicalConditionParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)((52*medicalConditions.size()*getScreenDensity()-(int)(2*getScreenDensity()))));
        medicalCOnditionsList.setLayoutParams(medicalConditionParams);
        addMedicalCondition = (TextView)findViewById(R.id.addMedicalCOndition);
        if(accessType == 1){
            addMedicalCondition.setVisibility(View.GONE);
        }
        addMedicalCondition.setOnTouchListener(new TextHighlighterOnTouchListener(addMedicalCondition));
        addMedicalCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPatientProfile.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_advanced_record,null);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                recordTitle = (EditText) view.findViewById(R.id.title);
                recordTitle.setEnabled(true);
                recordTitle.setText("");
                recordTitle.setHint("Medical Condition Name");
                recordBody = (EditText)view.findViewById(R.id.message);
                recordBody.setText("");
                recordBody.setHint("Description here . . .");
                editRecord = (TextView)view.findViewById(R.id.editButton);
                recordBody.setEnabled(true);
                recordTitle.setTypeface(gothic, Typeface.BOLD);
                recordBody.setTypeface(gothic, Typeface.NORMAL);
                editRecord.setTypeface(gothic, Typeface.NORMAL);
                editRecord.setText("Add");
                editRecord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        medicalCOnditionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog = recordDialog("Medical Condition");
                dialog.show();
            }
        });
    }
    private AlertDialog recordDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPatientProfile.this);
        View v = getLayoutInflater().inflate(R.layout.dialog_advanced_record,null);
        builder.setView(v);
        final AlertDialog dialog = builder.create();
        recordTitle = (EditText)v.findViewById(R.id.title);
        recordTitle.setText(title);
        recordBody = (EditText)v.findViewById(R.id.message);
        editRecord = (TextView)v.findViewById(R.id.editButton);
        recordBody.setEnabled(false);
        recordTitle.setTypeface(gothic, Typeface.BOLD);
        recordBody.setTypeface(gothic, Typeface.NORMAL);
        editRecord.setTypeface(gothic, Typeface.NORMAL);
        editRecord.setOnTouchListener(new TextHighlighterOnTouchListener(editRecord));
        editRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editAdvancedRredordStatus ==0){
                    editRecord.setBackground(getResources().getDrawable(R.drawable.background_rounded_button_full_green));
                    editRecord.setText("Done");
                    recordBody.requestFocus();
                    recordBody.setEnabled(true);
                    editAdvancedRredordStatus=1;
                }
                else{
                    editRecord.setBackground(getResources().getDrawable(R.drawable.background_rounded_button_full));
                    editRecord.setText("Edit");
                    recordBody.setEnabled(false);
                    editAdvancedRredordStatus=0;
                }
            }
        });

        if(accessType==1){
            editRecord.setVisibility(View.INVISIBLE);
        }

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
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
