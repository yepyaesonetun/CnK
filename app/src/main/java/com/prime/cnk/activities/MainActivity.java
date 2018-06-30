package com.prime.cnk.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prime.cnk.R;
import com.prime.cnk.adapters.ItemListRVAdapter;
import com.prime.cnk.components.EmptyViewPod;
import com.prime.cnk.components.SmartRecyclerView;
import com.prime.cnk.components.SmartScrollListener;
import com.prime.cnk.data.vo.NewProductVO;
import com.prime.cnk.mvp.presenters.NewProductListPresenter;
import com.prime.cnk.mvp.views.NewProductListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActiviy implements NewProductListView {

    public static int INITIAL_PAGE_NUMBER = 1;

    @BindView(R.id.iv_list)
    ImageView ivList;

    @BindView(R.id.iv_grid)
    ImageView ivGrid;

    @BindView(R.id.view_list_separator)
    View vListSeparator;

    @BindView(R.id.view_grid_separator)
    View vGridSeparator;

    @BindView(R.id.tv_item_count)
    TextView tvItemCount;

    @BindView(R.id.vp_empty)
    EmptyViewPod emptyViewPod;

    @BindView(R.id.rv_news_in)
    SmartRecyclerView rvNewsIn;

    private boolean isViewWithList = true;
    RecyclerView.LayoutManager lmVertical;
    RecyclerView.LayoutManager lmGrid;
    private SmartScrollListener mSmartScrollListener;

    private ItemListRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        lmVertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lmGrid = new GridLayoutManager(this, 2);

        NewProductListPresenter mPresenter = ViewModelProviders.of(this).get(NewProductListPresenter.class);
        mPresenter.initPresenter(this);


        adapter = new ItemListRVAdapter(this, mPresenter); // parse presenter
        rvNewsIn.setEmptyView(emptyViewPod);
        rvNewsIn.setLayoutManager(lmVertical);
        rvNewsIn.setAdapter(adapter);
        refreshRecycler(isViewWithList);

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                mPresenter.loadData(++INITIAL_PAGE_NUMBER);
            }
        });
        rvNewsIn.addOnScrollListener(mSmartScrollListener);


        mPresenter.getNewProductsLD().observe(this, newProductVOList -> {
            displayNewProductList(newProductVOList);
            tvItemCount.setText(getString(R.string.txt_tv_item_count, newProductVOList != null ? newProductVOList.size() : 0));
        });

        mPresenter.getErrorLD().observe(this, observedError ->
                Toast.makeText(MainActivity.this, observedError, Toast.LENGTH_SHORT).show());

        ivList.setOnClickListener(v -> {
            isViewWithList = true;
            refreshRecycler(true);
        });

        ivGrid.setOnClickListener(v -> {
            isViewWithList = false;
            refreshRecycler(false);
        });


    }

    private void displayNewProductList(List<NewProductVO> newProductVOList) {
        adapter.appendNewData(newProductVOList);
    }

    private void refreshRecycler(boolean isViewWithList) {
        if (isViewWithList) {
            vGridSeparator.setVisibility(View.GONE);
            vListSeparator.setVisibility(View.VISIBLE);
            rvNewsIn.setLayoutManager(lmVertical);
            adapter.notifyDataSetChanged();
        } else {
            vListSeparator.setVisibility(View.GONE);
            vGridSeparator.setVisibility(View.VISIBLE);
            rvNewsIn.setLayoutManager(lmGrid);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void navigateToDetail(int id) {
        Intent intent = ProductDetailActivity.newIntent(this, id);
        startActivity(intent);
    }
}
