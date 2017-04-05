package com.jempton.medirec;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BALE on 24/02/2017.
 */

public class AdapterAccountOptions extends ArrayAdapter<ListAccountOptions> {
    Context context;
    ArrayList<ListAccountOptions> options;
    int layoutResourceId;
    Typeface robotoThin, robotoLight, robotoRegular, gothic, avenirNextRegular;
    public AdapterAccountOptions(Context context, int resource, ArrayList<ListAccountOptions> objects) {
        super(context, resource, objects);
        this.context = context;
        this.options = objects;
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
        ImageView optionImage = (ImageView)inflate.findViewById(R.id.optionImage);
        TextView optionName = (TextView)inflate.findViewById(R.id.optionName);
        final ListAccountOptions option = this.options.get(position);
        optionName.setText(option.optionName);
        optionName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        optionName.setTypeface(gothic);
        optionImage.setImageResource(option.optionImage);
        return inflate;
    }
}
