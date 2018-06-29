package com.prime.cnk.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.CircularProgressDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.prime.cnk.R;
import com.prime.cnk.delegates.NewProductDetailDelegate;
import com.prime.cnk.utils.GlideApp;

import java.util.ArrayList;

/**
 * Created by yepyaesonetun on 6/26/18.
 **/

public class ImagePagerAdapter extends PagerAdapter {

    private ArrayList<String> mImageList;
    private Context mContext;
    private LayoutInflater inflater;
    private NewProductDetailDelegate mDelegate;

    public ImagePagerAdapter(ArrayList<String> imageList, Context context, NewProductDetailDelegate delegate) {
        this.mImageList = imageList;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mDelegate = delegate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View myImageLayout = inflater.inflate(R.layout.layout_slide_image, container, false);
        ImageView imageView = myImageLayout.findViewById(R.id.image);

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        GlideApp.with(mContext)
                .load(mImageList.get(position))
                .placeholder(circularProgressDrawable)
                .into(imageView);

        myImageLayout.setOnClickListener(v -> mDelegate.onTapProductImage(mImageList.get(position)));

        container.addView(myImageLayout, 0);
        return myImageLayout;
    }


    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

}
