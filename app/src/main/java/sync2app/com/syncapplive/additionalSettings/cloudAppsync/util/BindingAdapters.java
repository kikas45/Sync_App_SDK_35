package sync2app.com.syncapplive.additionalSettings.cloudAppsync.util;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import sync2app.com.syncapplive.R;


public class BindingAdapters {

    @BindingAdapter("android:imageURL")
    public static void setImageURL(ImageView imageView, String URL) {
        try {

            if (URL == null || TextUtils.isEmpty(URL)){

                imageView.setImageResource(R.color.white);

            } else {

                imageView.setAlpha(0f);
                Picasso.get()
                        .load(Uri.parse(URL))
                        .noFade()
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                imageView.animate()
                                        .setDuration(400)
                                        .alpha(1f)
                                        .start();
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });

            }

        } catch (Exception e){

        }
    }

    @BindingAdapter("android:customImgURL")
    public static void setCustomURL(ImageView imageView, String URL) {
        try {

            if (URL == null || TextUtils.isEmpty(URL)){

                imageView.setImageResource(R.drawable.img_logo_icon);

            } else {

                imageView.setAlpha(0f);
                Picasso.get()
                        .load(Uri.parse(URL))
                        .noFade()
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                imageView.animate()
                                        .setDuration(400)
                                        .alpha(1f)
                                        .start();
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });

            }

        } catch (Exception e){

        }
    }

}
