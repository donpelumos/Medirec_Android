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
 * Created by BALE on 10/04/2017.
 */

public class AdapterPatients extends ArrayAdapter<ListSearchedPatients> {
    Context context;
    ArrayList<ListSearchedPatients> patients;
    int layoutResourceId;
    LinearLayout patientImageBackground;
    RoundedImage roundedImage;

    int [] roundedBackgrounds = {R.drawable.background_round_image_1,R.drawable.background_round_image_2,
            R.drawable.background_round_image_3,R.drawable.background_round_image_4};
    Typeface robotoThin, robotoLight, robotoRegular, gothic, avenirNextRegular, avenirNextDemiBold;
    public AdapterPatients(Context context, int resource, ArrayList<ListSearchedPatients> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.patients = objects;
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
        ImageView patientImage = (ImageView)inflate.findViewById(R.id.patientImage);
        TextView patientName = (TextView)inflate.findViewById(R.id.patientName);
        ImageView patientAccessImage = (ImageView)inflate.findViewById(R.id.patientAccessIcon);
        patientName.setTypeface(gothic, Typeface.BOLD);

        TextView notificationChecked = (TextView)inflate.findViewById(R.id.notificationCheckedIcon);

        final ListSearchedPatients list = this.patients.get(position);
        patientImageBackground = (LinearLayout)inflate.findViewById(R.id.patientImageBackground);
        patientImage.setImageDrawable(new RoundedImage(drawableToBitmap(context.getResources().getDrawable(list.patientImage))));
        //notificationImageBackground.setBackgroundResource(roundedBackgrounds[list.backgroundColorType]);
        patientName.setText(list.patientName);
        if(list.patientAccessType == 0){
            patientAccessImage.setImageDrawable(context.getResources().getDrawable(R.drawable.completed));
        }
        else if(list.patientAccessType == 1){
            patientAccessImage.setImageDrawable(context.getResources().getDrawable(R.drawable.padlock_red_unlocked));
        }
        else if(list.patientAccessType == 2){
            patientAccessImage.setImageDrawable(context.getResources().getDrawable(R.drawable.padlock_red));
        }
        else if(list.patientAccessType == 3){
            patientAccessImage.setImageDrawable(context.getResources().getDrawable(R.drawable.shield_red));
        }
        patientAccessImage.setVisibility(View.GONE);
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
}
