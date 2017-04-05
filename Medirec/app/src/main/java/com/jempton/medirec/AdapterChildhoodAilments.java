package com.jempton.medirec;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BALE on 28/03/2017.
 */

public class AdapterChildhoodAilments extends ArrayAdapter<ListAllergy> {
    Context context;
    ArrayList<ListAllergy> allergies;
    int layoutResourceId;
    Typeface robotoThin, robotoLight, robotoRegular, gothic, avenirNextRegular;
    public AdapterChildhoodAilments(Context context, int resource, ArrayList<ListAllergy> objects) {
        super(context, resource, objects);
        this.context = context;
        this.allergies = objects;
        this.layoutResourceId = resource;
        robotoThin = Typeface.createFromAsset(getContext().getAssets(),"fonts/RobotoThin.ttf");
        robotoLight = Typeface.createFromAsset(getContext().getAssets(),"fonts/RobotoLight.ttf");
        robotoRegular = Typeface.createFromAsset(getContext().getAssets(),"fonts/RobotoRegular.ttf");
        gothic = Typeface.createFromAsset(getContext().getAssets(),"fonts/Gothic.TTF");
        avenirNextRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/avenir-next-regular.ttf");
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View inflate = view;
        if (inflate == null) {
            inflate = LayoutInflater.from(getContext()).inflate(this.layoutResourceId, null);
        }
        TextView allergyName = (TextView)inflate.findViewById(R.id.allergyName);
        ImageView viewAllergy = (ImageView)inflate.findViewById(R.id.viewAllergy);
        ImageView deleteAllergy = (ImageView)inflate.findViewById(R.id.deleteAllergy);
        viewAllergy.setOnTouchListener(new ImageHighlighterOnTouchListener(viewAllergy));
        deleteAllergy.setOnTouchListener(new ImageHighlighterOnTouchListener(deleteAllergy));
        deleteAllergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Remove Childhood Ailment");
                builder.setMessage("Are you sure you want to remove this childhood ailment?");
                builder.setIcon(R.drawable.bin);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                final AlertDialog dialog = builder.create();
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        final ListAllergy allergy = this.allergies.get(position);
        allergyName.setText(allergy.allergyName);
        allergyName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        allergyName.setTypeface(gothic, Typeface.NORMAL);
        return inflate;
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
