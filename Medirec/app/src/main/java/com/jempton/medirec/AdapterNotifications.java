package com.jempton.medirec;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import utilities.RoundedImage;

/**
 * Created by BALE on 15/01/2017.
 */

public class AdapterNotifications extends ArrayAdapter<ListNotifications> {
    Context context;
    ArrayList<ListNotifications> notifications;
    int layoutResourceId;
    LinearLayout notificationImageBackground;
    RoundedImage roundedImage;

    int [] roundedBackgrounds = {R.drawable.background_round_image_1,R.drawable.background_round_image_2,
            R.drawable.background_round_image_3,R.drawable.background_round_image_4};
    Typeface robotoThin, robotoLight, robotoRegular, gothic, avenirNextRegular, avenirNextDemiBold;
    public AdapterNotifications(Context context, int resource, ArrayList<ListNotifications> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.notifications = objects;
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
        ImageView notificationImage = (ImageView)inflate.findViewById(R.id.notificationImage);
        TextView notificationName = (TextView)inflate.findViewById(R.id.notificationName);
        notificationName.setTypeface(gothic, Typeface.NORMAL);
        TextView notificationTime = (TextView)inflate.findViewById(R.id.notificationTime);
        notificationTime.setTypeface(robotoThin);

        TextView notificationChecked = (TextView)inflate.findViewById(R.id.notificationCheckedIcon);

        final ListNotifications list = this.notifications.get(position);
        notificationImageBackground = (LinearLayout)inflate.findViewById(R.id.notificationImageBackground);
        notificationImage.setImageDrawable(new RoundedImage(drawableToBitmap(context.getResources().getDrawable(list.notificationImage))));
        //notificationImageBackground.setBackgroundResource(roundedBackgrounds[list.backgroundColorType]);
        notificationName.setText(list.notificationName);
        notificationTime.setText(list.notificationTime);
        if(list.notificationChecked == 0){
            notificationChecked.setVisibility(View.INVISIBLE);
        }
        else{
            notificationChecked.setVisibility(View.VISIBLE);
            notificationName.setTypeface(gothic, Typeface.BOLD);
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
}
