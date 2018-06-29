package com.prime.cnk.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.prime.cnk.R;
import com.prime.cnk.data.vo.NewProductVO;
import com.prime.cnk.delegates.NewProductItemDelegate;
import com.prime.cnk.viewholders.ItemViewHolder;

/**
 * Created by yepyaesonetun on 6/26/18.
 **/

public class ItemListRVAdapter extends BaseRecyclerAdapter<ItemViewHolder, NewProductVO> {

    private NewProductItemDelegate mDelegate;

    public ItemListRVAdapter(Context context, NewProductItemDelegate delegate) {
        super(context);
        this.mDelegate = delegate;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsItemView = mLayoutInflator.inflate(R.layout.item_view_product, parent, false);
        return new ItemViewHolder(newsItemView, mDelegate);
    }
}
