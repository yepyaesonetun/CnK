package com.prime.cnk.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.CircularProgressDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.prime.cnk.R;
import com.prime.cnk.utils.GlideApp;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yepyaesonetun on 6/27/18.
 **/

@SuppressLint("ValidFragment")
public class ProductImageDialogFragment extends DialogFragment {

    private String imageUrl;

    public ProductImageDialogFragment(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_image_dialog, container, false);
        ImageView imgProduct = view.findViewById(R.id.iv_frag_product_image_dialog);
        ButterKnife.bind(this, view);

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(imgProduct.getContext());
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        GlideApp.with(imgProduct.getContext())
                .load(imageUrl)
                .placeholder(circularProgressDrawable)
                .into(imgProduct);

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    @OnClick(R.id.iv_product_image_dialog_close)
    public void close() {
        dismiss();
    }

}
