package com.jempton.medirec;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import utilities.RoundedImage;

/**
 * Created by BALE on 21/03/2017.
 */

public class AdapterMessages extends ArrayAdapter<ListMessages> {
    Context context;
    ArrayList<ListMessages> messages;
    int layoutResourceId;
    RoundedImage roundedImage;
    Typeface robotoThin, robotoLight, robotoRegular, gothic, avenirNextRegular, avenirNextDemiBold;
    public AdapterMessages(Context context, int resource, ArrayList<ListMessages> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.messages = objects;
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
        TextView messageCount = (TextView)inflate.findViewById(R.id.messageCount);
        TextView doctorName = (TextView)inflate.findViewById(R.id.doctorName);
        doctorName.setTypeface(gothic, Typeface.NORMAL);
        TextView dateTime = (TextView)inflate.findViewById(R.id.dateTime);
        dateTime.setTypeface(gothic, Typeface.NORMAL);

        final ListMessages list = this.messages.get(position);
        doctorName.setText(list.doctorName);
        dateTime.setText(list.dateTime);
        messageCount.setText(String.valueOf(list.messageCount));
        if(list.messageCount == 0){
            messageCount.setVisibility(View.INVISIBLE);
        }
        else{
            messageCount.setVisibility(View.VISIBLE);
            doctorName.setTypeface(avenirNextRegular, Typeface.BOLD);
        }
        return inflate;
    }
}