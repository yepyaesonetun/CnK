package com.prime.cnk.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prime.cnk.R;
import com.prime.cnk.data.vo.NewProductVO;
import com.prime.cnk.delegates.NewProductItemDelegate;
import com.prime.cnk.utils.GlideApp;

import butterknife.BindView;

/**
 * Created by yepyaesonetun on 6/26/18.
 **/

public class ItemViewHolder extends BaseViewHolder<NewProductVO> {

    @BindView(R.id.iv_product)
    ImageView ivProduct;

    @BindView(R.id.tv_product_title)
    TextView tvProductTitle;

    private NewProductItemDelegate mDelegate;

    public ItemViewHolder(View itemView, NewProductItemDelegate delegate) {
        super(itemView);
        this.mDelegate = delegate;
    }

    @Override
    public void setData(NewProductVO data) {
        tvProductTitle.setText(data.getProductTitle());
        GlideApp.with(ivProduct.getContext())
                .load(data.getProductImage())
                .into(ivProduct);
    }

    @Override
    public void onClick(View v) {
        mDelegate.onTapNewProduct(getAdapterPosition());
    }
}
