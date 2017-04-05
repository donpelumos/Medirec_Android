package com.jempton.medirec;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import utilities.RoundedImage;

/**
 * Created by BALE on 04/04/2017.
 */

public class AdapterAddNewDoctor extends ArrayAdapter<ListAddNewDoctor> {
    Context context;
    ArrayList<ListAddNewDoctor> doctors;
    int layoutResourceId;
    RoundedImage roundedImage;
    AppCompatActivity sourceActivity;
    Typeface robotoThin, robotoLight, robotoRegular, gothic, avenirNextRegular, avenirNextDemiBold;
    public AdapterAddNewDoctor(Context context, int resource, ArrayList<ListAddNewDoctor> objects, AppCompatActivity sourceACtivity) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.doctors = objects;
        this.sourceActivity = sourceACtivity;
        robotoThin = Typeface.createFromAsset(getContext().getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getContext().getAssets(),"fonts/RobotoLight.ttf");
        robotoRegular = Typeface.createFromAsset(getContext().getAssets(),"fonts/RobotoRegular.ttf");
        gothic = Typeface.createFromAsset(getContext().getAssets(),"fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/avenir-next-regular.ttf");
        avenirNextDemiBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/avenirnext-demibold.ttf");
    }
    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View inflate = view;
        if (inflate == null) {
            inflate = LayoutInflater.from(getContext()).inflate(this.layoutResourceId, null);
        }
        ImageView doctorImage = (ImageView)inflate.findViewById(R.id.doctorImage);
        final TextView doctorName = (TextView)inflate.findViewById(R.id.doctorName);
        doctorName.setTypeface(gothic, Typeface.BOLD);
        TextView hospitalName = (TextView)inflate.findViewById(R.id.hospitalName);
        TextView addDoctor = (TextView)inflate.findViewById(R.id.addDoctor);


        final ListAddNewDoctor list = this.doctors.get(position);
        doctorName.setText(list.doctorName);
        hospitalName.setText(list.hospitalName);
        hospitalName.setTypeface(gothic, Typeface.NORMAL);
        addDoctor.setOnTouchListener(new TextHighlighterOnTouchListener(addDoctor));
        addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.signout);
                View inflate = sourceActivity.getLayoutInflater().inflate(R.layout.dialog_sign_out,null);
                builder.setView(inflate);
                TextView optionYes = (TextView)inflate.findViewById(R.id.optionYes);
                TextView optionNo = (TextView)inflate.findViewById(R.id.optionNo);
                TextView title = (TextView)inflate.findViewById(R.id.title);
                TextView message = (TextView)inflate.findViewById(R.id.message);
                title.setText("Add New Doctor");
                message.setText("Do you want to add "+doctorName.getText().toString()+" to you doctors?");
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
            }
        });
        doctorImage.setImageDrawable(new RoundedImage(drawableToBitmap(getContext().getResources().getDrawable(list.doctorImage))));
        if(list.toAdd == 0){
            addDoctor.setVisibility(View.GONE);
        }
        else{
            addDoctor.setVisibility(View.VISIBLE);
        }
        return inflate;
    }
    public Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
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
