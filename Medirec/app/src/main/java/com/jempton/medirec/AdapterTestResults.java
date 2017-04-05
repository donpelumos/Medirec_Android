package com.jempton.medirec;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
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
 * Created by BALE on 20/03/2017.
 */

public class AdapterTestResults extends ArrayAdapter<ListTestResults> {
    Context context;
    ArrayList<ListTestResults> results;
    int layoutResourceId;
    int [] roundedBackgrounds = {R.drawable.jpeg_icon,R.drawable.png_icon, R.drawable.pdf_icon,R.drawable.word_icon};
    Typeface robotoThin, robotoLight, robotoRegular, gothic, avenirNextRegular, avenirNextDemiBold;
    public int rowHeight;
    public AdapterTestResults(Context context, int resource, ArrayList<ListTestResults> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.results = objects;
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
        ImageView testResultImage = (ImageView)inflate.findViewById(R.id.testResultImage);
        TextView testResultName = (TextView)inflate.findViewById(R.id.testResultName);
        testResultName.setTypeface(gothic, Typeface.NORMAL);

        final ListTestResults list = this.results.get(position);
        testResultImage.setImageDrawable(new RoundedImage(drawableToBitmap(context.getResources().getDrawable(roundedBackgrounds[list.resultType]))));
        testResultName.setText(list.resultName);

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
