package com.jempton.medirec;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Created by BALE on 28/03/2017.
 */

public class ActivityBasicMedicalInformation  extends AppCompatActivity {
    ActionBar appBar;
    Typeface robotoThin, robotoLight, gothic, avenirNextRegular;
    ImageView backButton;
    EditText password,recordBody,recordTitle;
    TextView title, tab1, tab2, bloodGroup, genotype, height, weight, editButton, confirmPassword, dialogTitle, dialogMessage, addAllergy,
    editRecord, addChildhoodAilment, addFamilyHistory, addMedicalCondition, topicAllergies, topicChildhoodAIlment, topicFamilyHistory,
    topicMedicalCondition;
    Spinner bloodGroupOptions, genotypeOptions, heightOptions1, heightOptions2, weightOptions1, weightOptions2;
    View subTab1, subTab2;
    int currentTab = 1; int editAdvancedRredordStatus = 0;
    String [] heightTypes = {"centimetres","metres","feet"};
    String [] weightTypes = {"kilograms","pounds"};
    String [] bloodGroupTypes = {"O positive (O+)","O negative (O-)","A positive (A+)","A negative (A-)","B positive (B+)",
        "B negative (B-)","AB positive (AB+)","AB negative (AB-)"};
    String [] genotypeTypes = {"AA","AS","SS","CC","AC","SC"};
    ArrayList<String> heightCetimetres = new ArrayList<>();
    ArrayList<String> heightMetres = new ArrayList<>();
    ArrayList<String> heightFeet = new ArrayList<>();
    ArrayList<String> weightKilos = new ArrayList<>();
    ArrayList<String> weightPounds = new ArrayList<>();
    LinearLayout basicLayout, advancedLayout;
    ListView allergiesList, childhoodAilmentsList, familyHistoryList, medicalCOnditionsList;
    ArrayList<ListAllergy> allergies, childhoodAilments, familyHistory, medicalConditions;
    AdapterAllergy adapterAllergy;
    AdapterChildhoodAilments adapterChildhoodAilments;
    AdapterFamilyHistory adapterFamilyHistory;
    AdapterMedicalCondition adapterMedicalCondition;
    ArrayAdapter<String> heightOptions1Adapter, heightOptions2Adapter, weightOptions1Adapter, weightOptions2Adapter,
            genotypeOptionsAdapter, bloodGroupOptionsAdapter;
    int editMode = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        robotoThin = Typeface.createFromAsset(getAssets(), "fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getAssets(), "fonts/RobotoLight.ttf");
        gothic = Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getAssets(), "fonts/avenir-next-regular.ttf");
        appBar = getSupportActionBar();
        appBar.hide();
        setContentView(R.layout.activity_basic_medical_information);
        backButton = (ImageView)findViewById(R.id.backButton);
        basicLayout = (LinearLayout)findViewById(R.id.basicLayout);
        advancedLayout = (LinearLayout)findViewById(R.id.advancedLayout);
        advancedLayout.setVisibility(View.GONE);
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
        title = (TextView)findViewById(R.id.title);
        title.setTypeface(gothic, Typeface.BOLD);
        tab1 = (TextView)findViewById(R.id.tab1);
        tab2 = (TextView)findViewById(R.id.tab2);
        subTab1 = findViewById(R.id.tabLine1);
        subTab2 = findViewById(R.id.tabLine2);
        tab1.setOnTouchListener(new TextHighlighterOnTouchListener(tab1));
        tab2.setOnTouchListener(new TextHighlighterOnTouchListener(tab2));
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTab == 1){

                }
                else{
                    currentTab = 1;
                    tab1.setBackgroundColor(Color.rgb(255,255,255));
                    tab1.setTextColor(Color.rgb(119,119,119));
                    tab2.setBackgroundColor(Color.rgb(221,221,221));
                    tab2.setTextColor(Color.rgb(170,170,170));
                    basicLayout.setVisibility(View.VISIBLE);
                    advancedLayout.setVisibility(View.GONE);
                    subTab1.setVisibility(View.VISIBLE);
                    subTab2.setVisibility(View.INVISIBLE);
                }
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTab == 1){
                    currentTab = 2;
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
        bloodGroup = (TextView)findViewById(R.id.bloodGroup);
        genotype = (TextView)findViewById(R.id.genotype);
        height = (TextView)findViewById(R.id.height);
        weight = (TextView)findViewById(R.id.weight);
        editButton = (TextView)findViewById(R.id.editButton);
        bloodGroup.setTypeface(gothic, Typeface.NORMAL);
        genotype.setTypeface(gothic, Typeface.NORMAL);
        height.setTypeface(gothic, Typeface.NORMAL);
        weight.setTypeface(gothic, Typeface.NORMAL);
        editButton.setTypeface(gothic, Typeface.NORMAL);
        bloodGroup.setOnTouchListener(new TextHighlighterOnTouchListener(bloodGroup));
        genotype.setOnTouchListener(new TextHighlighterOnTouchListener(genotype));
        height.setOnTouchListener(new TextHighlighterOnTouchListener(height));
        weight.setOnTouchListener(new TextHighlighterOnTouchListener(weight));
        editButton.setOnTouchListener(new TextHighlighterOnTouchListener(editButton));

        for(int i=151; i <=210; i++){
            heightCetimetres.add(String.valueOf(i)+" cm");
            heightMetres.add(String.valueOf(new DecimalFormat("#.00").format((double)i/100).toString())+" m");
        }
        for(int i=49; i<=96; i++){
            int ft = (int)Math.floor(i/12);
            int in = (int)(i%12);
            heightFeet.add(String.valueOf(ft)+"ft "+String.valueOf(in)+" in");
        }
        for(int i=51; i<=150; i++){
            weightKilos.add(String.valueOf(i)+" kg");
            weightPounds.add(String.valueOf(new DecimalFormat("#.00").format((i*2.20462)).toString()+" lbs"));
        }
        genotypeOptions = (Spinner)findViewById(R.id.genotypeOptions);
        genotypeOptionsAdapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this, android.R.layout.simple_spinner_item,
                genotypeTypes){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }

            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        genotypeOptions.setAdapter(genotypeOptionsAdapter);
        bloodGroupOptions = (Spinner)findViewById(R.id.bloodGroupOptions);
        bloodGroupOptionsAdapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this, android.R.layout.simple_spinner_item,
                bloodGroupTypes){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }

            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        bloodGroupOptions.setAdapter(bloodGroupOptionsAdapter);
        heightOptions1 = (Spinner)findViewById(R.id.heightOptions1);
        heightOptions1Adapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this,
                android.R.layout.simple_spinner_item, heightTypes){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }

            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        heightOptions1.setAdapter(heightOptions1Adapter);
        heightOptions2 = (Spinner)findViewById(R.id.heightOptions2);
        heightOptions2Adapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this,
                android.R.layout.simple_spinner_item,heightCetimetres){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }

            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        heightOptions2.setAdapter(heightOptions2Adapter);
        weightOptions1 = (Spinner)findViewById(R.id.weightOptions1);
        weightOptions1Adapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this,
                android.R.layout.simple_spinner_item,weightTypes){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }

            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        weightOptions1.setAdapter(weightOptions1Adapter);
        weightOptions2 = (Spinner)findViewById(R.id.weightOptions2);
        weightOptions2Adapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this,
                android.R.layout.simple_spinner_item,weightKilos){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }

            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);
                Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                ((TextView) v).setTypeface(externalFont);
                return v;
            }
        };
        weightOptions2.setAdapter(weightOptions2Adapter);
        weightOptions1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    weightOptions2Adapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this,
                            android.R.layout.simple_spinner_item,weightKilos){
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }

                        public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                            View v =super.getDropDownView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }
                    };
                    weightOptions2.setAdapter(weightOptions2Adapter);
                }
                else if(position == 1){
                    weightOptions2Adapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this,
                            android.R.layout.simple_spinner_item,weightPounds){
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }

                        public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                            View v =super.getDropDownView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }
                    };
                    weightOptions2.setAdapter(weightOptions2Adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        heightOptions1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position ==0){
                    heightOptions2Adapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this,
                            android.R.layout.simple_spinner_item,heightCetimetres){
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }

                        public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                            View v =super.getDropDownView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }
                    };
                    heightOptions2.setAdapter(heightOptions2Adapter);
                }
                else if(position == 1){
                    heightOptions2Adapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this,
                            android.R.layout.simple_spinner_item,heightMetres){
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }

                        public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                            View v =super.getDropDownView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }
                    };
                    heightOptions2.setAdapter(heightOptions2Adapter);
                }
                else if(position == 2){
                    heightOptions2Adapter = new ArrayAdapter<String>(ActivityBasicMedicalInformation.this,
                            android.R.layout.simple_spinner_item,heightFeet){
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }

                        public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                            View v =super.getDropDownView(position, convertView, parent);
                            Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Gothic.TTF");
                            ((TextView) v).setTypeface(externalFont);
                            return v;
                        }
                    };
                    heightOptions2.setAdapter(heightOptions2Adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editButton.setBackground(getResources().getDrawable(R.drawable.background_rounded_button_full_green));
        bloodGroupOptions.setEnabled(false); genotypeOptions.setEnabled(false);heightOptions1.setEnabled(false);
        heightOptions2.setEnabled(false); weightOptions1.setEnabled(false); weightOptions2.setEnabled(false);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editMode == 1){
                    editMode = 0;
                    editButton.setText("Edit");
                    editButton.setBackground(getResources().getDrawable(R.drawable.background_rounded_button_full_green));
                    bloodGroupOptions.setEnabled(false); genotypeOptions.setEnabled(false);heightOptions1.setEnabled(false);
                    heightOptions2.setEnabled(false); weightOptions1.setEnabled(false); weightOptions2.setEnabled(false);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityBasicMedicalInformation.this);
                    View view = getLayoutInflater().inflate(R.layout.dialog_confirm_password,null);
                    builder.setView(view);
                    final AlertDialog dialog = builder.create();
                    dialogTitle = (TextView)view.findViewById(R.id.title);
                    dialogMessage = (TextView)view.findViewById(R.id.message);
                    confirmPassword = (TextView)view.findViewById(R.id.confirmButton);
                    password = (EditText)view.findViewById(R.id.password);
                    dialogTitle.setTypeface(gothic, Typeface.BOLD);
                    dialogMessage.setTypeface(gothic, Typeface.NORMAL);
                    confirmPassword.setTypeface(gothic, Typeface.NORMAL);
                    password.setTypeface(gothic, Typeface.NORMAL);
                    confirmPassword.setOnTouchListener(new TextHighlighterOnTouchListener(confirmPassword));
                    confirmPassword.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            editMode = 1;editButton.setText("Done");
                            editButton.setBackground(getResources().getDrawable(R.drawable.background_rounded_button_full));
                            bloodGroupOptions.setEnabled(true); genotypeOptions.setEnabled(true);heightOptions1.setEnabled(true);
                            heightOptions2.setEnabled(true); weightOptions1.setEnabled(true); weightOptions2.setEnabled(true);
                        }
                    });
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            }
        });

        allergiesList = (ListView)findViewById(R.id.allergiesList);
        allergies = new ArrayList<>();
        allergies.add(new ListAllergy("Allergy Name"));  allergies.add(new ListAllergy("Allergy Name"));
        allergies.add(new ListAllergy("Allergy Name"));  allergies.add(new ListAllergy("Allergy Name"));
        adapterAllergy = new AdapterAllergy(ActivityBasicMedicalInformation.this,R.layout.row_allergies, allergies);
        allergiesList.setAdapter(adapterAllergy);
        LinearLayout.LayoutParams allergiesListParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)((52*allergies.size()*getScreenDensity()-(int)(2*getScreenDensity()))));
        allergiesList.setLayoutParams(allergiesListParams);
        addAllergy = (TextView)findViewById(R.id.addAllergy);
        addAllergy.setOnTouchListener(new TextHighlighterOnTouchListener(addAllergy));
        addAllergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityBasicMedicalInformation.this);
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
        adapterChildhoodAilments = new AdapterChildhoodAilments(ActivityBasicMedicalInformation.this,R.layout.row_allergies,
                childhoodAilments);
        childhoodAilmentsList.setAdapter(adapterChildhoodAilments);
        LinearLayout.LayoutParams childhoodAilmentsParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)((52*childhoodAilments.size()*getScreenDensity()-(int)(2*getScreenDensity()))));
        childhoodAilmentsList.setLayoutParams(childhoodAilmentsParams);
        addChildhoodAilment = (TextView)findViewById(R.id.addChildhoodAilment);
        addChildhoodAilment.setOnTouchListener(new TextHighlighterOnTouchListener(addChildhoodAilment));
        addChildhoodAilment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityBasicMedicalInformation.this);
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
        adapterFamilyHistory = new AdapterFamilyHistory(ActivityBasicMedicalInformation.this,R.layout.row_allergies,
                familyHistory);
        familyHistoryList.setAdapter(adapterFamilyHistory);
        LinearLayout.LayoutParams familyHistoryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)((52*familyHistory.size()*getScreenDensity()-(int)(2*getScreenDensity()))));
        familyHistoryList.setLayoutParams(familyHistoryParams);
        addFamilyHistory = (TextView)findViewById(R.id.addFamilyHistory);
        addFamilyHistory.setOnTouchListener(new TextHighlighterOnTouchListener(addFamilyHistory));
        addFamilyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityBasicMedicalInformation.this);
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
        adapterMedicalCondition = new AdapterMedicalCondition(ActivityBasicMedicalInformation.this,R.layout.row_allergies,
                medicalConditions);
        medicalCOnditionsList.setAdapter(adapterMedicalCondition);
        LinearLayout.LayoutParams medicalConditionParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int)((52*medicalConditions.size()*getScreenDensity()-(int)(2*getScreenDensity()))));
        medicalCOnditionsList.setLayoutParams(medicalConditionParams);
        addMedicalCondition = (TextView)findViewById(R.id.addMedicalCOndition);
        addMedicalCondition.setOnTouchListener(new TextHighlighterOnTouchListener(addMedicalCondition));
        addMedicalCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityBasicMedicalInformation.this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityBasicMedicalInformation.this);
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
