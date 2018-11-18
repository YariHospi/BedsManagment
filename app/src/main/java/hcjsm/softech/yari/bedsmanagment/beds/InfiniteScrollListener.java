package hcjsm.softech.yari.bedsmanagment.beds;


import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import static android.support.v4.util.Preconditions.checkNotNull;

public abstract class InfiniteScrollListener extends OnScrollListener {

    private static final int VISIBLE_THRESHOLD = 5;
    private final LinearLayoutManager mLayoutManager;
    private final IDataLoading mDataLoading;

    @SuppressLint("RestrictedApi")
    public InfiniteScrollListener(IDataLoading dataLoading, LinearLayoutManager linearLayoutManager){
        mDataLoading = checkNotNull(dataLoading);
        mLayoutManager = checkNotNull(linearLayoutManager);
    }

    public abstract void onLoadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy){
        if(dy<0 || mDataLoading.isLoadingData() || !mDataLoading.isThereMoreData())
            return;

        final int visibleItemCount = recyclerView.getChildCount();
        final int totalItemCount = mLayoutManager.getItemCount();
        final int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)){
            onLoadMore();
        }
    }
}
