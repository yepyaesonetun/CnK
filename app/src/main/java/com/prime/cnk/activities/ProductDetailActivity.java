package com.prime.cnk.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.prime.cnk.R;
import com.prime.cnk.adapters.ImagePagerAdapter;
import com.prime.cnk.components.VerticalViewPager;
import com.prime.cnk.data.vo.NewProductVO;
import com.prime.cnk.fragments.InfoDialogFragment;
import com.prime.cnk.fragments.ProductImageDialogFragment;
import com.prime.cnk.mvp.presenters.NewProductDetailPresenter;
import com.prime.cnk.mvp.views.NewProductDetailView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

@SuppressWarnings("all")
public class ProductDetailActivity extends BaseActiviy implements NewProductDetailView {

    private static final String IE_PRODUCT_ID = "IE_PRODUCT_ID";

    @BindView(R.id.pager)
    VerticalViewPager pager;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    @BindView(R.id.tv_product_name)
    TextView tvProductName;

    @BindView(R.id.tv_color_chooser)
    TextView tvColorChooser;

    @BindView(R.id.btn_product_info)
    Button btnProductInfo;

    private boolean scrolledOnLastPage = false;
    private ImagePagerAdapter imagePagerAdapter;
    private NewProductDetailPresenter mPresenter;

    private NewProductVO mVO;

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(IE_PRODUCT_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this, this);

        int productId = getIntent().getIntExtra(IE_PRODUCT_ID, 0);
        mPresenter = ViewModelProviders.of(this).get(NewProductDetailPresenter.class);
        mPresenter.initPresenter(this);

        mPresenter.onUiReady(1 + productId).observe(this, newProductVO -> {
            displayNewProductData(newProductVO);
            mVO = newProductVO;
        });

        mPresenter.getErrorLD().observe(this, s -> Toast.makeText(ProductDetailActivity.this, s, Toast.LENGTH_SHORT).show());

        tvColorChooser.setOnClickListener(v -> displayColorChooser());

        btnProductInfo.setOnClickListener(v -> displayInfo(mVO));

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0F) {
                    int lastPage = imagePagerAdapter.getCount() - 1;
                    //scroll on last page has occured
                    scrolledOnLastPage = position == lastPage;

                    if (scrolledOnLastPage) {
                        // do something on end just like to show more items: 'You'll also like'
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void displayNewProductData(NewProductVO newProductVO) {
        tvProductName.setText(newProductVO.getProductTitle());
        ArrayList<String> dummyImgList = new ArrayList<>();
        dummyImgList.add(newProductVO.getProductImage());
        dummyImgList.add("https://www.charleskeith.com/media/catalog/product/cache/28/small_image/549x704/9df78eab33525d08d6e5fb8d27136e95/2/0/2018-L4-CK1-70380670-01-1.jpg");
        dummyImgList.add("https://www.charleskeith.com/media/catalog/product/cache/28/small_image/549x704/9df78eab33525d08d6e5fb8d27136e95/2/0/2018-L4-CK1-70920043-17-1.jpg");
        dummyImgList.add("https://www.charleskeith.com/media/catalog/product/cache/28/small_image/549x704/9df78eab33525d08d6e5fb8d27136e95/2/0/2018-L4-CK1-70920043-01-1.jpg");

        imagePagerAdapter = new ImagePagerAdapter(dummyImgList, this, mPresenter);
        pager.setAdapter(imagePagerAdapter);
        indicator.setViewPager(pager);
    }

    @Override
    public void displayInfo(NewProductVO newProductVO) {
        final InfoDialogFragment dialog = new InfoDialogFragment();
        final android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        dialog.setCancelable(true);

        dialog.show(fm, null);
    }

    @Override
    public void displayProductImage(String url) {
        final ProductImageDialogFragment productImage = new ProductImageDialogFragment(url);
        final FragmentManager fm = getSupportFragmentManager();
        productImage.setCancelable(false);

        productImage.show(fm, null);
    }

    @Override
    public void displayColorChooser() {
        Toast.makeText(this, "Clicked Color Chooser", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.iv_nav_icon)
    public void close() {
        finish();
    }
}
